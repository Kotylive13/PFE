package controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import org.springframework.web.servlet.ModelAndView;

import services.MessageService;
import services.UserService;
import domain.Message;
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
	public ModelAndView detailPerson(Model model) {

		ModelAndView result;
		Message message = new Message();
		result = new ModelAndView("message/NewMessageForm");
		model.addAttribute("message", message);
		return result;
	}
	
	@RequestMapping(value = "/newmessage.htm",  method = RequestMethod.POST)
	public ModelAndView subscribePost(@Valid @ModelAttribute Message message, 
			BindingResult bindingResult, HttpSession session, HttpServletRequest request) {

		ModelAndView result;
		result = new ModelAndView("message/NewMessageForm");
		
		if (bindingResult.hasErrors()) {
			result.addObject("message", message);
		} else {
			
			String input = request.getParameter("receiversList");
			input = input.replace(" ", "");
			String[] emails = input.split(";");
			
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
				result = new ModelAndView("redirect:list.htm");
			}
			
			if (!badReceivers.isEmpty())
				result = new ModelAndView("message/NewMessageForm", 
						"badReceivers", badReceivers);
		}
		
		return result;
	}

	@RequestMapping("/list.htm")
	public String listMessage() {
		return "message/ReceivedMessagesList";
	}
}
