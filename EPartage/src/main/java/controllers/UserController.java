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

/**
 * Class managing user page's actions
 * 
 * @author 
 */
@Controller
@RequestMapping("/workspace")
public class UserController {
	
	/**
	 * @see UserService
	 */
	@Autowired
	UserService userService;
	
	/**
	 * @see MessageService
	 */
	@Autowired
	MessageService messageService;

	public UserController() {
		super();
	}

	/**
	 * Return the view corresponding to the user workspace
	 * 
	 * @return View name
	 */
	@RequestMapping("/index.htm")
	public String index() {		
		return "workspace/workspace";
	}

	/**
	 * Return the view corresponding to the contact form
	 * 
	 * @return View name
	 */
	@RequestMapping(value = "/contact.htm",  method = RequestMethod.GET)
	public String contact() {		
		return "workspace/contact";
	}
	
	/**
	 * Allows to contact administrator
	 * Called when user sends contact form
	 * 
	 * @param object
	 * @param message Content
	 * @param session {@link HttpSession}
	 * @param redirectAttributes {@link RedirectAttributes}
	 * @return view with specific message
	 */
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
	
	/**
	 * Displays avatar corresponding to the URL parameter 
	 * 
	 * @param id User id
	 * @param response {@link HttpServletResponse}
	 */
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
	
	/**
	 * @param session {@link HttpSession}
	 * @return Student in session
	 */
	@ModelAttribute("student")
	public Student getStudent (HttpSession session) {
		return (Student) session.getAttribute("userSession");
	}
	
	/**
	 * @param session {@link HttpSession}
	 * @return User in session
	 */
	@ModelAttribute("user")
	public User getUser (HttpSession session) {
		User userSession = (User) session.getAttribute("userSession");
		if (userSession == null)
			return null;
		return userService.findByLogin(userSession.getEmail());
	}
	
	/**
	 * @param session {@link HttpSession}
	 * @return Number of unconsulted message of the user in session
	 */
	@ModelAttribute("nbOfUnconsultedMessages")
	public int nbOfUnconsultedMessages(HttpSession session) {
		User userSession = (User) session.getAttribute("userSession");
		if (userSession == null)
			return 0;
		return messageService.getNbOfUnconsultedMessages(userSession);
	}
	
	/**
	 * @param session {@link HttpSession}
	 * @return Groups list corresponding to the user in session
	 */
	@ModelAttribute("groupsList")
	public Collection<Group> getUserGroups(HttpSession session) {
		User userSession = (User) session.getAttribute("userSession");
		if (userSession == null)
			return null;
		return userService.findByLogin(userSession.getEmail()).getGroups();
	}
	
	/**
	 * @param session {@link HttpSession}
	 * @return Map with groups name and corresponding encoded groups name
	 */
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
	
	/**
	 * @param session {@link HttpSession}
	 * @return All publications of the groups to which the user in session 
	 * belongs
	 */
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
