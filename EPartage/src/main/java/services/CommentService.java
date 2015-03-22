package services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.CommentDAO;
import repositories.CommentFileDAO;
import domain.Comment;
import domain.CommentFile;
import domain.CommentForm;
import domain.IdComment;
import domain.IdCommentFile;


@Service
@Transactional
public class CommentService {
	
	@Autowired
	CommentDAO commentDAO;

	@Autowired
	CommentFileDAO commentFileDAO;
	
	public void reconstructAndSave(CommentForm commentForm){
		
		Comment comment = new Comment();
		
		comment.setAuthor(commentForm.getAuthor());
		comment.setDateC(commentForm.getDateC());
		comment.setPublication(commentForm.getPublication());
		
		comment.setContent(commentForm.getContent());
		IdComment idC=new IdComment();
		idC.setPublication(commentForm.getPublication().getId());
		comment.setIdComment(idC);
		
		
		//idC = commentDAO.save(comment).getIdComment();
		commentDAO.saveAndFlush(comment);

		Comment savedComment = commentDAO.findOne(idC);

		if (commentForm.getFile() != null) {
			
			CommentFile comFile = new CommentFile();
			IdCommentFile idCF = new IdCommentFile();
			idCF.setPublication(savedComment.getPublication().getId());
			idCF.setComment(savedComment.getIdComment().getNum());
			
			comFile.setIdCommentFile(idCF);
			comFile.setTitle(commentForm.getFileTile());
			comFile.setFile(commentForm.getFile());
			comFile.setPublication(savedComment.getPublication());
			comFile.setComment(savedComment);
			
			
			commentFileDAO.save(comFile);
		}
	}

}
