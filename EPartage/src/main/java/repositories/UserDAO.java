package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import domain.Student;
import domain.User;

public interface UserDAO extends JpaRepository<User, Integer> {

//	FIND_BY_EMAIL_PASSWORD_QUERY ----------------------------------------------
	
	public final String FIND_BY_EMAIL_PASSWORD_QUERY = 
			  " SELECT s "
			+ " FROM User u, Student s " 
			+ " WHERE u.email = :email "
			+ " AND u.password = :password "
			+ " AND u.id = s.id";

	@Query(FIND_BY_EMAIL_PASSWORD_QUERY)
	public Student findByEmailPass(@Param("email") String email,
			@Param("password") String password);

//	FIND_BY_LOGIN_QUERY -------------------------------------------------------
	
	public final String FIND_BY_LOGIN_QUERY = 
			  " SELECT u "
			+ " FROM User u " 
			+ " WHERE u.email = :login ";
	
	@Query(FIND_BY_LOGIN_QUERY)
	public User findByLogin(@Param("login") String login);
	
//	FIND_BY_EMAIL_PASSWORD_QUERY ----------------------------------------------
	
	public final String FIND_BY_EMAIL_QUERY = 
			  " SELECT s "
			+ " FROM User u, Student s " 
			+ " WHERE u.email = :email "
			+ " AND u.id = s.id";

	@Query(FIND_BY_EMAIL_QUERY)
	public Student findByEmail(@Param("email") String email);
	
//	COUNT_NB_WAITING_USERS_QUERY ----------------------------------------------
	
	public final String COUNT_NB_WAITING_USERS_QUERY = 
			  " SELECT count(u) "
			+ " FROM User u " 
			+ " WHERE u.status = 'W'";

	@Query(COUNT_NB_WAITING_USERS_QUERY)
	public int nbWaitingUsers();

//	FIND_ALL_ACTIVE_USERS_QUERY ----------------------------------------------
	
	public final String FIND_ALL_ACTIVE_USERS_QUERY = 
			  " SELECT u "
			+ " FROM User u " 
			+ " WHERE u.status = 'A'";

	@Query(FIND_ALL_ACTIVE_USERS_QUERY)
	public List<User> findAllActive();

}
