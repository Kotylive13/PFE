package controllers;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import services.CategoryService;
import services.GroupService;
import services.MessageService;
import utilities.AsciiToHex;
import domain.Category;
import domain.Group;
import domain.IdSubcategory;
import domain.Student;
import domain.Subcategory;
import domain.User;

@Controller
@RequestMapping("/workspace/group")
public class GroupController {

	@Autowired
	GroupService groupService;
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	MessageService messageService;
	
	@RequestMapping("/avatar.htm")
	public void avatarGroup(
			@RequestParam(value = "nameG", required = false) String nameG,
			HttpServletResponse response) {
		
		Group group = groupService.findGroupByName(AsciiToHex.decode(nameG));
		try {
			OutputStream o = response.getOutputStream();
			o.write(group.getAvatar());
			o.flush(); o.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/detail.htm", method = RequestMethod.GET)
	public String detailGroup(
			@ModelAttribute Group g,
			@ModelAttribute User u,
			Model model) {	
		
		if (g == null || g.getName().isEmpty())
			return "redirect:../index.htm";
		
		if (!u.getGroups().contains(g))
			return "redirect:../index.htm";
		
		Map<String, Object> urlParams = new HashMap<String, Object>();
		
		String nameG = AsciiToHex.asciiToHex(g.getName());
		urlParams.put(g.getName(), nameG);
		for (Category cat : g.getCategories()) {
			String nameC = AsciiToHex.asciiToHex(cat.getIdCategory().getName());
			urlParams.put(cat.getIdCategory().getName(), nameC);
			for (Subcategory sub : cat.getSubcategories()) {
				String nameS = AsciiToHex.asciiToHex(sub.getIdSubcategory().getSubcategory());
				urlParams.put(sub.getIdSubcategory().getSubcategory(), nameS);
			}
		}
		
		model.addAttribute("urlParams", urlParams);

		return "group/group";
	}
	
	@RequestMapping(value = "/subcategory/detail.htm", method = RequestMethod.GET)
	public String detailSubcategory(
			@ModelAttribute Subcategory sub,
			@ModelAttribute User u,
			Model model) throws UnsupportedEncodingException {
		
		if (sub == null || sub.getIdSubcategory().getSubcategory().isEmpty())
			return "redirect:../../index.htm";
	
		if (!u.getGroups().contains(sub.getGroup()))
			return "redirect:../../index.htm";
		
		Map<String, Object> urlParams = new HashMap<String, Object>();
		
		String nameG = sub.getIdSubcategory().getGroup();
		String urlNameG = AsciiToHex.asciiToHex(nameG);
		urlParams.put(nameG, urlNameG);
		
		for (Category cat : sub.getGroup().getCategories()) {
			
			String nameC = cat.getIdCategory().getName();
			String urlNameC = AsciiToHex.asciiToHex(nameC);
			urlParams.put(nameC, urlNameC);
			
			for (Subcategory s : cat.getSubcategories()) {
				
				String nameS = s.getIdSubcategory().getSubcategory();
				String urlNameS = AsciiToHex.asciiToHex(nameS);
				urlParams.put(nameS, urlNameS);
			}
		}
		
		model.addAttribute("urlParams", urlParams);

		return "group/subcategory";
	}
	
	@ModelAttribute("subcategory")
	public Subcategory newSubcategory(
			@RequestParam(value = "nameG", required = false) String nameG,
			@RequestParam(value = "nameC", required = false) String nameC,
			@RequestParam(value = "nameS", required = false) String nameS) {
		
		if (nameG != null && nameC != null && nameS != null) {
			IdSubcategory idSubcategory = new IdSubcategory();
			idSubcategory.setGroup(AsciiToHex.decode(nameG));
			idSubcategory.setCategory(AsciiToHex.decode(nameC));
			idSubcategory.setSubcategory(AsciiToHex.decode(nameS));
			return categoryService.findOne(idSubcategory);
		}

		return new Subcategory();
	}
	
	@ModelAttribute("group")
	public Group newGroup(
			@RequestParam(value = "nameG", required = false) String name) {
		
		if (name != null) {
			return groupService.findGroupByName(AsciiToHex.decode(name));
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
	
	@ModelAttribute("nbOfUnconsultedMessages")
	public int nbOfUnconsultedMessages(HttpSession session) {
		return messageService.getNbOfUnconsultedMessages(
				(User) session.getAttribute("userSession"));
	}
	
	@ModelAttribute("groupsUrl")
	public Map<String, Object> getGroupsUrl (HttpSession session) {
		User user = (User) session.getAttribute("userSession");
		
		Map<String, Object> groupsUrl = new HashMap<String, Object>();
		
		for(Group g : user.getGroups()) {
			String nameG = g.getName();
			String urlNameG = AsciiToHex.asciiToHex(nameG);
			groupsUrl.put(nameG, urlNameG);
		}
		
		return groupsUrl;
	}
}
