package controllers;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import services.MessageService;
import services.UserService;
import utilities.AsciiToHex;
import utilities.MailSender;
import domain.Category;
import domain.Group;
import domain.Publication;
import domain.Student;
import domain.Subcategory;
import domain.User;

@Controller
@RequestMapping("/workspace")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	MessageService messageService;

	public UserController() {
		super();
	}

	@RequestMapping("/index.htm")
	public String index(HttpSession session) {		
		return "workspace/workspace";
	}

	@RequestMapping(value = "/contact.htm",  method = RequestMethod.GET)
	public String contact() {		
		return "workspace/contact";
	}
	
	@RequestMapping(value = "/contact.htm",  method = RequestMethod.POST)
	public ModelAndView contact(
			@RequestParam(required = true) String object,
			@RequestParam(required = true) String message,
			HttpSession session, RedirectAttributes redirectAttributes) {
		Map<String, String> errors = new HashMap<String, String>();
		
		if(object.length() < 2)
			errors.put("objectError", "L'objet doit contenir plus de 2 caractères");
		if(message.length() < 10)
			errors.put("messageError", "Le message doit contenir plus de 10 caractères");
		if(!errors.isEmpty()) {
			errors.put("objectOld", object);
			errors.put("messageOld", message);
			return new ModelAndView("workspace/contact", errors);
		}
				
		Student student = (Student) session.getAttribute("userSession");
		
		MailSender
			.sendEmail(
				"luminy.annuaire@gmail.com",
				"e-Partage - Nouveau message de " + student.getFirstName() + " " + student.getLastName() + " - Objet : " + object,
				"Message reçu de la part de " + student.getFirstName() + " " + student.getLastName() + "\n"
						+ "Numéro étudiant : " + student.getNumStudent() + "\n"
						+ "Promotion : " + student.getPromo() + "\n"
						+ "Email : " + student.getEmail() + "\n"
						+ "Adresse :" + student.getAdress() + "\n"
						+ "Téléphone :" + student.getPhone() + "\n\n\n"
						+ "Objet :" + object + "\n\n" 
						+ "Message : " + message);
		redirectAttributes.addFlashAttribute("type", "success");
		redirectAttributes.addFlashAttribute("message", "Votre message a été envoyé à l'administrateur.");
		return new ModelAndView("redirect:/workspace/index.htm");
	}
	
	@RequestMapping("/avatar.htm")
	public void avatar(
			@RequestParam(value = "id", required = false) String id,
			HttpServletResponse response) {
		
		User user = userService.find(Integer.parseInt(id));
		if (user != null) {
			try {
				OutputStream o = response.getOutputStream();
				o.write(user.getAvatar());
				o.flush();
				o.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	@ModelAttribute("student")
	public Student getStudent (HttpSession session) {
		return (Student) session.getAttribute("userSession");
	}
	
	@ModelAttribute("user")
	public User getUser (HttpSession session) {
		User userSession = (User) session.getAttribute("userSession");
		if (userSession == null)
			return null;
		return userService.findByLogin(userSession.getEmail());
	}
	
	@ModelAttribute("nbOfUnconsultedMessages")
	public int nbOfUnconsultedMessages(HttpSession session) {
		User userSession = (User) session.getAttribute("userSession");
		if (userSession == null)
			return 0;
		return messageService.getNbOfUnconsultedMessages(userSession);
	}
	
	@ModelAttribute("groupsList")
	public Collection<Group> getUserGroups(HttpSession session) {
		User userSession = (User) session.getAttribute("userSession");
		if (userSession == null)
			return null;
		return userService.findByLogin(userSession.getEmail()).getGroups();
	}
	
	@ModelAttribute("groupsUrl")
	public Map<String, Object> getGroupsUrl (HttpSession session) {
		User userSession = (User) session.getAttribute("userSession");
		if (userSession == null)
			return null;
		User user = userService.findByLogin(userSession.getEmail());
		
		Map<String, Object> groupsUrl = new HashMap<String, Object>();
		
		for(Group g : user.getGroups()) {
			String nameG = g.getName();
			String urlNameG = AsciiToHex.asciiToHex(nameG);
			groupsUrl.put(nameG, urlNameG);
		}
		
		return groupsUrl;
	}
	
	@ModelAttribute("allPublications")
	public List<Publication> getAllPublications(HttpSession session) {

		User userSession = (User) session.getAttribute("userSession");
		if (userSession == null)
			return null;
		User user = userService.findByLogin(userSession.getEmail());
		List<Publication> publications = new ArrayList<Publication>();
		
		for(Group group : user.getGroups())
			for(Category category : group.getCategories())
				for(Subcategory subcategory : category.getSubcategories())
					publications.addAll(subcategory.getPublications());

		Collections.sort(publications);
		
		return publications;
	}
}
