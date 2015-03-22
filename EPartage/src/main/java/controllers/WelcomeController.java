package controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import utilities.MailSender;
import domain.Student;

/**
 * Class managing welcome page actions
 * 
 * @author 
 */
@Controller
@RequestMapping("/welcome")
public class WelcomeController {

	// Constructors -----------------------------------------------------------

	public WelcomeController() {
		super();
	}

	// Index ------------------------------------------------------------------

	@RequestMapping(value = "/index")
	public ModelAndView index() {
		System.out.println("Controller : /welcome --- Action : /index");
		ModelAndView result;
		result = new ModelAndView("welcome/index");
		return result;
	}
	
	/**
	 * Return the view corresponding to the contact form
	 * 
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/contact.htm",  method = RequestMethod.GET)
	public ModelAndView contact() {		
		ModelAndView result = new ModelAndView("welcome/contact");
		result.addObject("student", new Student());
		return result;
	}
	
	/**
	 * Allows to contact administrator
	 * Called when visitor sends contact form
	 * 
	 * @param object
	 * @param message Content
	 * @param session {@link HttpSession}
	 * @param redirectAttributes {@link RedirectAttributes}
	 * @return view with specific message
	 */
	@RequestMapping(value = "/contact.htm",  method = RequestMethod.POST)
	public ModelAndView contact(
			@RequestParam(required = true) String firstName,
			@RequestParam(required = true) String lastName,
			@RequestParam(required = true) String email,
			@RequestParam(required = true) String object,
			@RequestParam(required = true) String message,
			HttpSession session, RedirectAttributes redirectAttributes) {
		
		Map<String, Object> errors = new HashMap<String, Object>();
		
		if(firstName.length() < 2)
			errors.put("firstNameError", "Le prénom doit contenir plus de 2 caractères");
		if(lastName.length() < 2)
			errors.put("lastNameError", "Le nom doit contenir plus de 2 caractères");

		Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
								+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		Matcher matcher = pattern.matcher(email);
		
		if(!matcher.matches())			
			errors.put("emailError", "Ceci n'est pas une adresse email valide");
		if(object.length() < 2)
				errors.put("objectError", "L'objet doit contenir plus de 2 caractères");
		if(message.length() < 10)
			errors.put("messageError", "Le message doit contenir plus de 10 caractères");
		if(!errors.isEmpty()) {
			errors.put("firstNameOld", firstName);
			errors.put("lastNameOld", lastName);
			errors.put("emailOld", email);
			errors.put("objectOld", object);
			errors.put("messageOld", message);
			errors.put("student", new Student());
			return new ModelAndView("welcome/contact", errors);
		}
				
		MailSender
			.sendEmail(
				"luminy.annuaire@gmail.com",
				"e-Partage - Nouveau message de " + firstName + " " + lastName + " - Objet : " + object,
				"Message reçu de la part de " + firstName + " " + lastName + "\n"
						+ "Email : " + email + "\n\n\n"
						+ "Objet :" + object + "\n\n" 
						+ "Message : " + message);
		redirectAttributes.addFlashAttribute("type", "success");
		redirectAttributes.addFlashAttribute("message", "Votre message a été envoyé à l'administrateur.");
		return new ModelAndView("redirect:/authentication/connection.htm");
	}
}