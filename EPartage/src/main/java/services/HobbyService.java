package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.HobbyDAO;
import domain.Hobby;

@Service
@Transactional
public class HobbyService {

	@Autowired
	private HobbyDAO hobbyDAO;
	
	/**
	 * All Hobbies
	 */
	public Collection<Hobby> findAll() {
		return hobbyDAO.findAll();
	}
	
	public Hobby save(Hobby hobby) {
		return hobbyDAO.save(hobby);
	}	
	
	public Hobby find(String hobby) {
		return hobbyDAO.findOne(hobby);
	}	
}