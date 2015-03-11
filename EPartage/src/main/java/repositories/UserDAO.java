package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import domain.User;

public interface UserDAO extends JpaRepository<User, Integer> {


	//public final static String FIND_BY_EMAIL_PASSWORD_QUERY = "SELECT u FROM USER u where u.email = ?1 AND u.password = ?2";
	
	/*"SELECT u "
	+ "FROM User u, Student s WHERE u.id_u = s.id_u"
	+ "AND (u.email = :email OR s.numStudent = :email)"
	+ " AND u.password = :password";*/
	
//	@Query("SELECT u FROM User u where u.email = ?1 AND u.password = ?2")
//	User findByEmailAndPassword(String email, String password);

//	public final static String FIND_BY_LOGIN_PASSWORD_QUERY = "SELECT u "
//			+ " FROM User u, Student s " + " WHERE (u.email = :login OR s.numStudent = :login) "
//			+ " AND u.password = :password " 
//			+ " AND u.id_u = s.id_u ";
	public final static String FIND_BY_LOGIN_PASSWORD_QUERY = "SELECT u "
			+ " FROM User u " + " WHERE u.email = :login "
			+ " AND u.password = :password ";

	@Query(FIND_BY_LOGIN_PASSWORD_QUERY)
	public User findByLogin(@Param("login") String login,
			@Param("password") String password);

}
