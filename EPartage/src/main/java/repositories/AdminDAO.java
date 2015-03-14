package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import domain.Admin;
import domain.Status;
import domain.Student;

public interface AdminDAO extends JpaRepository<Admin, Integer> {

//	FIND_BY_LOGIN_PASSWORD_QUERY ----------------------------------------------
	
	public final static String FIND_BY_LOGIN_PASSWORD_QUERY = "SELECT a "
			+ " FROM Admin a " + " WHERE a.login = :login "
			+ " AND a.password = :password ";

	@Query(FIND_BY_LOGIN_PASSWORD_QUERY)
	public Admin findByLogin(@Param("login") String login,
			@Param("password") String password);
	
//	FIND_BY_STATUS_QUERY ------------------------------------------------------

	public final static String FIND_BY_STATUS_QUERY = "SELECT s "
			+ " FROM Student s, User u " + " WHERE u.status = :status "
			+ " AND u.id = s.id";

	@Query(FIND_BY_STATUS_QUERY)
	public List<Student> findByStatus(@Param("status") Status status);

//	VALIDATE_USER -------------------------------------------------------------
	
	public final static String VALIDATE_USER = "UPDATE User "
			+ " SET status = :status " + " WHERE id = :id ";
	
	@Modifying 
	@Query(VALIDATE_USER)
	public void validateUser(@Param("id") Integer id,
			@Param("status") Status status);
	
// FIND_USER_BY_ID ------------------------------------------------------
	
	public final static String FIND_USER_BY_ID = "SELECT s "
			+ " FROM Student s" + " WHERE s.id = :id ";

	@Query(FIND_USER_BY_ID)
	public Student findStudentById(@Param("id") Integer id);
	
// REFUSED_USER
	
	public final static String REFUSED_USER = "UPDATE User "
			+ " SET status = :status " + " WHERE id = :id ";
	
	@Modifying 
	@Query(REFUSED_USER)
	public void refusedUser(@Param("id") Integer id,
			@Param("status") Status status);
}
