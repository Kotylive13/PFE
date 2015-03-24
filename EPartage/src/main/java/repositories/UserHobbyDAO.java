package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import domain.IdUserHobby;
import domain.UserHobby;


/**
 * 
 * Interface for data acces Userhobby for CRUD operations
 * @author 
 *
 */
public interface UserHobbyDAO extends JpaRepository<UserHobby, IdUserHobby> {
	
	
	final String FIND_HOBBIES_BY_ID_USER ="SELECT h FROM UserHobby h WHERE h.idUserHobby.id = :idUser " ;
	
	@Query(FIND_HOBBIES_BY_ID_USER)
	public List<UserHobby> findAll(@Param("idUser") Integer idUser);
	
	

}
