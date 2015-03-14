package controllers;

import java.util.Date;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.HobbyService;
import services.UserService;
import utilities.MailSender;
import domain.Status;
import domain.Student;

/**
 * Class managing subscription page actions
 * 
 * @author
 */
@Controller
@RequestMapping("/subscription")
public class SubscriptionController {

	@Autowired
	UserService userService;
	@Autowired
	HobbyService hobbyService;

	// Constructors -----------------------------------------------------------

	public SubscriptionController() {
		super();
	}

	@RequestMapping(value = "/subscribe", method = RequestMethod.GET)
	public ModelAndView subscribe(Model model) {
		System.out.println("Controller : /SubscriptionController --- Action : /subscribe");
		ModelAndView result = new ModelAndView("subscription/subscribe");
		model.addAttribute("student", new Student());
		return result;
	}

	@RequestMapping(value = "/subscribe", method = RequestMethod.POST)
	public ModelAndView subscribePost(@Valid @ModelAttribute Student student ,BindingResult bindingResult) {
		System.out
				.println("Controller : /SubscriptionController --- Action : /subscribePost");

		ModelAndView result;
		result = new ModelAndView("subscription/subscribe");
		// Validation du model
		if (bindingResult.hasErrors()) {
			return result;
		}
		student.setInscriptAppDate(new Date());
		student.setStatus(Status.W);
		
		// store hobbies not implemented
		//...
		// send email to this person
		MailSender
				.sendEmail(
						student.getEmail(),
						"Demande d'inscription à la plateforme collaborative e-Partage",
						"Chèr(e) "
								+ student.getFirstName()
								+ ",\n"
								+ "Votre demande d'inscription à la plateforme collaborative e-Partage a bien été prise en compte.\n\n"
								+ "La validation de celle-ci vous sera communiquer par mail d'ici quelques jours.\n\n"
								+ "A bientôt sur e-Partage !");

		// show message "Votre demande d'inscription est en cours de validation"
		userService.save(student);
		result = new ModelAndView("welcome/index");
		return result;

	}
}
