package services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.OpinionDAO;
import domain.Opinion;
import domain.User;

@Service
@Transactional
public class OpinionService {

	@Autowired
	private OpinionDAO opinionDAO;
	
	public Opinion save(Opinion opinion) {
		return opinionDAO.save(opinion);
	}

	public Opinion findByAuthorAndPublication(User author, int publication) {
		return opinionDAO.findByAuthorAndPublication(author, publication);
	}	

	public void delete(Opinion opinion) {
		opinionDAO.delete(opinion);
	}
}