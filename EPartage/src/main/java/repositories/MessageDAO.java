package repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import domain.IdMessage;
import domain.Message;

public interface MessageDAO extends JpaRepository<Message, IdMessage>{

//	FIND_ALL_SENT_MESSAGES_BY_USER_ID_QUERY -----------------------------------
	
	public final String FIND_ALL_SENT_MESSAGES_BY_USER_ID_QUERY = 
			  " SELECT m "
			+ " FROM Message m " 
			+ " WHERE m.idMessage.sender = :id "
			+ " ORDER BY m.idMessage.dateM DESC ";
	
	@Query(FIND_ALL_SENT_MESSAGES_BY_USER_ID_QUERY)
	public List<Message> findAllSentMessagesByUserId(@Param("id") Integer id);
	
//	FIND_ALL_RECEIVED_MESSAGES_BY_USER_ID -------------------------------------
	
	public final String FIND_ALL_RECEIVED_MESSAGES_BY_USER_ID = 
			  " SELECT m "
			+ " FROM Message m, MessageReceiver mr " 
			+ " WHERE mr.idMessageReceiver.receiver = :id "
			+ " AND m.idMessage.sender = mr.idMessageReceiver.id_u "
			+ " AND m.idMessage.dateM = mr.idMessageReceiver.dateM "
			+ " ORDER BY m.idMessage.dateM DESC ";
	
	@Query(FIND_ALL_RECEIVED_MESSAGES_BY_USER_ID)
	public List<Message> findAllReceivedMessages(@Param("id") Integer id);
	
//	GET_NB_UNCONSULTED_MESSAGES_BY_USER_ID ------------------------------------
	
	public final String GET_NB_UNCONSULTED_MESSAGES_BY_USER_ID = 
			  " SELECT COUNT(m) "
			+ " FROM MessageReceiver m " 
			+ " WHERE m.idMessageReceiver.receiver = :id "
			+ " AND m.consult = false ";
	
	@Query(GET_NB_UNCONSULTED_MESSAGES_BY_USER_ID)
	public int getNbOfUnconsultedMessages(@Param("id") Integer id);
	
//	SET_CONSULTED_MESSAGE -----------------------------------------------------
	
	public final String SET_CONSULTED_MESSAGE = 
			  " UPDATE MessageReceiver "
			+ " SET consult = :consult " 
			+ " WHERE idMessageReceiver.receiver = :receiver "
			+ " AND idMessageReceiver.id_u = :author "
			+ " AND idMessageReceiver.dateM = :date ";
	
	@Modifying
	@Query(SET_CONSULTED_MESSAGE)
	public void setConsultedMessage(
			@Param("author") Integer author, 
			@Param("date") Date date,
			@Param("receiver") Integer receiver,
			@Param("consult") boolean consult);
	
//	GET_MESSAGE_STATUS --------------------------------------------------------
	
	public final String GET_MESSAGE_STATUS = 
			  " SELECT consult "
			+ " FROM MessageReceiver "
			+ " WHERE idMessageReceiver.receiver = :receiver "
			+ " AND idMessageReceiver.id_u = :author "
			+ " AND idMessageReceiver.dateM = :date ";
	
	@Query(GET_MESSAGE_STATUS)
	public boolean getMessageStatus(
			@Param("author") Integer author,
			@Param("date") Date date,
			@Param("receiver") Integer receiver);
}
