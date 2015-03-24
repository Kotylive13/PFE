package controllers;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import services.HobbyService;
import services.MessageService;
import services.UserHobbyService;
import services.UserService;
import utilities.AsciiToHex;
import utilities.CryptPassword;
import utilities.MailSender;
import domain.Category;
import domain.Group;
import domain.Hobby;
import domain.Publication;
import domain.Student;
import domain.Subcategory;
import domain.User;
import domain.UserHobby;

/**
 * Class managing user page's actions
 * 
 * @author
 */
@Controller
@RequestMapping("/workspace")
public class UserController {

	/**
	 * @see ServletContext
	 */
	@Autowired
	ServletContext context;

	/**
	 * @see UserService
	 */
	@Autowired
	UserService userService;

	/**
	 * @see HobbyService
	 */
	@Autowired
	HobbyService hobbyService;

	/**
	 * @see MessageService
	 */
	@Autowired
	MessageService messageService;

	/**
	 * @see UserHobbyService
	 */
	@Autowired
	UserHobbyService userHobbyService;

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
	@RequestMapping(value = "/contact.htm", method = RequestMethod.GET)
	public String contact() {
		return "workspace/contact";
	}

	/**
	 * Allows to contact administrator Called when user sends contact form
	 * 
	 * @param object
	 * @param message
	 *            Content
	 * @param session
	 *            {@link HttpSession}
	 * @param redirectAttributes
	 *            {@link RedirectAttributes}
	 * @return view with specific message
	 */
	@RequestMapping(value = "/contact.htm", method = RequestMethod.POST)
	public ModelAndView contact(@RequestParam(required = true) String object,
			@RequestParam(required = true) String message, HttpSession session,
			RedirectAttributes redirectAttributes) {
		Map<String, String> errors = new HashMap<String, String>();

		if (object.length() < 2)
			errors.put("objectError",
					"L'objet doit contenir plus de 2 caractères");
		if (message.length() < 10)
			errors.put("messageError",
					"Le message doit contenir plus de 10 caractères");
		if (!errors.isEmpty())
			return new ModelAndView("workspace/contact", errors);

		Student student = (Student) session.getAttribute("userSession");

		MailSender.sendEmail(
				"luminy.annuaire@gmail.com",
				"e-Partage - Nouveau message de " + student.getFirstName()
						+ " " + student.getLastName() + " - Objet : " + object,
				"Message reçu de la part de " + student.getFirstName() + " "
						+ student.getLastName() + "\n" + "Numéro étudiant : "
						+ student.getNumStudent() + "\n" + "Promotion : "
						+ student.getPromo() + "\n" + "Email : "
						+ student.getEmail() + "\n" + "Adresse :"
						+ student.getAdress() + "\n" + "Téléphone :"
						+ student.getPhone() + "\n\n\n" + "Objet :" + object
						+ "\n\n" + "Message : " + message);
		redirectAttributes.addFlashAttribute("type", "success");
		redirectAttributes.addFlashAttribute("message",
				"Votre message a été envoyé à l'administrateur.");
		return new ModelAndView("redirect:/workspace/index.htm");
	}

