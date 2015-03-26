package controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.servlet.ModelAndView;

import services.MessageService;
import services.UserService;
import utilities.AsciiToHex;
import domain.Group;
import domain.IdMessage;
import domain.Message;
import domain.ReceivedMessage;
import domain.Student;
import domain.User;

/**
 * Class managing message page's actions
 * 
 * @author 
 */
@Controller
@RequestMapping("/message")
public class MessageController extends GlobalExceptionHandler {

	/**
	 * @see MessageService
	 */
	@Autowired
	private MessageService messageService;
	
	/**
	 * @see UserService
	 */
	@Autowired
	private UserService userService;

	public MessageController() {
		super();
	}
	
	/**
	 * Finds all application users to display possible receivers in the view
	 * 
	 * @param model {@link Model}
	 * @return New message form
	 */
	@RequestMapping(value = "/newmessage.htm", method = RequestMethod.GET)
	public ModelAndView newMessageForm(Model model) {
		Map<String, Object> users = new HashMap<String, Object>();
		users.put("users", userService.findAll());
		return new ModelAndView("message/newMessageForm", users);
	}
	
	/**
	 * Called when user sends new message form
	 * 
	 * @param message Content of the message
	 * @param bindingResult {@link BindingResult}
	 * @param session {@link HttpSession}
	 * @param request {@link HttpServletRequest}
	 * @return view with specific message
	 */
	@RequestMapping(value = "/newmessage.htm",  method = RequestMethod.POST)
	public ModelAndView newMessageForm(@Valid @ModelAttribute Message message,
			BindingResult bindingResult, HttpSession session, 
			HttpServletRequest request) {

		Map<String, Object> users = new HashMap<String, Object>();
		users.put("users", userService.findAll());
		ModelAndView result = new ModelAndView("message/newMessageForm", users);
		
		if (bindingResult.hasErrors()) {
			result.addObject("message", message);
		} else {
			
			String input = request.getParameter("receiversList");
			input = input.replace(" ", "");
			String[] userIds = input.split(",");
			
			if (userIds[0].isEmpty() || userIds.length > 100)
				return result.addObject("error",
						"Le nombre de destinataire doit être compris entre 1 et 100");
			
			Set<User> receivers = new HashSet<User>();
			List<String> badReceivers = new ArrayList<String>();
			
			for (String userId : userIds) {				
				User receiver = userService.find(Integer.parseInt(userId));
				if(receiver == null)
					badReceivers.add(userId);
				else
					receivers.add(receiver);
			}
			
			if (!receivers.isEmpty()) {
				message.setReceivers(receivers);
				User user = (User) session.getAttribute("userSession");
				message.setAuthor(user);
				message.getIdMessage().setDateM(new Date());
				messageService.save(message);
				result = new ModelAndView("redirect:sentMessagesList.htm");
			}
			
			if (!badReceivers.isEmpty())
				result = new ModelAndView("message/newMessageForm", 
						"badReceivers", badReceivers);
		}
		
		return result;
	}

	/**
	 * @return view which display sent messages
	 */
	@RequestMapping("/sentMessagesList.htm")
	public String sentMessageList() {
		return "message/sentMessagesList";
	}
	
	/**
	 * @return view which display received messages
	 */
	@RequestMapping("/receivedMessagesList.htm")
	public String receivedMessageList() {
		return "message/receivedMessagesList";
	}
	
	/**
	 * Displays the details of a message if user in session is either author 
	 * or receiver
	 * 
	 * @param m Message that we want to see informations
	 * @param u User in session
	 * @param session {@link HttpSession}
	 * @return view with specific message
	 */
	@RequestMapping(value = "/detail.htm", method = RequestMethod.GET)
	public ModelAndView detailMessage(
			@ModelAttribute Message m, 
			@ModelAttribute User u,
			HttpSession session) {
		
		ModelAndView result = new ModelAndView("message/receivedMessagesList");
		
		if (m == null || m.getAuthor() == null)
			return result;
				
		if (!m.getAuthor().equals(u) && !m.getReceivers().contains(u))
			return result;
		
		result = new ModelAndView("message/message");
		messageService.setConsultedMessage(u, m, true);
		result.addObject("nbOfUnconsultedMessages", nbOfUnconsultedMessages(session));
		
		return result;
	}

	/**
	 * @param author Author id
	 * @param date Date
	 * @return Message corresponding to the URL params or a new Message
	 */
	@ModelAttribute("message")
	public Message newMessage(
			@RequestParam(value = "id", required = false) Integer author,
			@RequestParam(value = "date", required = false) Long date) {
		
		if (author != null && date != null) {
			IdMessage idMessage = new IdMessage();
			idMessage.setSender(author);
			idMessage.setDateM(new Date(date));
			return messageService.findOne(idMessage);
		}

		return new Message();
	}
	
	/**
	 * @param session {@link HttpSession}
	 * @return List of sent messages corresponding to the user in session
	 */
	@ModelAttribute("sentMessages")
	public Collection<Message> sentMessages(HttpSession session) {
		return messageService.findAllSentMessages(
				(User) session.getAttribute("userSession"));
	}
	
	/**
	 * @param session {@link HttpSession}
	 * @return List of received messages corresponding to the user in session
	 */
	@ModelAttribute("receivedMessages")
	public Collection<ReceivedMessage> receivedMessages(HttpSession session) {
		return messageService.findAllReceivedMessages(
				(User) session.getAttribute("userSession"));
	}
	
	/** 
	 * @param session {@link HttpSession}
	 * @return Number of unconsulted message of the user in session
	 */
	@ModelAttribute("nbOfUnconsultedMessages")
	public int nbOfUnconsultedMessages(HttpSession session) {
		return messageService.getNbOfUnconsultedMessages(
				(User) session.getAttribute("userSession"));
	}
	
	/** 
	 * @param session {@link HttpSession}
	 * @return Student in session
	 */
	@ModelAttribute("student")
	public Student getStudent (HttpSession session) {
		return (Student) session.getAttribute("userSession");
	}
	
	/**
	 * @param session {@link HttpSession}
	 * @return User in session
	 */
	@ModelAttribute("user")
	public User getUser (HttpSession session) {
		User userSession = (User) session.getAttribute("userSession");
		return userService.findByLogin(userSession.getEmail());
	}
	
	/**
	 * @param session {@link HttpSession}
	 * @return Groups list corresponding to the user in session
	 */
	@ModelAttribute("groupsList")
	public Collection<Group> getUserGroups(HttpSession session) {
		User userSession = (User) session.getAttribute("userSession");
		return userService.findByLogin(userSession.getEmail()).getGroups();
	}
	
	/**
	 * @param session {@link HttpSession}
	 * @return Map with groups name and corresponding encoded groups name
	 */
	@ModelAttribute("groupsUrl")
	public Map<String, Object> getGroupsUrl (HttpSession session) {
		User userSession = (User) session.getAttribute("userSession");
		User user = userService.findByLogin(userSession.getEmail());
		
		Map<String, Object> groupsUrl = new HashMap<String, Object>();
		
		for(Group g : user.getGroups()) {
			String nameG = g.getName();
			String urlNameG = AsciiToHex.asciiToHex(nameG);
			groupsUrl.put(nameG, urlNameG);
		}
		
		return groupsUrl;
	}
}
