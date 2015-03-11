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
			System.out.println("Erreurs   :" + bindingResult.getAllErrors());
		}else{
			
			//User user = (User) session.getAttribute("userSession");
			//message.setAuthor(user);
			User author = userService.findLogin("yoann.m@gmail.com", "mdp");
			message.setAuthor(author);
			
			String input = request.getParameter("receiversList");
			input = input.replace(" ", "");
			String[] emails = input.split(";");
			
			List<User> receivers = new ArrayList<User>();
			for (String email : emails) {
				User receiver = userService.findByLogin(email);
				if(receiver == null) {
					System.out.println("Erreur : " + email + " utilisateur inconnu");
					continue;
				}
				receivers.add(receiver);
			}
			message.setReceivers(receivers);
			
			message.getIdMessage().setDateM(new Date());
			
			messageService.save(message);
			
			result = new ModelAndView("redirect:list.htm");
		}
		
		return result;
	}

	@RequestMapping("/list.htm")
	public void listMessage() {
		System.out.println("###### Messag");
		System.out.println(messageService);
		System.out.println("Nom expéditeur : "
				+ messageService.findAll().iterator().next().getIdMessage().getDateM());
	}
}
