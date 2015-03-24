package services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.CommentDAO;
import repositories.CommentFileDAO;
import domain.Comment;
import domain.IdComment;


@Service
@Transactional
public class CommentService {
	
	@Autowired
	CommentDAO commentDAO;

	@Autowired
	CommentFileDAO commentFileDAO;
	
	public void save(Comment comment){
		IdComment idC=new IdComment();
		idC.setPublication(comment.getPublication().getId());
		comment.setIdComment(idC);
		commentDAO.saveAndFlush(comment);
	}

}
