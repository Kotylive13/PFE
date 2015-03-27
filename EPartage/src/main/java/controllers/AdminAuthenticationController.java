package controllers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import services.AdminService;
import services.UserService;
import utilities.CryptPassword;
import domain.Admin;

@Controller
@RequestMapping("/login_staff/authentication")
public class AdminAuthenticationController extends GlobalExceptionHandler {

	@Autowired
	AdminService adminService;

	@Autowired
	UserService userService;

	// Constructors -----------------------------------------------------------

	public AdminAuthenticationController() {
		super();
	}

	@RequestMapping(value = "/connection")
	public ModelAndView connection(Model model) {
		//System.out.println("Controller : /AdminController --- Action : /connection");
		Admin admin = new Admin();
		ModelAndView result;
		result = new ModelAndView("login_staff/authentication/connection");
		model.addAttribute("admin", admin);
		return result;
	}
	
	/**
	 * Admin connection
	 */
	@RequestMapping(value = "/login")
	public ModelAndView login (
			@RequestParam(required = true) String login,
			@RequestParam(required = true) String password, 
			RedirectAttributes redirectAttributes,
			HttpSession session,
			Model model) {
		
		//System.out.println("Controller : /AdminController --- Action : /login");
		
		Map<String, Object> message = new HashMap<String, Object>();

		ModelAndView result = new ModelAndView ("redirect:/login_staff/index.htm");
		
		password = CryptPassword.getCryptString(password);

		Admin adminSession = adminService.findByLogin(login, password);
		
		if(adminSession != null){
			session.setAttribute( "adminSession", adminSession );
			model.addAttribute("admin", adminSession);
		} else {
			message.put("type", "error");
			message.put("message",
					"Le login ou le mot de passe n'est pas correct.");
			Admin admin = new Admin();
			result = new ModelAndView("/login_staff/authentication/connection", message);
			model.addAttribute("admin", admin);
			return result;
		}
		
		redirectAttributes.addFlashAttribute("type", "success");
		redirectAttributes.addFlashAttribute("message", "Connexion réussie");
		
		return result;
	}

	
	/**
	 * Admin logout
	 */
	@RequestMapping(value = "/logout")
	public ModelAndView logoutForm (HttpSession session, Model model) {
		session.invalidate();
		return new ModelAndView("redirect:/login_staff/authentication/connection.htm");
	}
	
	@ModelAttribute("nbWaitingUsers")
	public int nbWaitingUsers () {
		return userService.nbWaitingUsers();
	}
}
