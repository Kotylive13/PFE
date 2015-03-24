package services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.PublicationFileDAO;
import domain.PublicationFile;


/**
 * Service for Publication entity CRUD
 * @author Asma
 *
 */

@Service
@Transactional
public class PublicationFileService {

	
	@Autowired
	private PublicationFileDAO publicationFileDAO;
	
	public void save(PublicationFile publicationFile){
		publicationFileDAO.save(publicationFile);
	}

	public PublicationFile find(int pub, int id) {
		return publicationFileDAO.find(pub, id);
	}
}
