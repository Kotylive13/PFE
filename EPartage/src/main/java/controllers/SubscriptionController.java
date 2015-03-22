package controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
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

import services.HobbyService;
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
	ServletContext context;

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
		Map<String, Object> hobbies = new HashMap<String, Object>();
		hobbies.put("hobbies", hobbyService.findAll());
		ModelAndView result = new ModelAndView("subscription/subscribe", hobbies);
		model.addAttribute("student", new Student());
		return result;
	}

	@RequestMapping(value = "/subscribe", method = RequestMethod.POST)
	public ModelAndView subscribePost(@Valid @ModelAttribute Student student ,
			@RequestParam(required = false) MultipartFile file,
			BindingResult bindingResult, 
			HttpServletRequest request) {
		System.out
				.println("Controller : /SubscriptionController --- Action : /subscribePost");

		Map<String, Object> allHobbies = new HashMap<String, Object>();
		allHobbies.put("hobbies", hobbyService.findAll());
		ModelAndView result = new ModelAndView("subscription/subscribe", allHobbies);
		
		// Validation du model
		if (bindingResult.hasErrors()) {
			return result;
		}
		if (!request.getParameter("confirmation").equals(student.getPassword()))
			return result.addObject("errorConfirmation",
					"Les mots de passe ne concordent pas");
		
		student.setInscriptAppDate(new Date());
		student.setStatus(Status.W);
		
		String input = request.getParameter("hobbies");
		input = input.replace(" ", "");
		String[] userHobbies = input.split(",");
		
		if (userHobbies[0].isEmpty())
			return result.addObject("errorHobbies",
					"Veuillez renseigner des centres d'intérêt");
		
		Set<Hobby> hobbies = new HashSet<Hobby>();		
		for (String userHobby : userHobbies) {				
			Hobby hobby = hobbyService.find(userHobby);
			if(hobby == null) {
				hobby = new Hobby(userHobby);
				hobby = hobbyService.save(hobby);
			}				
			hobbies.add(hobby);
		}
		student.setHobbies(hobbies);
		
		// save file
		try {
			if (!file.isEmpty()) {
					student.setAvatar(file.getBytes());
			}else{
				InputStream is = context.getResourceAsStream("/images/user.png");
				student.setAvatar(IOUtils.toByteArray(is));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		userService.save(student);
		// send email to this person
		MailSender
				.sendEmail(
						student.getEmail(),
						"Demande d'inscription à la plateforme collaborative e-Partage",
						"Cher(e) "
								+ student.getFirstName()
								+ ",\n"
								+ "Votre demande d'inscription à la plateforme collaborative e-Partage a bien été prise en compte.\n\n"
								+ "La validation de celle-ci vous sera communiquer par mail d'ici quelques jours.\n\n"
								+ "A bientôt sur e-Partage !");
		
		result = new ModelAndView("authentication/connection");		
		result.addObject("type", "success");
		result.addObject("message", "Votre demande d'inscription est en cours de validation.");
		return result;
	}
	
	@ModelAttribute("hobbiesList")
	public Collection<Hobby> getCategories() {
		return hobbyService.findAll();
	}
	
	
	/*@ModelAttribute
	public Student newStudent(){
		Student newStudent = new Student();
		newStudent.setInscriptAppDate(new Date());
		newStudent.setStatus(Status.W);
		return newStudent;
	}*/
}
