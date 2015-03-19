package services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.CommentFileDAO;
import domain.CommentFile;


/**
 * Service for Publication entity CRUD
 * @author Asma
 *
 */

@Service
@Transactional
public class CommentFileService {

	
	@Autowired
	private CommentFileDAO commentFileDao;
	
	public void save(CommentFile commentFile){
		commentFileDao.save(commentFile);
	}

	public CommentFile find(int pub, int com, int id) {
		return commentFileDao.find(pub, com, id);
	}
	
}
