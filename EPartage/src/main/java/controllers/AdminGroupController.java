package controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.GroupService;
import domain.Admin;
import domain.Group;

@Controller
@RequestMapping("/login_staff/group")
public class AdminGroupController {
	
	@Autowired
	GroupService groupService;


// Constructors ---------------------------------------------------------------

	public AdminGroupController() {
		super();
	}


// ADD GROUP ------------------------------------------------------------------
	
	@RequestMapping(value = "/addGroup", method = RequestMethod.GET)
	public ModelAndView addGroup(HttpSession session, Model model) {
		System.out.println("Controller : /AdminGroupController --- Action : /addGroup GET");
		Group group = new Group();
		Admin admin = (Admin) session.getAttribute("adminSession");
		ModelAndView result = new ModelAndView("/login_staff/group/addGroup");
		model.addAttribute("group", group);
		model.addAttribute("admin", admin);
		return result;
	}
	
	@RequestMapping(value = "/addGroup", method = RequestMethod.POST)
	public ModelAndView addGroupForm(HttpSession session, Model model, @ModelAttribute Group group){
		System.out.println("Controller : /AdminGroupController --- Action : /addGroup POST");
		ModelAndView result;
		Admin admin = (Admin) session.getAttribute("adminSession");
		result = new ModelAndView("/login_staff/group/addGroup");
		if(group == null ) System.out.println("Le groupe est null");
		System.out.println("Nom du groupe : " + group.getName());
		System.out.println("Nom du Description : " + group.getDescription());
		groupService.save(group);
		model.addAttribute("admin", admin);
		return result;
	}
	
// LIST GROUP -----------------------------------------------------------------
	
	@RequestMapping(value = "/listGroup")
	public ModelAndView listWaiting(HttpSession session, Model model) {
		System.out.println("Controller : /AdminGroupController --- Action : /listgroup");
		Map<String, List<Group>> mapGroup = new HashMap<String, List<Group>>();
		List<Group> listGroups = (List<Group>) groupService.findAll();
		mapGroup.put("listGroups", listGroups);
		model.addAttribute("admin", session.getAttribute("adminSession"));
		return new ModelAndView("login_staff/group/listGroup", mapGroup);
	}

}
