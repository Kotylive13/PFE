package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import services.AdminService;

@Controller
@RequestMapping("/login_staff")
public class AdminController {
	
	@Autowired
	AdminService adminService;
	
// Constructors ---------------------------------------------------------------

	public AdminController() {
		super();
	}

// Index ----------------------------------------------------------------------
	
	@RequestMapping(value = "/index")
	public ModelAndView index() {
		System.out.println("Controller : /login_staff --- Action : /index");
		ModelAndView result;
		result = new ModelAndView("login_staff/index");
		return result;
	}
					
}
