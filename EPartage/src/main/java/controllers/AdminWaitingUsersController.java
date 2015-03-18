package controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.AdminService;
import utilities.MailSender;
import domain.Status;
import domain.Student;

@Controller
@RequestMapping("/login_staff/waitingUsers")
public class AdminWaitingUsersController {

	@Autowired
	AdminService adminService;

// Constructors ---------------------------------------------------------------

	public AdminWaitingUsersController() {
		super();
	}

// List waiting ---------------------------------------------------------------

	@RequestMapping(value = "/listWaiting")
	public ModelAndView listWaiting(HttpSession session, Model model) {
		
		if(session.getAttribute("adminSession") == null) {
			System.out.println("Error Admin Session is Null");
			return new ModelAndView("/authentication/connection");
		}
		model.addAttribute("admin", session.getAttribute("adminSession"));
		
		System.out.println("Controller : /AdminController --- Action : /listWaiting");
		Map<String, List<Student>> mapStudents = new HashMap<String, List<Student>>();
		List<Student> listStudents = adminService.findByStatus(Status.W);
		mapStudents.put("listStudents", listStudents);
		return new ModelAndView("login_staff/listWaiting", mapStudents);
	}
	
	@RequestMapping(value = "/validateUser")
	public ModelAndView validateUsers(@RequestParam(value="id") Integer id , 
			@RequestParam String action, HttpSession session, Model model) {
		
		if(session.getAttribute("adminSession") == null) {
			System.out.println("Error Admin Session is Null");
			return new ModelAndView("/authentication/connection");
		}
		
		Map<String, Object> errorMessages = new HashMap<String, Object>();
		model.addAttribute("admin", session.getAttribute("adminSession"));
		
		Student student = adminService.findStudentById(id);
		if (student == null) {
			errorMessages.put("type", "error");
			errorMessages.put("message", "Cette page est inaccessible.");
			return new ModelAndView("login_staff/listWaiting", errorMessages);
		}
		
		if (action.equals("Valider")) {
			adminService.validateUser(student.getId());
			MailSender.sendEmail(student.getEmail(), "Validation de l'inscription", 
					"Votre compte a bien été activé sur le site E-Partage. "
					+ "Vous pouvez dès à présent vous connecter.");
		} else if (action.equals("Supprimer")){
			adminService.refusedUser(student.getId());
			MailSender.sendEmail(student.getEmail(), "Non validation de l'inscription", 
					"Votre compte n'a pas été validé par l'administrateur pour des raisons de sécurité sur le site E-Partage. ");
		} else {
			System.out.println("Erreur");
			return new ModelAndView ("/authentication/connection");
		}
		return new ModelAndView("login_staff/listWaiting");
	}
	
}
