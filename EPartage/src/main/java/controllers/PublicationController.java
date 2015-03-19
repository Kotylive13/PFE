package controllers;

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
import services.PublicationService;
import domain.IdSubcategory;
import domain.Publication;
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

	@ModelAttribute("student")
	public Student getStudent(HttpSession session) {
		return (Student) session.getAttribute("userSession");
	}

	@ModelAttribute("user")
	public User getUser(HttpSession session) {
		return (User) session.getAttribute("userSession");
	}

}
