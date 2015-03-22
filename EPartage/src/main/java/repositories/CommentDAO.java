package repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import domain.Comment;
import domain.IdComment;

public interface CommentDAO extends JpaRepository<Comment, IdComment> {

}
