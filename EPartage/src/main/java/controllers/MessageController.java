package controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class MessageController {

	@Autowired
	private MessageService messageService;
	
	@Autowired
	private UserService userService;

	public MessageController() {
		super();
	}
	
	@RequestMapping(value = "/newmessage.htm", method = RequestMethod.GET)
	public ModelAndView newMessageForm(Model model) {
		Map<String, Object> users = new HashMap<String, Object>();
		users.put("users", userService.findAll());
		return new ModelAndView("message/newMessageForm", users);
	}
	
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
			String[] emails = input.split(",");
			
			if (emails[0].isEmpty() || emails.length > 100)
				return result.addObject("error",
						"Le nombre de destinataire doit être compris entre 1 et 100");
			
			List<User> receivers = new ArrayList<User>();
			List<String> badReceivers = new ArrayList<String>();
			
			for (String email : emails) {
				User receiver = userService.findByLogin(email);
				if(receiver == null)
					badReceivers.add(email);
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

	@RequestMapping("/sentMessagesList.htm")
	public String sentMessageList() {
		return "message/sentMessagesList";
	}
	
	@RequestMapping("/receivedMessagesList.htm")
	public String receivedMessageList() {
		return "message/receivedMessagesList";
	}
	
	@RequestMapping(value = "/detail.htm", method = RequestMethod.GET)
	public String detailMessage(
			@ModelAttribute Message m, 
			@ModelAttribute User u,
			HttpSession session) {
		
		if (m == null || m.getAuthor() == null)
			return "message/receivedMessagesList";
				
		if (!m.getAuthor().equals(u) && !m.getReceivers().contains(u))
			return "message/receivedMessagesList";
		
		messageService.setConsultedMessage(u, m, true);
		
		return "message/message";
	}
	
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
	
	@ModelAttribute("sentMessages")
	public Collection<Message> sentMessages(HttpSession session) {
		return messageService.findAllSentMessages(
				(User) session.getAttribute("userSession"));
	}
	
//	@ModelAttribute("consultedMessages")
//	public Collection<Message> consultedMessages(HttpSession session) {
//		return messageService.findAllReceivedMessages(
//				(User) session.getAttribute("userSession"), true);
//	}
//	
//	@ModelAttribute("unconsultedMessages")
//	public Collection<Message> unconsultedMessages(HttpSession session) {
//		return messageService.findAllReceivedMessages(
//				(User) session.getAttribute("userSession"), false);
//	}
	
	@ModelAttribute("receivedMessages")
	public Collection<ReceivedMessage> receivedMessages(HttpSession session) {
		return messageService.findAllReceivedMessages(
				(User) session.getAttribute("userSession"));
	}
	
	@ModelAttribute("nbOfUnconsultedMessages")
	public int nbOfUnconsultedMessages(HttpSession session) {
		return messageService.getNbOfUnconsultedMessages(
				(User) session.getAttribute("userSession"));
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
