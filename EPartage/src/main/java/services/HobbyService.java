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
	
	public void save(Hobby hobby) {
		hobbyDAO.save(hobby);
	}	
}