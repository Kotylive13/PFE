package controllers;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CategoryService;
import services.GroupService;
import services.MessageService;
import services.UserService;
import utilities.AsciiToHex;
import domain.Category;
import domain.Comment;
import domain.Group;
import domain.IdSubcategory;
import domain.PublicationForm;
import domain.Student;
import domain.Subcategory;
import domain.User;

/**
 * Class managing group page's actions
 * 
 * @author
 */
@Controller
@RequestMapping("/workspace/group")
public class GroupController {

	/**
	 * @see UserService
	 */
	@Autowired
	UserService userService;

	/**
	 * @see GroupService
	 */
	@Autowired
	GroupService groupService;

	/**
	 * @see CategoryService
	 */
	@Autowired
	CategoryService categoryService;

	/**
	 * @see MessageService
	 */
	@Autowired
	MessageService messageService;

	/**
	 * Displays avatar corresponding to the URL parameter
	 * 
	 * @param nameG
	 *            group name
	 * @param response
	 *            {@link HttpServletResponse}
	 */
	@RequestMapping("/avatar.htm")
	public void avatarGroup(
			@RequestParam(value = "nameG", required = false) String nameG,
			HttpServletResponse response) {

		Group group = groupService.findGroupByName(AsciiToHex.decode(nameG));
		if (group != null) {
			if (group.getAvatar() != null) {
				try {
					OutputStream o = response.getOutputStream();
					o.write(group.getAvatar());
					o.flush();
					o.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Displays group's informations if user in session belongs to this group.
	 * 
	 * @param g
	 *            Group that we want to see informations
	 * @param u
	 *            User in session
	 * @param model
	 *            {@link Model}
	 * @return view with specific message
	 */
	@RequestMapping(value = "/detail.htm", method = RequestMethod.GET)
	public ModelAndView detailGroup(@ModelAttribute Group g,
			@ModelAttribute User u, Model model) {

		if (g == null || g.getName().isEmpty())
			return new ModelAndView("redirect:../index.htm");

		ModelAndView result = new ModelAndView("group/group");

		if (!u.getGroups().contains(g)) {
			result.addObject("notInGroup", "error");
			result.addObject("message", "Vous n'appartenez pas à ce groupe.");
		}

		Map<String, Object> urlParams = new HashMap<String, Object>();

		String nameG = AsciiToHex.asciiToHex(g.getName());
		urlParams.put(g.getName(), nameG);
		for (Category cat : g.getCategories()) {
			String nameC = AsciiToHex.asciiToHex(cat.getIdCategory().getName());
			urlParams.put(cat.getIdCategory().getName(), nameC);
			for (Subcategory sub : cat.getSubcategories()) {
				String nameS = AsciiToHex.asciiToHex(sub.getIdSubcategory()
						.getSubcategory());
				urlParams.put(sub.getIdSubcategory().getSubcategory(), nameS);
			}
		}

		model.addAttribute("urlParams", urlParams);

		return result;
	}

	/**
	 * Displays subcategory's informations if user in session belongs to the
	 * corresponding group.
	 * 
	 * @param sub
	 *            Subcategory that we want to see informations
	 * @param u
	 *            User in session
	 * @param model
	 *            {@link Model}
	 * @return view with specific message
	 */
	@RequestMapping(value = "/subcategory/detail.htm", method = RequestMethod.GET)
	public ModelAndView detailSubcategory(@ModelAttribute Subcategory sub,
			@ModelAttribute User u, Model model) {

		if (sub == null || sub.getIdSubcategory().getSubcategory().isEmpty())
			return new ModelAndView("redirect:../index.htm");

		ModelAndView result = new ModelAndView("group/subcategory");

		if (!u.getGroups().contains(sub.getGroup())) {
			result.addObject("notInGroup", "error");
			result.addObject("message", "Vous n'appartenez pas à ce groupe.");
		}

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

		return result;
	}

	/**
	 * Search groups and display them.
	 * 
	 * @param keywords
	 *            Keywords corresponding to the search
	 * @return view with specific message
	 */
	@RequestMapping(value = "/search.htm", method = RequestMethod.GET)
	public ModelAndView search(
			@RequestParam(value = "keywords", required = true) String keywords) {
		Set<Group> res = new HashSet<Group>();

		String tokens[] = keywords.split("\\s+");
		for (String token : tokens)
			res.addAll(groupService.findByKeyword(token));

		Map<String, Object> groups = new HashMap<String, Object>();
		groups.put("groups", res);
		return new ModelAndView("group/search", groups);
	}

	/**
	 * @param nameG
	 *            Group name
	 * @param nameC
	 *            Category name
	 * @param nameS
	 *            Subcategory name
	 * @return Subcategory corresponding to the URL params or a new Subcategory
	 */
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

	/**
	 * @param name
	 *            Group name
	 * @return Group corresponding to the URL params or a new Group
	 */
	@ModelAttribute("group")
	public Group newGroup(
			@RequestParam(value = "nameG", required = false) String name) {

		if (name != null) {
			return groupService.findGroupByName(AsciiToHex.decode(name));
		}

		return new Group();
	}

	/**
	 * @param session
	 *            {@link HttpSession}
	 * @return Groups list corresponding to the user in session
	 */
	@ModelAttribute("groupsList")
	public Collection<Group> getUserGroups(HttpSession session) {
		User userSession = (User) session.getAttribute("userSession");
		if (userSession == null)
			return null;
		return userService.findByLogin(userSession.getEmail()).getGroups();
	}

	/**
	 * @param session
	 *            {@link HttpSession}
	 * @return Map with groups name and corresponding encoded groups name
	 */
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

	/**
	 * @param session
	 *            {@link HttpSession}
	 * @return Student in session
	 */
	@ModelAttribute("student")
	public Student getStudent(HttpSession session) {
		Student studentSession = (Student) session.getAttribute("userSession");
		if (studentSession == null)
			return null;
		return studentSession;
	}

	/**
	 * @param session
	 *            {@link HttpSession}
	 * @return User in session
	 */
	@ModelAttribute("user")
	public User getUser(HttpSession session) {
		User userSession = (User) session.getAttribute("userSession");
		if (userSession == null)
			return null;
		return userService.findByLogin(userSession.getEmail());
	}

	/**
	 * @param session
	 *            {@link HttpSession}
	 * @return Number of unconsulted message of the user in session
	 */
	@ModelAttribute("nbOfUnconsultedMessages")
	public int nbOfUnconsultedMessages(HttpSession session) {
		User userSession = (User) session.getAttribute("userSession");
		if (userSession == null)
			return 0;
		return messageService.getNbOfUnconsultedMessages(userSession);
	}

	/**
	 * @return new Publication form
	 */
	@ModelAttribute("publication")
	public PublicationForm newPublicatin() {
		return new PublicationForm();
	}

	@ModelAttribute("comment")
	public Comment newComment() {
		return new Comment();
	}
}
