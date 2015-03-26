package controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import services.CategoryService;
import services.GroupService;
import services.PublicationService;
import services.SubcategoryService;
import services.UserService;
import utilities.AsciiToHex;
import domain.Category;
import domain.Group;
import domain.IdSubcategory;
import domain.Subcategory;

@Controller
@RequestMapping("/login_staff/subcategory")
public class AdminSubcategoryController extends GlobalExceptionHandler {

	@Autowired
	SubcategoryService subcategoryService;

	@Autowired
	PublicationService publicationService;
	
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
			@RequestParam(value = "groupname") String group,
			@RequestParam(value = "category") String cat,
			@Valid @ModelAttribute(value = "subcategory") Subcategory subcategory,
			BindingResult bindingResult) {

		if (session.getAttribute("adminSession") == null) {
			System.out.println("Error Admin Session is Null");
			return new ModelAndView("/authentication/connection");
		}
		model.addAttribute("admin", session.getAttribute("adminSession"));
		
		String groupname = AsciiToHex.decode(group);
		String category = AsciiToHex.decode(cat);

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
		
		if (subcategoryService.findOne(idSubcategory) != null) {		
			ModelAndView result = new ModelAndView("login_staff/subcategory/addSubcategory");
			result.addObject("errorName", "Une sous-catégorie porte déjà ce nom");
			return result;
		}
		
		subcategoryService.save(subcategory);

		Map<String, Object> mapSubcategory = new HashMap<String, Object>();
		mapSubcategory.put("groupname", category);
		mapSubcategory.put("groupcategory", groupname);
		mapSubcategory.put("listSubcategory", subcategoryService.findByGroupAndCategory(groupname, category));
		return new ModelAndView("/login_staff/subcategory/listSubcategory", mapSubcategory);
	}
	
	
// MANAGEMENT GROUP ---------------------------------------------------------------

	@RequestMapping(value = "/managementSubcategory")
	public ModelAndView managementGroup(
			@RequestParam String action,
			@RequestParam (value = "subcategory") String subcat,
			@RequestParam (value = "category") String cat,
			@RequestParam (value = "group") String groupname,
			HttpSession session, 
			Model model) {

		if(session.getAttribute("adminSession") == null) {
			System.out.println("Error Admin Session is Null");
			return new ModelAndView("/authentication/connection");
		}
		model.addAttribute("admin", session.getAttribute("adminSession"));
		
		String subcategory = AsciiToHex.decode(subcat);
		String category = AsciiToHex.decode(cat);
		String group = AsciiToHex.decode(groupname);

		Map<String, Object> mapSubcategory = new HashMap<String, Object>();
		
		if (action.equals("Supprimer")) {
			IdSubcategory idSubcategory = new IdSubcategory();
			idSubcategory.setCategory(category);
			idSubcategory.setGroup(group);
			idSubcategory.setSubcategory(subcategory);
			Subcategory deleteSubcategory = categoryService.findOne(idSubcategory);
			subcategoryService.delete(deleteSubcategory);
		} 
		else {
			System.out.println("Error action not exist");
		}

		List<Subcategory> listSubcategory = 
				subcategoryService.findByGroupAndCategory(group, category);
		
		mapSubcategory.put("groupname", category);
		mapSubcategory.put("groupcategory", group);
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

	/**
	 * @param session
	 *            {@link HttpSession}
	 * @return Map with groups name and corresponding encoded groups name
	 */
	@ModelAttribute("groupsUrl")
	public Map<String, Object> getGroupsUrl(HttpSession session) {
		Map<String, Object> groupsUrl = new HashMap<String, Object>();

		for (Group g : groupService.findAll()) {
			String nameG = g.getName();
			String urlNameG = AsciiToHex.asciiToHex(nameG);
			groupsUrl.put(nameG, urlNameG);
		}

		return groupsUrl;
	}
}
