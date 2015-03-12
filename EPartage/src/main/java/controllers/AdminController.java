package controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.AdminService;
import utilities.CryptPassword;
import domain.Admin;

@Controller
@RequestMapping("/login_staff")
public class AdminController {
	
	@Autowired
	AdminService adminService;
	
	// Constructors -----------------------------------------------------------

	public AdminController() {
		super();
	}

	// Index 
	
	@RequestMapping(value = "/index")
	public ModelAndView index() {
		System.out.println("Controller : /login_staff --- Action : /index");
		ModelAndView result;
		result = new ModelAndView("login_staff/index");
		return result;
	}

	// Connection ------------------------------------------------------------------

		@RequestMapping(value = "/connection")
		public ModelAndView connection(Model model) {
			System.out.println("Controller : /AdminController --- Action : /connection");
			Admin admin = new Admin();
			ModelAndView result;
			result = new ModelAndView("login_staff/connection");
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
				HttpSession session) {
			System.out.println("Controller : /AdminController --- Action : /login");
			
			password = CryptPassword.getCryptString(password);
			
			Admin admin = adminService.findByLogin(login, password);
			
			if(admin != null){
				session.setAttribute( "adminSession", admin );
			} else {
				session.setAttribute( "adminSession", null );
			}
		
			return new ModelAndView("login_staff/index");
		}
		
		
		/**
		 * Admin logout
		 */
		@RequestMapping(value = "/logout")
		public ModelAndView logoutForm (HttpSession session) {
			session.invalidate();
			return new ModelAndView("login_staff/index");
		}

}
