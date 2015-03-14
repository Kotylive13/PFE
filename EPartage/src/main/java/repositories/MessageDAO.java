package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import domain.IdMessage;
import domain.Message;

public interface MessageDAO extends JpaRepository<Message, IdMessage>{

//	FIND_ALL_SENT_MESSAGES_BY_USER_ID_QUERY -----------------------------------
	
	public final String FIND_ALL_SENT_MESSAGES_BY_USER_ID_QUERY = 
			  " SELECT m "
			+ " FROM Message m " 
			+ " WHERE m.idMessage.sender = :id ";
	
	@Query(FIND_ALL_SENT_MESSAGES_BY_USER_ID_QUERY)
	public List<Message> findAllSentMessagesByUserId(@Param("id") Integer id);
}
