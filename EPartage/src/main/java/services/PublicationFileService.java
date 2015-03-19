package services;

import java.util.List;

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

	public List<PublicationFile> findByPublication(int parseInt) {
		return publicationFileDAO.findByPublication(parseInt);
	}
	
}
