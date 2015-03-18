package controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import domain.Publication;
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
	
	
	 /**
	  * Edit a publication
	  * @param model publication
	  * @return
	  */
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(Model model) {
		System.out.println("Controller : /PublicationController --- Action : /edit");
		ModelAndView result = new ModelAndView("publication/edit");
		model.addAttribute("publication", new Publication());
		return result;
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
