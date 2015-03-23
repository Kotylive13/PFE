package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;

import services.GroupService;
import utilities.AsciiToHex;
import domain.Group;

@Controller
@RequestMapping("/login_staff/group")
public class AdminGroupController {

	@Autowired
	GroupService groupService;

	// @Autowired
	// MembershipGroupService membershipGroupService;

	// Constructors
	// ---------------------------------------------------------------

	public AdminGroupController() {
		super();
	}

	// ADD GROUP
	// ------------------------------------------------------------------

	@RequestMapping(value = "/addGroup", method = RequestMethod.GET)
	public ModelAndView addGroup(HttpSession session, Model model) {
		if (session.getAttribute("adminSession") == null) {
			System.out.println("Error Admin Session is Null");
			return new ModelAndView("/authentication/connection");
		}
		model.addAttribute("admin", session.getAttribute("adminSession"));
		System.out
				.println("Controller : /AdminGroupController --- Action : /addGroup GET");
		model.addAttribute("group", new Group());

		return new ModelAndView("/login_staff/group/addGroup");
	}

	@RequestMapping(value = "/addGroup", method = RequestMethod.POST)
	public ModelAndView addGroupForm(HttpSession session, Model model,
			@ModelAttribute Group group) {
		if (session.getAttribute("adminSession") == null) {
			System.out.println("Error Admin Session is Null");
			return new ModelAndView("/authentication/connection");
		}
		model.addAttribute("admin", session.getAttribute("adminSession"));

		System.out
				.println("Controller : /AdminGroupController --- Action : /addGroup POST");

		Map<String, List<Group>> mapGroup = new HashMap<String, List<Group>>();
		List<Group> listGroups = new ArrayList<Group>();
//		if (file == null)
//			System.out.println("coucouuuuuuu");
//		
//		if (file.isEmpty())
//			System.out.println("pistache");
//		// save file
//		if (!file.isEmpty()) {
//			try {
//				group.setAvatar(file.getBytes());
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		} else {
//			group.setAvatar(null);
//		}
		
		groupService.save(group);
		listGroups = (List<Group>) groupService.findAll();
		mapGroup.put("listGroups", listGroups);

		model.addAttribute("type", "success");
		model.addAttribute("message", "Le groupe a bien été ajouté");

		return new ModelAndView("/login_staff/group/listGroup", mapGroup);
	}

	// LIST GROUP
	// -----------------------------------------------------------------

	@RequestMapping(value = "/listGroup")
	public ModelAndView listWaiting(HttpSession session, Model model) {
		if (session.getAttribute("adminSession") == null) {
			System.out.println("Error Admin Session is Null");
			return new ModelAndView("/authentication/connection");
		}
		model.addAttribute("admin", session.getAttribute("adminSession"));
		System.out
				.println("Controller : /AdminGroupController --- Action : /listgroup");
		Map<String, List<Group>> mapGroup = new HashMap<String, List<Group>>();
		List<Group> listGroups = (List<Group>) groupService.findAll();
		mapGroup.put("listGroups", listGroups);
		return new ModelAndView("login_staff/group/listGroup", mapGroup);
	}

	// MANAGEMENT GROUP
	// ---------------------------------------------------------------

	@RequestMapping(value = "/managementGroup")
	public ModelAndView managementGroup(
			@RequestParam(value = "name") String name,
			@RequestParam String action, HttpSession session, Model model) {

		if (session.getAttribute("adminSession") == null) {
			System.out.println("Error Admin Session is Null");
			return new ModelAndView("/authentication/connection");
		}
		model.addAttribute("admin", session.getAttribute("adminSession"));

		Map<String, List<Group>> mapGroup = new HashMap<String, List<Group>>();
		List<Group> listGroup = new ArrayList<Group>();

		Map<String, Group> mapGroupModify = new HashMap<String, Group>();

		Group group = groupService.findGroupByName(name);

		if (action.equals("Supprimer")) {
			groupService.delete(group);
		} else if (action.equals("Modifier")) {
			mapGroupModify.put("group", group);
			return new ModelAndView("login_staff/group/modifyGroup",
					mapGroupModify);
		} else {
			System.out.println("Error action not exist");
		}

		listGroup = (List<Group>) groupService.findAll();
		mapGroup.put("listGroups", listGroup);

		model.addAttribute("type", "success");
		model.addAttribute("message", "Le groupe a bien été supprimé");

		return new ModelAndView("login_staff/group/listGroup", mapGroup);
	}

	// MODIFY GROUP
	// ---------------------------------------------------------------

	@RequestMapping(value = "/modifyGroup")
	public ModelAndView modifyGroup(@RequestParam(value = "name") String name,
			@ModelAttribute(value = "group") Group groupNew,
			HttpSession session, Model model) {

		if (session.getAttribute("adminSession") == null) {
			System.out.println("Error Admin Session is Null");
			return new ModelAndView("/authentication/connection");
		}
		model.addAttribute("admin", session.getAttribute("adminSession"));
		// TODO A
		// CHANGERRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR
		Map<String, List<Group>> mapGroup = new HashMap<String, List<Group>>();
		Map<String, Group> mapGroupModify = new HashMap<String, Group>();
		String[] listName = name.split(",");

		groupNew.setName(listName[1]);
		name = listName[0];

		if (!groupNew.getName().equals(name)
				&& groupService.findGroupByName(groupNew.getName()) != null) {
			Group group = groupService.findGroupByName(name);
			mapGroupModify.put("group", group);
			return new ModelAndView("login_staff/group/modifyGroup",
					mapGroupModify);
			// TODO FAIRE MESSAGE ERREUR
		}
		groupService.modify(groupNew, name);

		mapGroup.put("listGroups", (List<Group>) groupService.findAll());

		return new ModelAndView("login_staff/group/listGroup", mapGroup);
	}

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
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(byte[].class,
				new ByteArrayMultipartFileEditor());
	}
}
