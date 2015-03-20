package controllers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.UserService;
import utilities.CryptPassword;
import domain.Status;
import domain.Student;


/**
 * Class managing connection page's actions
 * 
 * @author 
 */
@Controller
@RequestMapping("/authentication")
public class AuthenticationController {

	

	@Autowired
	UserService userService;
	
	// Constructors -----------------------------------------------------------

	public AuthenticationController() {
		super();
	}

	// Connection ------------------------------------------------------------------

	@RequestMapping(value = "/connection")
	public ModelAndView connection(Model model) {
		System.out.println("Controller : /AuthenticationController --- Action : /connection");
		Student student = new Student();
		ModelAndView result;
		result = new ModelAndView("authentication/connection");
		model.addAttribute("student", student);
		return result;
	}
	
	/**
	 * User connection
	 */
	@RequestMapping(value = "/login")
	public ModelAndView login (
			@RequestParam(required = true) String email,
			@RequestParam(required = true) String password, 
			HttpSession session,
			Model model) {
		System.out.println("Controller : /AuthenticationController --- Action : /login");
		
		ModelAndView result;
		password = CryptPassword.getCryptString(password);
		Map<String, Object> message = new HashMap<String, Object>();
		Student studentSession = userService.findByEmailPass(email, password);
		if(studentSession != null){
			if(studentSession.getStatus().equals(Status.A)){
				session.setAttribute( "userSession", studentSession );
			}
		} else {
			session.setAttribute( "userSession", null );
			message.put("type", "error");
	    	message.put("message", "Le login ou le mot de passe n'est pas correct.");
	    	Student student = new Student();
			result = new ModelAndView("authentication/connection", message);
			model.addAttribute("student", student);
			return result;
		}
	
		System.out.println(studentSession.getFirstName());
		
		message.put("type", "success");
    	message.put("message", "Connexion réussie");
		result = new ModelAndView("redirect:/workspace/index.htm", message);

		return result;
	}

	/**
	 * User logout
	 */
	@RequestMapping(value = "/logout")
	public ModelAndView logoutForm (HttpSession session) {
		session.invalidate();
		return new ModelAndView("welcome/index");
	}

}
