package controllers;

import java.util.Collection;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.UserService;
import domain.User;

@Controller
@RequestMapping("/user")
public class UserController {
	

	@Autowired
	UserService userService;

	public UserController() {
		super();
	}


	/**
	 * List all users 
	 * 
	 * @return the jsp view of users
	 */
	@RequestMapping("/list")
	public ModelAndView allUsers() {
		System.out.println("Controller : /UserController --- Action : /list");
		ModelAndView result;
		Collection<User> users = userService.findAll();
		result = new ModelAndView("user/list");
		System.out.println(users.size());
		result.addObject("users", users);
		return result;
	}

}