package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import services.UserService;
import domain.User;

@Controller
@RequestMapping("/user")
public class UserController extends AbstractController {

	public UserController() {
		super();
	}

	@Autowired
	UserService UserService;

	/**
	 * List all users
	 * 
	 * @return the jsp view of users
	 */
	@RequestMapping("/list")
	public ModelAndView allUsers() {
		ModelAndView result;
		Collection<User> users = UserService.findAll();
		result = new ModelAndView("user/list");
		System.out.println(users.size());
		result.addObject("users", users);
		return result;
	}

}