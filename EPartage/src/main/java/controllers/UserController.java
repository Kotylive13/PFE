package controllers;

import java.util.Collection;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import services.CategoryService;
import services.GroupService;
import services.UserService;
import domain.Group;
import domain.IdSubcategory;
import domain.Student;
import domain.Subcategory;
import domain.User;

@Controller
@RequestMapping("/workspace")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	GroupService groupService;
	
	@Autowired
	CategoryService categoryService;

	public UserController() {
		super();
	}

	@RequestMapping("/index.htm")
	public String index() {
		return "workspace/workspace";
	}
	
	@RequestMapping(value = "/group/detail.htm", method = RequestMethod.GET)
	public String detailGroup(
			@ModelAttribute Group g,
			@ModelAttribute User u) {	
		
		if (g == null || g.getName().isEmpty())
			return "redirect:../index.htm";
		
		if (!u.getGroups().contains(g))
			return "redirect:../index.htm";

		return "group/group";
	}
	
	@RequestMapping(value = "/group/subcategory/detail.htm", method = RequestMethod.GET)
	public String detailSubcategory(
			@ModelAttribute Subcategory sub,
			@ModelAttribute User u) {
		
		if (sub == null || sub.getIdSubcategory().getSubcategory().isEmpty())
			return "redirect:../../index.htm";
	
		if (!u.getGroups().contains(sub.getGroup()))
			return "redirect:../../index.htm";

		return "group/subcategory";
	}
	
	@ModelAttribute("subcategory")
	public Subcategory newSubcategory(
			@RequestParam(value = "nameG", required = false) String nameG,
			@RequestParam(value = "nameC", required = false) String nameC,
			@RequestParam(value = "nameS", required = false) String nameS) {
		
		if (nameG != null && nameC != null && nameS != null) {
			IdSubcategory idSubcategory = new IdSubcategory();
			idSubcategory.setGroup(nameG);
			idSubcategory.setCategory(nameC);
			idSubcategory.setSubcategory(nameS);
			return categoryService.findOne(idSubcategory);
		}

		return new Subcategory();
	}
	
	@ModelAttribute("group")
	public Group newGroup(
			@RequestParam(value = "name", required = false) String name) {
		
		if (name != null) {
			return groupService.findGroupByName(name);
		}

		return new Group();
	}
	
	@ModelAttribute("groupsList")
	public Collection<Group> getUserGroups(HttpSession session) {
		return ((User) session.getAttribute("userSession")).getGroups();
	}
	
	@ModelAttribute("student")
	public Student getStudent (HttpSession session) {
		return (Student) session.getAttribute("userSession");
	}
	
	@ModelAttribute("user")
	public User getUser (HttpSession session) {
		return (User) session.getAttribute("userSession");
	}

}