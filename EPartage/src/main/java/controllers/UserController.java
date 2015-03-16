package controllers;

import java.util.Collection;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import services.GroupService;
import services.UserService;
import domain.Group;
import domain.Student;
import domain.User;

@Controller
@RequestMapping("/workspace")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	GroupService groupService;

	public UserController() {
		super();
	}

	@RequestMapping("/index.htm")
	public String index() {
//		User user = userService.findByLogin("yoann.m@gmail.com");
//		Set<Group> groups = new HashSet<Group>();
//		Group group = groupService.findGroupByName("M2_ISL");
//		groups.add(group);
//		user.setGroups(groups);
//		
//		userService.save(user);
		
		return "workspace/workspace";
	}
	
	@ModelAttribute("groupsList")
	public Collection<Group> getUserGroups(HttpSession session) {
		return ((User) session.getAttribute("userSession")).getGroups();
	}
	
	@ModelAttribute("student")
	public Student getStudent (HttpSession session) {
		return (Student) session.getAttribute("userSession");
	}

}