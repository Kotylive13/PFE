package controllers;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CategoryService;
import services.CommentFileService;
import services.MessageService;
import services.PublicationFileService;
import services.PublicationService;
import utilities.AsciiToHex;
import domain.CommentFile;
import domain.Group;
import domain.IdSubcategory;
import domain.Publication;
import domain.PublicationFile;
import domain.Student;
import domain.Subcategory;
import domain.User;

/**
 * Class managing publications
 * 
 * @author Asma
 */

@Controller
@RequestMapping("/publication")
public class PublicationController {

	@Autowired
	PublicationService publicationService;
	@Autowired
	CategoryService categoryService;
	@Autowired
	MessageService messageService;
	@Autowired
	PublicationFileService publicationFileService;
	@Autowired
	CommentFileService commentFileService;

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(ModelMap mapModel,
			@RequestParam(value = "nameG", required = true) String nameG,
			@RequestParam(value = "nameC", required = true) String nameC,
			@RequestParam(value = "nameS", required = true) String nameS) {
		System.out
				.println("Controller : /PublicationController --- Action : /edit");
		ModelAndView result = new ModelAndView("publication/edit");
		IdSubcategory idSubcategory = new IdSubcategory();
		idSubcategory.setGroup(nameG);
		idSubcategory.setCategory(nameC);
		idSubcategory.setSubcategory(nameS);
		Subcategory sub = categoryService.findOne(idSubcategory);
		mapModel.addAttribute("subcategory", sub);
		mapModel.addAttribute("publication", new Publication());
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public ModelAndView save(@Valid @ModelAttribute Publication publication,
			@ModelAttribute Subcategory subcategory,
			BindingResult bindingResult, HttpSession session) {

		System.out
				.println("Controller : /PublicationController --- Action : /save");

		ModelAndView result = new ModelAndView("publication/edit");
		// Validating model
		if (bindingResult.hasErrors()) {
			return result;
		}
		User user = (User) session.getAttribute("userSession");
		publication.setAuthor(user);
		publication.setSubcategory(subcategory);
		publicationService.save(publication);
		return new ModelAndView("redirect:/workspace/index.htm");
	}
	
	@RequestMapping("/file.htm")
	public void file(
			@RequestParam(value = "pub", required = false) String pub,
			@RequestParam(value = "id", required = false) String id,
			HttpServletResponse response) {
		
		PublicationFile publicationFile = publicationFileService.find(Integer.parseInt(pub), Integer.parseInt(id));
		try {
			OutputStream o = response.getOutputStream();
			o.write(publicationFile.getFile());
			o.flush(); o.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/comment/file.htm")
	public void commentFile(
			@RequestParam(value = "pub", required = false) String pub,
			@RequestParam(value = "com", required = false) String com,
			@RequestParam(value = "id", required = false) String id,
			HttpServletResponse response) {
		
		CommentFile commentFile = commentFileService.find(
				Integer.parseInt(pub), 
				Integer.parseInt(com), 
				Integer.parseInt(id));
		try {
			OutputStream o = response.getOutputStream();
			o.write(commentFile.getFile());
			o.flush(); o.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@ModelAttribute("student")
	public Student getStudent(HttpSession session) {
		return (Student) session.getAttribute("userSession");
	}

	@ModelAttribute("user")
	public User getUser(HttpSession session) {
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
