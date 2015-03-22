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

import services.CategoryService;
import services.GroupService;
import services.SubcategoryService;
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
			@Valid @ModelAttribute Category category, BindingResult bindingResult){
		
		if(session.getAttribute("adminSession") == null) {
			System.out.println("Error Admin Session is Null");
			return new ModelAndView("/authentication/connection");
		}
		model.addAttribute("admin", session.getAttribute("adminSession"));

		System.out.println("Controller : /AdminCategory --- Action : /addCategory POST");
		
		category.setGroup(groupService.findGroupByName(category.getIdCategory().getGroup()));

		categoryService.save(category);
		
		model.addAttribute("type", "success");
		model.addAttribute("message", "La catégorie a bien été ajoutée");
		
		return new ModelAndView("/login_staff/category/listCategory");
		
		//return new ModelAndView("/login_staff/index"); Ce qu'il y avait de base !!!!!
	}
	
// MANAGEMENT CATEGORY --------------------------------------------------------

	@RequestMapping(value = "/managementCategory")
	public ModelAndView managementCategory(
			@RequestParam(value="nameCategory") String nameCategory ,
			@RequestParam(value="groupCategory") String groupCategory ,
			@RequestParam String action, 
			HttpSession session, 
			Model model) {
		
		if(session.getAttribute("adminSession") == null) {
			System.out.println("Error Admin Session is Null");
			return new ModelAndView("/authentication/connection");
		}
		model.addAttribute("admin", session.getAttribute("adminSession"));
		
		
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
			
			Map<String, List<Subcategory>> mapSubcategory = new HashMap<String, List<Subcategory>>();
			List<Subcategory> listSubcategory = subcategoryService.findByGroupAndCategory(groupCategory, nameCategory);
			
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
	
}
