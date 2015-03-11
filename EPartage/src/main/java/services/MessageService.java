package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.MessageDAO;
import domain.Message;

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
}
