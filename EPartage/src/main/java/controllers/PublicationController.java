package controllers;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import services.CategoryService;
import services.CommentFileService;
import services.CommentService;
import services.MessageService;
import services.OpinionService;
import services.PublicationFileService;
import services.PublicationService;
import services.UserService;
import utilities.AsciiToHex;
import utilities.Final;
import domain.Comment;
import domain.CommentFile;
import domain.Group;
import domain.IdSubcategory;
import domain.Opinion;
import domain.Publication;
import domain.PublicationFile;
import domain.PublicationForm;
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
public class PublicationController implements HandlerExceptionResolver {

	@Autowired
	PublicationService publicationService;
	@Autowired
	UserService userService;
	@Autowired
	CategoryService categoryService;
	@Autowired
	MessageService messageService;
	@Autowired
	PublicationFileService publicationFileService;
	@Autowired
	CommentFileService commentFileService;
	@Autowired
	OpinionService opinionService;
	@Autowired
	CommentService commentService;

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public ModelAndView save(
			@Valid @ModelAttribute PublicationForm publication,
			BindingResult bindingResult, HttpSession session,
			@RequestParam(required = false) MultipartFile file,
			@RequestParam(required = false) String nameC,
			@RequestParam(required = false) String nameG,
			@RequestParam(required = false) String nameS,
			RedirectAttributes redirectAttributes, Model model) {

		String errorMessage = "Veuillez utiliser le formulaire de publication pour partager un contenu !";
		// test if no params
		if (nameC == null || nameG == null || nameS == null) {
			redirectAttributes.addFlashAttribute("type", "error");
			redirectAttributes.addFlashAttribute("message", errorMessage);
			return new ModelAndView("redirect:/workspace/index.htm");
		}
		// test if wrong params
		IdSubcategory idSubcategory = new IdSubcategory();
		idSubcategory.setGroup(AsciiToHex.decode(nameG));
		idSubcategory.setCategory(AsciiToHex.decode(nameC));
		idSubcategory.setSubcategory(AsciiToHex.decode(nameS));
		Subcategory subcategory = categoryService.findOne(idSubcategory);

		if (subcategory == null) {
			redirectAttributes.addFlashAttribute("type", "error");
			redirectAttributes.addFlashAttribute("message", errorMessage);
			return new ModelAndView("redirect:/workspace/index.htm");
		}
		publication.setSubcategory(subcategory);

		ModelAndView result = new ModelAndView(
				"redirect:/workspace/group/subcategory/detail.htm?nameG="
						+ nameG + "&nameC=" + nameC + "&nameS=" + nameS);

		User user = (User) session.getAttribute("userSession");
		publication.setAuthor(user);
		publication.setDateP(new Date());

		// save file
		if (!file.isEmpty()) {
			// test if name too long
			if (file.getOriginalFilename().length() > 32
					|| file.getOriginalFilename().length() < 5) {
				model.addAttribute("publication", publication);
				redirectAttributes.addFlashAttribute("type", "error");
				redirectAttributes
						.addFlashAttribute("message",
								"Le nom du fichier doit contenir entre 4 et 32 caractère !");
				return result;
			}
			// test if file too big > 5 Mo
			if (file.getSize() > Final.FILE_MAX_SIZE) {
				model.addAttribute("publication", publication);
				redirectAttributes.addFlashAttribute("type", "error");
				redirectAttributes
						.addFlashAttribute("message",
								"La taille du fichier joint ne doit pas dépasser 5 Mo !");
				return result;
			}

			try {
				publication.setFile(file.getBytes());
				publication.setFileTile(file.getOriginalFilename());
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			publication.setFile(null);
		}
		// Validating model
		if (bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("publication", publication);
			redirectAttributes.addFlashAttribute("type", "error");
			redirectAttributes.addFlashAttribute("message",
					"Veuillez saisir un titre et un contenu !");
			return result;
		}
		redirectAttributes.addFlashAttribute("type", "success");
		redirectAttributes.addFlashAttribute("message",
				"Contenu publié avec succès !");
		publicationService.constructAndSave(publication);

		return result;
	}

	@RequestMapping(value = "/comment/edit", method = RequestMethod.POST)
	public ModelAndView saveComment(@Valid @ModelAttribute Comment comment,
			BindingResult bindingResult, HttpSession session,
			@RequestParam(required = true) Integer id_pub,
			@RequestParam(required = false) String url,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {

		Publication pub = publicationService.find(id_pub);
		String nameG = AsciiToHex.asciiToHex(pub.getGroup().getName());
		String nameC = AsciiToHex.asciiToHex(pub.getSubcategory().getCategory()
				.getIdCategory().getName());
		String nameS = AsciiToHex.asciiToHex(pub.getSubcategory()
				.getIdSubcategory().getSubcategory());

		ModelAndView result = new ModelAndView(
				"redirect:/workspace/group/subcategory/detail.htm?nameG="
						+ nameG + "&nameC=" + nameC + "&nameS=" + nameS);
		// test if comment from workspace
		if (url != null) {
			if (url.equals("W")) {
				result = new ModelAndView("redirect:/workspace/index.htm");
			}
		}

		comment.setDateC(new Date());
		User user = (User) session.getAttribute("userSession");
		comment.setAuthor(user);
		comment.setPublication(pub);

		if (bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("type", "error");
			redirectAttributes.addFlashAttribute("message",
					"Veuillez saisir un commentaire !");
			return result;
		}
		// save comment

		commentService.save(comment);
		redirectAttributes.addFlashAttribute("type", "success");
		redirectAttributes.addFlashAttribute("message",
				"Commentaire publié avec succès !");
		return result;
	}

	@RequestMapping("/file.htm")
	public void file(@RequestParam(value = "pub", required = false) String pub,
			@RequestParam(value = "id", required = false) String id,
			HttpServletResponse response) {

		PublicationFile publicationFile = publicationFileService.find(
				Integer.parseInt(pub), Integer.parseInt(id));
		try {
			OutputStream o = response.getOutputStream();
			o.write(publicationFile.getFile());
			o.flush();
			o.close();
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
				Integer.parseInt(pub), Integer.parseInt(com),
				Integer.parseInt(id));
		try {
			OutputStream o = response.getOutputStream();
			o.write(commentFile.getFile());
			o.flush();
			o.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/addGoodOpinion.htm")
	public void addGoodOpinion(
			@RequestParam(value = "idPub", required = true) String idPub,
			@RequestParam(value = "idAuthor", required = true) String idAuthor) {

		User author = userService.find(Integer.parseInt(idAuthor));

		Opinion opinion = opinionService.findByAuthorAndPublication(author,
				Integer.parseInt(idPub));
		if (opinion != null)
			if (opinion.getValue().equals("good")) {
				opinionService.delete(opinion);
				return;
			} else
				opinionService.delete(opinion);

		opinion = new Opinion();
		opinion.setAuthor(author);
		opinion.setPublication(publicationService.find(Integer.parseInt(idPub)));
		opinion.setValue("good");
		opinionService.save(opinion);
	}

	@RequestMapping("/addBadOpinion.htm")
	public void addBadOpinion(
			@RequestParam(value = "idPub", required = true) String idPub,
			@RequestParam(value = "idAuthor", required = true) String idAuthor) {

		User author = userService.find(Integer.parseInt(idAuthor));

		Opinion opinion = opinionService.findByAuthorAndPublication(author,
				Integer.parseInt(idPub));
		if (opinion != null)
			if (opinion.getValue().equals("bad")) {
				opinionService.delete(opinion);
				return;
			} else
				opinionService.delete(opinion);

		opinion = new Opinion();
		opinion.setAuthor(author);
		opinion.setPublication(publicationService.find(Integer.parseInt(idPub)));
		opinion.setValue("bad");
		opinionService.save(opinion);
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
		return messageService.getNbOfUnconsultedMessages((User) session
				.getAttribute("userSession"));
	}

	@ModelAttribute("groupsUrl")
	public Map<String, Object> getGroupsUrl(HttpSession session) {
		User user = (User) session.getAttribute("userSession");

		Map<String, Object> groupsUrl = new HashMap<String, Object>();

		for (Group g : user.getGroups()) {
			String nameG = g.getName();
			String urlNameG = AsciiToHex.asciiToHex(nameG);
			groupsUrl.put(nameG, urlNameG);
		}

		return groupsUrl;
	}

	@ModelAttribute("publication")
	public PublicationForm newPublicatin() {
		return new PublicationForm();
	}

	@ModelAttribute("comment")
	public Comment newComment() {
		return new Comment();
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(byte[].class,
				new ByteArrayMultipartFileEditor());
	}

	@ExceptionHandler(Throwable.class)
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception exception) {
		ModelAndView result = new ModelAndView("redirect:/workspace/index.htm");

		if (exception instanceof MaxUploadSizeExceededException) {
			result.addObject("type", "error");
			result.addObject("message",
					"La taille du fichier joint ne doit pas dépasser 5 Mo !");
		} else {
			result.addObject("type", "error");
			result.addObject("message", "Oops ! Une erreur s'est produite !");
		}
		return result;
	}

}
