package controllers;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import services.AdminService;
import services.GroupService;
import services.MembershipGroupService;
import services.UserService;
import utilities.MailSender;
import domain.Group;
import domain.Status;
import domain.Student;
import domain.User;

@Controller
@RequestMapping("/login_staff/user")
public class AdminUsersController {

	@Autowired
	AdminService adminService;
	
	@Autowired
	GroupService groupService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	MembershipGroupService membershipGroupService;

// Constructors ---------------------------------------------------------------

	public AdminUsersController() {
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
		return new ModelAndView("login_staff/user/listWaiting", mapStudents);
	}
	
	@RequestMapping(value = "/validateUser")
	public ModelAndView validateUsers(@RequestParam(value="id") Integer id , 
			@RequestParam String action, @RequestParam("groupPost") String groupName, 
			RedirectAttributes redirectAttributes, HttpSession session, Model model) {
		
		if(session.getAttribute("adminSession") == null) {
			System.out.println("Error Admin Session is Null");
			return new ModelAndView("/authentication/connection");
		}
		
		Map<String, Object> errorMessages = new HashMap<String, Object>();
		model.addAttribute("admin", session.getAttribute("adminSession"));
//		List <Group> listGroup = new ArrayList<Group>();
		
		
		Student student = adminService.findStudentById(id);
		if (student == null) {
			errorMessages.put("type", "error");
			errorMessages.put("message", "Cette page est inaccessible.");
			return new ModelAndView("login_staff/user/listWaiting", errorMessages);
		}
		
		if (action.equals("Valider") && groupService.findGroupByName(groupName) != null) {
			membershipGroupService.addUser(student, groupName);
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

		redirectAttributes.addFlashAttribute("type", "success");
		redirectAttributes.addFlashAttribute("message", student.getFirstName() + " " + 
							student.getLastName() + " à désormais accès à la paterforme.");
		return new ModelAndView("redirect:/login_staff/user/listWaiting.htm");
	}
	
	@RequestMapping(value = "/listUsers")
	public ModelAndView listUsers(HttpSession session, Model model) {
		if(session.getAttribute("adminSession") == null) {
			System.out.println("Error Admin Session is Null");
			return new ModelAndView("/authentication/connection");
		}
		
		model.addAttribute("admin", session.getAttribute("adminSession"));
		Map<String, List<User>> mapUsers = new HashMap<String, List<User>>();
		List<User> listUsers = (List<User>) userService.findAllActive();
		mapUsers.put("listStudents", listUsers);
		return new ModelAndView("login_staff/user/listUsers", mapUsers);
	}
	
	@RequestMapping(value = "/addGrouptoUser")
	public ModelAndView addGrouptoUser(@RequestParam(value="id") Integer id , 
			@RequestParam String action, @RequestParam("groupPost") String groupName, 
			RedirectAttributes redirectAttributes, HttpSession session, Model model) {
		
		model.addAttribute("admin", session.getAttribute("adminSession"));		
		User user = userService.find(id);
		membershipGroupService.addUser(user, groupName);
		

		redirectAttributes.addFlashAttribute("type", "success");
		redirectAttributes.addFlashAttribute("message", user.getFirstName() + " " + 
				user.getLastName() + " à désormais accès au groupe "+groupName+".");
		
		return new ModelAndView("redirect:/login_staff/user/listUsers.htm"); 
	}
	
	@ModelAttribute("groupMap")
	public Map<String, String> getGroups() {
		Map<String,String> groupMap = new LinkedHashMap<String,String>();
		List<Group> groupList = groupService.findAll();
		
		for (Group group : groupList) {
			groupMap.put(group.getName(), group.getName());
		}
		return groupMap;
	}
	
	@ModelAttribute("groupList")
	public List<Group> getGroupsList() {
		return groupService.findAll();
	}
	
	@ModelAttribute("nbWaitingUsers")
	public int nbWaitingUsers () {
		return userService.nbWaitingUsers();
	}
}
