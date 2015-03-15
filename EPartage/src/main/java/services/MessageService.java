package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.MessageDAO;
import domain.IdMessage;
import domain.Message;
import domain.ReceivedMessage;
import domain.User;

@Service
@Transactional
public class MessageService {

	@Autowired
	private MessageDAO messageDAO;
	
	/**
	 * All messages
	 */
	public Collection<Message> findAll() {
		return messageDAO.findAll();
	}
	
	public void save(Message message) {
		messageDAO.save(message);
	}
	
	public Collection<Message> findAllSentMessages(User user) {
		return messageDAO.findAllSentMessagesByUserId(user.getId());
	}
	
	public Message findOne(IdMessage idMessage) {
		return messageDAO.findOne(idMessage);
	}
	
//	public Collection<Message> findAllReceivedMessages(User user, boolean consult) {
//		return messageDAO.findAllReceivedMessages(user.getId(), consult);
//	}
	
	public Collection<ReceivedMessage> findAllReceivedMessages(User user) {
		List<ReceivedMessage> receivedMessages = new ArrayList<ReceivedMessage>();
		
		List<Message> messages = messageDAO.findAllReceivedMessages(user.getId());
		
		for (Message m : messages) {
			ReceivedMessage rm = new ReceivedMessage();
			rm.setMessage(m);
			rm.setConsulted(messageDAO.getMessageStatus(
					m.getIdMessage().getSender(), 
					m.getIdMessage().getDateM(), 
					user.getId()));
			receivedMessages.add(rm);
		}
		
		return receivedMessages;
	}
	
	public int getNbOfUnconsultedMessages(User user) {
		return messageDAO.getNbOfUnconsultedMessages(user.getId());
	}
	
	public void setConsultedMessage(User user, Message message, boolean consult) {
		messageDAO.setConsultedMessage(message.getIdMessage().getSender(), 
				message.getIdMessage().getDateM(), user.getId(), consult);
	}

}
