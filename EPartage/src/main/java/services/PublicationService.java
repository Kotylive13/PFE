package services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.PublicationDAO;
import domain.Publication;


/**
 * Service for Publication entity CRUD
 * @author Asma
 *
 */

@Service
@Transactional
public class PublicationService {

	
	@Autowired
	private PublicationDAO publicationDAO;
	
	public void save(Publication publication){
		publicationDAO.save(publication);
	}
	
}
