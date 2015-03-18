package controllers;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import services.PublicationService;
import domain.IdPublicationFile;
import domain.Publication;
import domain.PublicationFile;
import domain.Student;
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
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(Model model) {
		System.out.println("Controller : /PublicationController --- Action : /edit");
		ModelAndView result = new ModelAndView("publication/edit");
		model.addAttribute("publication", new Publication());
		return result;
	}
	
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public ModelAndView save(@Valid @ModelAttribute Publication publication ,BindingResult bindingResult, HttpSession session, @RequestParam("file") MultipartFile file) {
		
		System.out.println("Controller : /PublicationController --- Action : /save");

		ModelAndView result=new ModelAndView("publication/edit");
		// Validating model
		if (bindingResult.hasErrors()) {
			return result;
		}
		//upload the file
//		 if (file != null) {
//			 PublicationFile pubFile = new PublicationFile();
//			 
//		 }
		
		User user = (User) session.getAttribute("userSession");
		publication.setAuthor(user);
		publicationService.save(publication);
		return new ModelAndView("redirect:/workspace/index.htm");
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
