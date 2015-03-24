package services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.PublicationDAO;
import domain.IdPublicationFile;
import domain.Publication;
import domain.PublicationFile;
import domain.PublicationForm;

/**
 * Service for Publication entity CRUD
 * 
 * @author Asma
 *
 */

@Service
@Transactional
public class PublicationService {

	@Autowired
	private PublicationDAO publicationDAO;
	@Autowired
	PublicationFileService publicationFileService;
	@Autowired
	OpinionService opinionService;

	public Publication save(Publication publication) {
		return publicationDAO.save(publication);
	}

	public Publication find(Integer id){
		return publicationDAO.findOne(id);
	}

	public void constructAndSave(PublicationForm pf) {

		Publication p = new Publication();
		p.setAuthor(pf.getAuthor());
		p.setDateP(pf.getDateP());
		p.setSubcategory(pf.getSubcategory());
		p.setTitle(pf.getTitle());
		p.setContent(pf.getContent());
		int idPub = publicationDAO.save(p).getId();

		if (pf.getFile() != null) {
			PublicationFile pubFile = new PublicationFile();
			IdPublicationFile idpf = new IdPublicationFile();
			idpf.setPublication(idPub);
			pubFile.setTitle(pf.getFileTile());
			pubFile.setIdPublicationFile(idpf);
			pubFile.setFile(pf.getFile());
			pubFile.setPublication(p);
			publicationFileService.save(pubFile);
		}

	}
}
