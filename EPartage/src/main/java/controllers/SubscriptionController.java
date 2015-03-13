package controllers;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.UserService;
import utilities.MailSender;
import domain.Hobby;
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

	// Constructors -----------------------------------------------------------

	public SubscriptionController() {
		super();
	}

	@RequestMapping(value = "/subscribe", method = RequestMethod.GET)
	public ModelAndView subscribe(Model model) {
		System.out
				.println("Controller : /SubscriptionController --- Action : /subscribe");
		Student student = new Student();
		Set<Hobby> studentHobbies = new HashSet<Hobby>();
		ModelAndView result;
		result = new ModelAndView("subscription/subscribe");
		model.addAttribute("student", student);
		model.addAttribute("hobbies", studentHobbies );
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
		
		// store hobbies
		/*Set<Hobby> studentHobbies = new HashSet<Hobby>();
		String[] arrayHobbies = null;
		if (hobbies != null) {
			if (!hobbies.isEmpty()) {
				arrayHobbies = hobbies.split(",");
			}
			for (int i = 0; i < arrayHobbies.length; i++) {
				studentHobbies.add(new Hobby(arrayHobbies[i]));
			}

		}*/
		//student.setHobbys(studentHobbies);
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
		//if(!userService.exists(student))
		userService.save(student);
		result = new ModelAndView("welcome/index");
		return result;

	}

	/*
	 * @ModelAttribute("months") public int[] getMonths() { int[] days = { 1, 2,
	 * 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 }; return days; }
	 * 
	 * @ModelAttribute("days") public int[] getDays() { int[] days = new
	 * int[31]; for (int i = 0; i < days.length; i++) { days[i] = i + 1; }
	 * return days; }
	 * 
	 * @ModelAttribute("years") public int[] getYears() { // Calendar cal =
	 * Calendar.getInstance(); // cal.setTime(new Date()); int currentYear =
	 * Calendar.getInstance().get(Calendar.YEAR) + 1; int[] years = new
	 * int[currentYear - 1900];
	 * 
	 * int i = 1900; for (int j = 0; j < years.length; j++) { years[j] = i; i++;
	 * } return years; }
	 */

}
