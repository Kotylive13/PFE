package controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CategoryService;
import services.GroupService;
import services.SubcategoryService;
import services.UserService;
import domain.Category;
import domain.IdSubcategory;
import domain.Subcategory;

@Controller
@RequestMapping("/login_staff/subcategory")
public class AdminSubcategoryController {

	@Autowired
	SubcategoryService subcategoryService;
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	GroupService groupService;
	
	@Autowired
	UserService userService;
	
	// Constructors
	// ---------------------------------------------------------------

	public AdminSubcategoryController() {
		super();
	}

	// ADD CATEGORY
	// ---------------------------------------------------------------

	@RequestMapping(value = "/addSubcategory", method = RequestMethod.GET)
	public ModelAndView addGroup(HttpSession session, Model model) {

		if (session.getAttribute("adminSession") == null) {
			System.out.println("Error Admin Session is Null");
			return new ModelAndView("/authentication/connection");
		}
		model.addAttribute("admin", session.getAttribute("adminSession"));

		System.out
				.println("Controller : /AdminCategory --- Action : /addCategory GET");

		model.addAttribute("category", new Category());
		return new ModelAndView("/login_staff/category/addCategory");
	}

	@RequestMapping(value = "/addSubcategory", method = RequestMethod.POST)
	public ModelAndView addGroupForm(HttpSession session, Model model,
			@RequestParam(value = "groupname") String groupname,
			@RequestParam(value = "category") String category,
			@ModelAttribute(value = "subcategory") Subcategory subcategory,
			BindingResult bindingResult) {

		if (session.getAttribute("adminSession") == null) {
			System.out.println("Error Admin Session is Null");
			return new ModelAndView("/authentication/connection");
		}
		model.addAttribute("admin", session.getAttribute("adminSession"));

		System.out
				.println("Controller : /AdminCategory --- Action : /addCategory POST");
		System.out.println(category + " : catégorie " + groupname + " : groupName");
		subcategory.setCategory(categoryService.findByNameAndGroup(category, groupname));
		subcategory.setGroup(groupService.findGroupByName(groupname));
		IdSubcategory idSubcategory = new IdSubcategory();
		idSubcategory.setCategory(category);
		idSubcategory.setGroup(groupname);
		idSubcategory.setSubcategory(subcategory.getIdSubcategory().getSubcategory());
		subcategory.setIdSubcategory(idSubcategory);
		subcategoryService.save(subcategory);

		Map<String, List<Subcategory>> mapSubcategory = new HashMap<String, List<Subcategory>>();
		mapSubcategory.put("listSubcategory", subcategoryService.findByGroupAndCategory(groupname, category));
		return new ModelAndView("/login_staff/subcategory/listSubcategory", mapSubcategory);
	}
	
	
// MANAGEMENT GROUP ---------------------------------------------------------------

	@RequestMapping(value = "/managementSubcategory")
	public ModelAndView managementGroup(
			@RequestParam String action,
			@RequestParam (value = "subcategory") String subcategory,
			@RequestParam (value = "category") String category,
			@RequestParam (value = "group") String group,
			HttpSession session, 
			Model model) {

		if(session.getAttribute("adminSession") == null) {
			System.out.println("Error Admin Session is Null");
			return new ModelAndView("/authentication/connection");
		}
		model.addAttribute("admin", session.getAttribute("adminSession"));

		Map<String, List<Subcategory>> mapSubcategory = new HashMap<String, List<Subcategory>>();
		
		if (action.equals("Supprimer")) {
			IdSubcategory idSubcategory = new IdSubcategory();
			idSubcategory.setCategory(category);
			idSubcategory.setGroup(group);
			idSubcategory.setSubcategory(subcategory);
			Subcategory deleteSubcategory = subcategoryService.findOne(idSubcategory);
			subcategoryService.delete(deleteSubcategory);
		} 
		else {
			System.out.println("Error action not exist");
		}

		List<Subcategory> listSubcategory = 
				subcategoryService.findByGroupAndCategory(group, category);
		
		mapSubcategory.put("listSubcategory", listSubcategory);
		
		return new ModelAndView("login_staff/subcategory/listSubcategory", mapSubcategory);
	}
	
//	@ModelAttribute("subcategory")
//	public Subcategory newSubcategory(
//			@RequestParam(value = "subcategory", required = false) String subcategory,
//			@RequestParam(value = "group", required = false) String group,
//			@RequestParam(value = "category", required = false) String category) {
//		
//		if (subcategory != null && group != null && category != null) {
//			IdSubcategory idSubcategory = new IdSubcategory();
//			idSubcategory.setGroup(group);
//			idSubcategory.setSubcategory(subcategory);
//			idSubcategory.setCategory(category);
//			return subcategoryService.findOne(idSubcategory);
//		}
//
//		return new Subcategory();
//	}
	
	@ModelAttribute("nbWaitingUsers")
	public int nbWaitingUsers () {
		return userService.nbWaitingUsers();
	}

}