	/**
	 * Displays avatar corresponding to the URL parameter
	 * 
	 * @param id
	 *            User id
	 * @param response
	 *            {@link HttpServletResponse}
	 */
	@RequestMapping("/avatar.htm")
	public void avatar(@RequestParam(value = "id", required = false) String id,
			HttpServletResponse response) {

		User user = userService.find(Integer.parseInt(id));
		if (user != null) {
			if (user.getAvatar() != null) {
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
	}

	@RequestMapping(value = "/editprofile", method = RequestMethod.GET)
	public ModelAndView subscribe(Model model, HttpSession session) {

		Map<String, Object> hobbies = new HashMap<String, Object>();
		hobbies.put("hobbies", hobbyService.findAll());

		ModelAndView result = new ModelAndView("workspace/editprofile", hobbies);
		model.addAttribute("student",
				(Student) session.getAttribute("userSession"));
		return result;
	}

	@RequestMapping(value = "/editprofile", method = RequestMethod.POST)
	public ModelAndView subscribePost(@Valid @ModelAttribute Student student,
			@RequestParam(required = false) MultipartFile file,
			BindingResult bindingResult, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {

		Map<String, Object> allHobbies = new HashMap<String, Object>();
		allHobbies.put("hobbies", hobbyService.findAll());
		ModelAndView result = new ModelAndView("workspace/editprofile",
				allHobbies);

		// Validation du model
		if (bindingResult.hasErrors()) {
			return result;
		}

		String oldPassword = request.getParameter("oldPassword");
		if (!(CryptPassword.getCryptString(oldPassword)).equals(student
				.getPassword()))
			return result.addObject("errorOldPassword",
					"Le mot de passe n'est pas le bon");
		student.setPassword(oldPassword);

		String newPassword = request.getParameter("newPassword");
		if (!newPassword.equals("")) {
			if (!request.getParameter("confirmation").equals(newPassword))
				return result.addObject("errorConfirmation",
						"Les mots de passe ne concordent pas");

			student.setPassword(newPassword);
		}

		String input = request.getParameter("hobbies");
		input = input.replace(" ", "");
		String[] tempHobbies = input.split(",");

		List<String> userHobbies = new ArrayList<String>();
		for (String s : tempHobbies)
			if (s.length() > 0)
				userHobbies.add(s);

		if (userHobbies.isEmpty())
			return result.addObject("errorHobbies",
					"Veuillez renseigner des centres d'intérêt");

		Set<Hobby> hobbies = new HashSet<Hobby>();
		for (String userHobby : userHobbies) {
			Hobby hobby = hobbyService.find(userHobby);
			if (hobby == null) {
				hobby = new Hobby(userHobby);
				hobby = hobbyService.save(hobby);
			}
			hobbies.add(hobby);
		}
		student.setHobbies(hobbies);

		// save avatar
		try {
			if (!file.isEmpty()) {
				if (!file.getContentType().equals("image/gif")
						&& !file.getContentType().equals("image/jpeg")
						&& !file.getContentType().equals("image/png"))
					return result
							.addObject("errorFile",
									"L'avatar doit être un fichier de type image (.gif, .jpeg ou .png).");

				if (file.getSize() > 1048576)
					return result
							.addObject("errorFile",
									"La taille de l'image est trop grande, veuillez en selectionner une autre");

				student.setAvatar(file.getBytes());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		student.setPassword(oldPassword);
		userService.save(student);

		redirectAttributes.addFlashAttribute("type", "success");
		redirectAttributes.addFlashAttribute("message",
				"Votre profil a bien été modifié.");
		return new ModelAndView("redirect:/workspace/editprofile.htm");
	}

	/**
	 * @param session
	 *            {@link HttpSession}
	 * @return Student in session
	 */
	@ModelAttribute("student")
	public Student getStudent(HttpSession session) {
		return (Student) session.getAttribute("userSession");
	}

	/**
	 * @param session
	 *            {@link HttpSession}
	 * @return User in session
	 */
	@ModelAttribute("user")
	public User getUser(HttpSession session) {
		User userSession = (User) session.getAttribute("userSession");
		if (userSession == null)
			return null;
		return userService.findByLogin(userSession.getEmail());
	}

	/**
	 * @param session
	 *            {@link HttpSession}
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
	 * @param session
	 *            {@link HttpSession}
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
	 * @param session
	 *            {@link HttpSession}
	 * @return Map with groups name and corresponding encoded groups name
	 */
	@ModelAttribute("groupsUrl")
	public Map<String, Object> getGroupsUrl(HttpSession session) {
		User userSession = (User) session.getAttribute("userSession");
		if (userSession == null)
			return null;
		User user = userService.findByLogin(userSession.getEmail());

		Map<String, Object> groupsUrl = new HashMap<String, Object>();

		for (Group g : user.getGroups()) {
			String nameG = g.getName();
			String urlNameG = AsciiToHex.asciiToHex(nameG);
			groupsUrl.put(nameG, urlNameG);
		}

		return groupsUrl;
	}

	/**
	 * @param session
	 *            {@link HttpSession}
	 * @return All publications of the groups to which the user in session
	 *         belongs
	 */
	@ModelAttribute("allPublications")
	public List<Publication> getAllPublications(HttpSession session) {

		User userSession = (User) session.getAttribute("userSession");
		if (userSession == null)
			return null;
		User user = userService.findByLogin(userSession.getEmail());
		List<Publication> publications = new ArrayList<Publication>();

		for (Group group : user.getGroups())
			for (Category category : group.getCategories())
				for (Subcategory subcategory : category.getSubcategories())
					publications.addAll(subcategory.getPublications());

		Collections.sort(publications);

		return publications;
	}

	@RequestMapping(value = "/showProfile", method = RequestMethod.GET)
	public ModelAndView showProfile(
			@RequestParam(required = false) String userId,
			RedirectAttributes redirectAttributes, Model model) {

		String errorMessage = "Veuillez cliquer sur le nom de l'utilisateur pour afficher son profile !";

		ModelAndView result = new ModelAndView("workspace/showProfile");
		// test if no parameter in request
		if (userId == null) {
			redirectAttributes.addFlashAttribute("type", "error");
			redirectAttributes.addFlashAttribute("message", errorMessage);
			return new ModelAndView("redirect:/workspace/index.htm");
		}

		// test if userId not number
		Integer parameterValue = null;
		try {
			parameterValue = Integer.valueOf(userId);
		} catch (NumberFormatException ex) {
			redirectAttributes.addFlashAttribute("type", "error");
			redirectAttributes.addFlashAttribute("message", errorMessage);
			return new ModelAndView("redirect:/workspace/index.htm");
		}
		Student studentProfile = (Student) userService.find(parameterValue);

		// test if user doesn't exist in Database
		if (studentProfile == null) {

			redirectAttributes.addFlashAttribute("type", "error");
			redirectAttributes.addFlashAttribute("message", errorMessage);
			return new ModelAndView("redirect:/workspace/index.htm");

		}
		model.addAttribute("studentProfile", studentProfile);
		// Get all user hobbies
		String hobbies = "";
		List<UserHobby> hobbiesList = userHobbyService.findAll(parameterValue);

		if (hobbiesList.size() <= 0) {
			hobbies = "--";
		} else {
			hobbies += hobbiesList.get(0).getIdUserHobby().getNameH();
			for (int i = 1; i < hobbiesList.size(); i++) {
				hobbies += ", "
						+ hobbiesList.get(i).getIdUserHobby().getNameH();
			}
		}

		model.addAttribute("hobbies", hobbies);
		return result;
	}
}
