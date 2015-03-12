package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import domain.Student;
import domain.User;

public interface UserDAO extends JpaRepository<User, Integer> {

	/*FIND_BY_EMAIL_PASSWORD_QUERY*/
	
	public final String FIND_BY_EMAIL_PASSWORD_QUERY = 
			  " SELECT s "
			+ " FROM User u, Student s " 
			+ " WHERE u.email = :email "
			+ " AND u.password = :password "
			+ " AND u.id = s.id";

	@Query(FIND_BY_EMAIL_PASSWORD_QUERY)
	public Student findByEmailPass(@Param("email") String email,
			@Param("password") String password);

	/*FIND_BY_LOGIN_QUERY*/
	
	public final String FIND_BY_LOGIN_QUERY = 
			  " SELECT u "
			+ " FROM User u " 
			+ " WHERE u.email = :login ";
	
	@Query(FIND_BY_LOGIN_QUERY)
	public User findByLogin(@Param("login") String login);

}
