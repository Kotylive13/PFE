package repositories;

import java.util.List;

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
			+ " cf.idCommentFile.comment = :com "
			+ " ORDER BY cf.idCommentFile.comment";
	
	@Query(FIND_FILES_OF_COMMENT_QUERY)
	public List<CommentFile> findByComment(@Param("pub") Integer pub, @Param("com") Integer com);

}
