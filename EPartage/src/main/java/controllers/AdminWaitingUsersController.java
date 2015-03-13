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
import domain.Status;
import domain.Student;

@Controller
@RequestMapping("/login_staff/waitingUsers")
public class AdminWaitingUsersController {

	@Autowired
	AdminService adminService;

	// Constructors -----------------------------------------------------------

	public AdminWaitingUsersController() {
		super();
	}

	// List waiting
	// ------------------------------------------------------------------

	@RequestMapping(value = "/listWaiting")
	public ModelAndView listWaiting(HttpSession session, Model model) {

		System.out
				.println("Controller : /AdminController --- Action : /listWaiting");

		Map<String, List<Student>> mapStudents = new HashMap<String, List<Student>>();

		List<Student> listStudents = adminService.findByStatus(Status.W);

		mapStudents.put("listStudents", listStudents);

		model.addAttribute("admin", session.getAttribute("adminSession"));
		return new ModelAndView("login_staff/listWaiting", mapStudents);
	}
	
	@RequestMapping(value = "/validateUser")
	public ModelAndView validateUsers(@RequestParam(value="id") Integer id , HttpSession session, Model model) {
		System.out.println("coucou");
		System.out.println(id);
		model.addAttribute("admin", session.getAttribute("adminSession"));
		adminService.validateUser(id);
		return new ModelAndView("login_staff/listWaiting");
	}
}
