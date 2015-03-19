package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import domain.CommentFile;
import domain.IdCommentFile;

public interface CommentFileDAO extends JpaRepository<CommentFile, IdCommentFile> {

	public final String FIND_FILES_OF_COMMENT_QUERY = 
			  " SELECT cf "
			+ " FROM CommentFile cf " 
			+ " WHERE cf.idCommentFile.publication = :pub AND "
			+ " cf.idCommentFile.comment = :com AND "
			+ " cf.idCommentFile.id = :id";
	
	@Query(FIND_FILES_OF_COMMENT_QUERY)
	public CommentFile find(@Param("pub") Integer pub, @Param("com") Integer com, @Param("id") Integer id);

}
