package controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import services.GroupService;
import services.UserService;
import utilities.AsciiToHex;
import utilities.Final;
import domain.Group;

@Controller
@RequestMapping("/login_staff/group")
public class AdminGroupController extends GlobalExceptionHandler {
	
	@Autowired
	ServletContext context;

	@Autowired
	GroupService groupService;
	
	@Autowired
	UserService userService;

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
			@RequestParam(required = false) MultipartFile file,
			RedirectAttributes redirectAttributes,
			@Valid @ModelAttribute Group group,
			BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			ModelAndView result = new ModelAndView("/login_staff/group/addGroup");
			result.addObject("group", group);
			return result;
		}
		
		if (session.getAttribute("adminSession") == null) {
			System.out.println("Error Admin Session is Null");
			return new ModelAndView("/authentication/connection");
		}
		model.addAttribute("admin", session.getAttribute("adminSession"));
		
		if (groupService.findGroupByName(group.getName()) != null) {			
			ModelAndView result = new ModelAndView("/login_staff/group/addGroup");
			result.addObject("errorName", "Un groupe porte déjà ce nom");
			return result;
		}

		System.out.println("Controller : /AdminGroupController --- Action : /addGroup POST");
		
		try {
			if (!file.isEmpty()) {
				if (!file.getContentType().equals("image/gif") &&
					!file.getContentType().equals("image/jpeg") &&
					!file.getContentType().equals("image/png"))				
					return new ModelAndView("/login_staff/group/addGroup").addObject("errorFile",
						"L'avatar doit être un fichier de type image (.gif, .jpeg ou .png)");
				
				if(file.getSize() > Final.FILE_MAX_SIZE)
					return new ModelAndView("/login_staff/group/addGroup").addObject("errorFile",
						"La taille de l'image est trop grande, veuillez en selectionner une autre");
				
				group.setAvatar(file.getBytes());
			}else{
				InputStream is = context.getResourceAsStream("/images/user.png");
				group.setAvatar(IOUtils.toByteArray(is));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		groupService.save(group);

		redirectAttributes.addFlashAttribute("type", "success");
		redirectAttributes.addFlashAttribute("message", "Le groupe "+group.getName()+" a bien été ajouté.");

		return new ModelAndView("redirect:/login_staff/group/listGroup.htm");
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
			@RequestParam(value = "name") String nameg,
			@RequestParam String action, HttpSession session, Model model) {

		if (session.getAttribute("adminSession") == null) {
			System.out.println("Error Admin Session is Null");
			return new ModelAndView("/authentication/connection");
		}
		model.addAttribute("admin", session.getAttribute("adminSession"));

		Map<String, List<Group>> mapGroup = new HashMap<String, List<Group>>();
		List<Group> listGroup = new ArrayList<Group>();

		Map<String, Group> mapGroupModify = new HashMap<String, Group>();

		String name = AsciiToHex.decode(nameg);
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
	public ModelAndView modifyGroup(@RequestParam(value = "gname") String gname,
			@Valid @ModelAttribute(value = "group") Group groupNew,
			@RequestParam(required = false) MultipartFile file,
			RedirectAttributes redirectAttributes,
			HttpSession session, Model model,
			BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			ModelAndView result = new ModelAndView("/login_staff/group/addGroup");
			result.addObject("group", groupNew);
			return result;
		}
		
		if (session.getAttribute("adminSession") == null) {
			System.out.println("Error Admin Session is Null");
			return new ModelAndView("/authentication/connection");
		}
		model.addAttribute("admin", session.getAttribute("adminSession"));
		Map<String, Group> mapGroupModify = new HashMap<String, Group>();
		
		String name = AsciiToHex.decode(gname);
		
		if (!groupNew.getName().equals(name)
				&& groupService.findGroupByName(groupNew.getName()) != null) {
			Group group = groupService.findGroupByName(name);
			mapGroupModify.put("group", group);	
			
			ModelAndView result = new ModelAndView("login_staff/group/modifyGroup", mapGroupModify);
			result.addObject("errorName",
				"Un groupe porte déjà le nom "+ groupNew.getName());
			return result;
		}
		Group groupOld = groupService.findGroupByName(name);
		try {
			if (!file.isEmpty()) {
				if (!file.getContentType().equals("image/gif") &&
					!file.getContentType().equals("image/jpeg") &&
					!file.getContentType().equals("image/png")) {		
					
					ModelAndView result = new ModelAndView("login_staff/group/modifyGroup", mapGroupModify);
					result.addObject("errorFile",
						"L'avatar doit être un fichier de type image (.gif, .jpeg ou .png).");
					return result;
				}
				
				if(file.getSize() > Final.FILE_MAX_SIZE) {							
					ModelAndView result = new ModelAndView("login_staff/group/modifyGroup", mapGroupModify);
					result.addObject("errorFile",
							"La taille de l'image est trop grande, veuillez en selectionner une autre");
					return result;
				}
				
				groupNew.setAvatar(file.getBytes());
			}
			else groupNew.setAvatar(groupOld.getAvatar());
		} catch (IOException e) {
			e.printStackTrace();
		}
		groupService.modify(groupNew, name);
		
		redirectAttributes.addFlashAttribute("type", "success");
		redirectAttributes.addFlashAttribute("message", "Le groupe "+groupNew.getName()+" a bien été modifié.");

		return new ModelAndView("redirect:/login_staff/group/listGroup.htm");
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
	
	@ModelAttribute("nbWaitingUsers")
	public int nbWaitingUsers () {
		return userService.nbWaitingUsers();
	}
}
