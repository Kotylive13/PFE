package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import services.MessageService;

@Controller
@RequestMapping("/message")
public class MessageController {

	@Autowired
	private MessageService messageService;
	
	public MessageController() {
		super();
	}
	
	@RequestMapping("/list")
	public void listMessage () {
		System.out.println("###### Messages");
		System.out.println(messageService);
		System.out.println("Nom dest 0 : " + messageService.findAll().iterator().next().getReceivers().get(0).getFirstName());
	}
}
