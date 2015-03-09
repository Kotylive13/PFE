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

import domain.User;
import services.UserService;

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

	@RequestMapping(value = "/subscribe",  method = RequestMethod.GET)
	public ModelAndView subscribe(Model model) {
		System.out.println("Controller : /SubscriptionController --- Action : /subscribe"); 
		User user = new User();
		ModelAndView result;
		result = new ModelAndView("subscription/subscribe");
		model.addAttribute("user", user);
		return result;
	}
	
	@RequestMapping(value = "/subscribe",  method = RequestMethod.POST)
	public ModelAndView subscribePost(@Valid @ModelAttribute User user, BindingResult bindingResult) {
		System.out.println("Controller : /SubscriptionController --- Action : /subscribePost"); 
		
		ModelAndView result;
		result = new ModelAndView("subscription/subscribe");
		
		if (bindingResult.hasErrors()) {
			System.out.println("Erreurs   :" + bindingResult.getAllErrors());
			result.addObject("user", user);
		}else{
			user.setInscriptAppDate(new Date());
			user.setStatus("Waiting");
			userService.save(user);
			result = new ModelAndView("welcome/index");
		}
		
		return result;
	}

}
