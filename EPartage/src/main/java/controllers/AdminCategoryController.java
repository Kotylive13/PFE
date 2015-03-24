package controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import services.CategoryService;
import services.GroupService;
import services.SubcategoryService;
import services.UserService;
import utilities.AsciiToHex;
import domain.Category;
import domain.Group;
import domain.IdCategory;
import domain.Subcategory;

@Controller
@RequestMapping("/login_staff/category")
public class AdminCategoryController {
	

	@Autowired
	GroupService groupService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	SubcategoryService subcategoryService;
	

// Constructors ---------------------------------------------------------------

	public AdminCategoryController() {
		super();
	}


// ADD CATEGORY ---------------------------------------------------------------
	
	@RequestMapping(value = "/addCategory", method = RequestMethod.GET)
	public ModelAndView addGroup(HttpSession session, Model model) {
		
		if(session.getAttribute("adminSession") == null) {
			System.out.println("Error Admin Session is Null");
			return new ModelAndView("/authentication/connection");
		}
		model.addAttribute("admin", session.getAttribute("adminSession"));
		
		System.out.println("Controller : /AdminCategory --- Action : /addCategory GET");
		
		model.addAttribute("category", new Category());
		return new ModelAndView("/login_staff/category/addCategory");
	}
	
	@RequestMapping(value = "/addCategory", method = RequestMethod.POST)
	public ModelAndView addGroupForm(HttpSession session, Model model, 
			@Valid @ModelAttribute Category category, 
			RedirectAttributes redirectAttributes,
			BindingResult bindingResult){
		
		if(session.getAttribute("adminSession") == null) {
			System.out.println("Error Admin Session is Null");
			return new ModelAndView("/authentication/connection");
		}
		model.addAttribute("admin", session.getAttribute("adminSession"));

		System.out.println("Controller : /AdminCategory --- Action : /addCategory POST");
		
		category.setGroup(groupService.findGroupByName(category.getIdCategory().getGroup()));
		
		if (categoryService.findByNameAndGroup(category.getIdCategory().getName(), category.getGroup().getName()) != null) {			
			ModelAndView result = new ModelAndView("/login_staff/category/addCategory");
			result.addObject("errorName", "Une catégorie de ce groupe porte déjà ce nom");
			return result;
		}

		categoryService.save(category);
		
		redirectAttributes.addFlashAttribute("type", "success");
		redirectAttributes.addFlashAttribute("message", "La catégorie a bien été ajoutée");
		return new ModelAndView("redirect:/login_staff/category/listCategory.htm");
	}
	
// MANAGEMENT CATEGORY --------------------------------------------------------

	@RequestMapping(value = "/managementCategory")
	public ModelAndView managementCategory(
			@RequestParam(value="nameCategory") String nameC ,
			@RequestParam(value="groupCategory") String groupC ,
			@RequestParam String action, 
			HttpSession session, 
			Model model) {
		
		if(session.getAttribute("adminSession") == null) {
			System.out.println("Error Admin Session is Null");
			return new ModelAndView("/authentication/connection");
		}
		model.addAttribute("admin", session.getAttribute("adminSession"));
		
		String nameCategory = AsciiToHex.decode(nameC);
		String groupCategory = AsciiToHex.decode(groupC);		
		
		Map<String, List<Category>> mapCategory = new HashMap<String, List<Category>>();
		List<Category> listCategory = new ArrayList <Category>();
		
//		Map<String, Category> mapCategoryModify = new HashMap<String, Category>();

		Category category = categoryService.findByNameAndGroup(nameCategory, groupCategory);

		if (action.equals("Supprimer")) {
			categoryService.delete(category);
		} 
		else if (action.equals("Ajouter sous-categorie")) {
			
			Map<String, String> mapGroupCategory = new HashMap<String, String>();
			
			mapGroupCategory.put("group", groupCategory);
			mapGroupCategory.put("category", nameCategory);
			
			return new ModelAndView("login_staff/subcategory/addSubcategory", mapGroupCategory);
		}
		else if (action.equals("Voir les sous-categories")){
			//TODO
			
			Map<String, Object> mapSubcategory = new HashMap<String, Object>();
			List<Subcategory> listSubcategory = subcategoryService.findByGroupAndCategory(groupCategory, nameCategory);
			
			mapSubcategory.put("groupname", nameCategory);
			mapSubcategory.put("groupcategory", groupCategory);
			mapSubcategory.put("listSubcategory", listSubcategory);
			
			return new ModelAndView("login_staff/subcategory/listSubcategory", mapSubcategory);
		}
		else {
			System.out.println("Error action not exist");
		}
		
		listCategory = (List<Category>) categoryService.findAll();
		mapCategory.put("listCategory", listCategory);
		
		return new ModelAndView("login_staff/category/listCategory", mapCategory);
	}
	
	
	
// LIST CATEGORY --------------------------------------------------------------

	@RequestMapping(value = "/listCategory")
	public ModelAndView listCategory(HttpSession session, Model model) {
		
		if(session.getAttribute("adminSession") == null) {
			System.out.println("Error Admin Session is Null");
			return new ModelAndView("/authentication/connection");
		}
		model.addAttribute("admin", session.getAttribute("adminSession"));
		
		System.out.println("Controller : /AdminGroupController --- Action : /listgroup");
		
		Map<String, List<Category>> mapCategory = new HashMap<String, List<Category>>();
		List<Category> listCategory = (List<Category>) categoryService.findAll();
		mapCategory.put("listCategory", listCategory);
		
		return new ModelAndView("login_staff/category/listCategory", mapCategory);
	}
	
	
// ModelAttribute -------------------------------------------------------------

	@ModelAttribute("groupMap")
	public Map<String, String> getGroups() {
		Map<String,String> groupMap = new LinkedHashMap<String,String>();
		List<Group> groupList = groupService.findAll();
		
		for (Group group : groupList) {
			groupMap.put(group.getName(), group.getName());
		}
		return groupMap;
	}
	
	@ModelAttribute("category")
	public Category newCategory(
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "group", required = false) String group) {
		
		if (name != null && group != null) {
			IdCategory idCategory = new IdCategory();
			idCategory.setGroup(group);
			idCategory.setName(name);
			Category category = new Category();
			category.setIdCategory(idCategory);
			return categoryService.findOne(idCategory);
		}

		return new Category();
	}
	
	@ModelAttribute("subcategory")
	public Subcategory newSubcategory() {
		return new Subcategory();
	}
	
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
			
			for (Category cat : groupService.findGroupByName(nameG).getCategories()) {

				String nameC = cat.getIdCategory().getName();
				String urlNameC = AsciiToHex.asciiToHex(nameC);
				groupsUrl.put(nameC, urlNameC);

				for (Subcategory s : cat.getSubcategories()) {

					String nameS = s.getIdSubcategory().getSubcategory();
					String urlNameS = AsciiToHex.asciiToHex(nameS);
					groupsUrl.put(nameS, urlNameS);
				}
			}
		}

		return groupsUrl;
	}
	
}
