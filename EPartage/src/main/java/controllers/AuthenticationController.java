package controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.UserService;
import utilities.CryptPassword;
import domain.User;


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
		User user = new User();
		ModelAndView result;
		result = new ModelAndView("authentication/connection");
		model.addAttribute("user", user);
		return result;
	}
	
	/**
	 * User connection
	 */
	@RequestMapping(value = "/login")
	public ModelAndView login (
			@RequestParam(required = true) String email,
			@RequestParam(required = true) String password, 
			HttpSession session) {
		System.out.println("Controller : /AuthenticationController --- Action : /login");
		
		password = CryptPassword.getCryptString(password);
		
		User user = userService.findByLogin(email, password);
		
		if(user != null && !user.getStatus().equals("Waiting")){
			session.setAttribute( "userSession", user );
		} else {
			session.setAttribute( "userSession", null );
		}
	
		System.out.println(user.getFirstName());
		return new ModelAndView("welcome/index");
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
