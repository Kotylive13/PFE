package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domain.Message;
import repositories.DAOMessage;

@Service
@Transactional
public class MessageService {

	@Autowired
	private DAOMessage daoMessage;
	
	/**
	 * All messages
	 */
	public Collection<Message> findAll() {
		return daoMessage.findAll();
	}
}
