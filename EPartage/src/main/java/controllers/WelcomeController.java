package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
	
	
}