package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import domain.User;

public interface UserDAO extends JpaRepository<User, Integer> {

//	public final static String FIND_BY_LOGIN_PASSWORD_QUERY = "SELECT * "
//			+ "FROM User u, Student s" + "WHERE u.id_u = s.id_u"
//			+ "AND (u.email = :login OR s.numStudent = :login)"
//			+ "AND u.password = :password";
//
//	@Query(FIND_BY_LOGIN_PASSWORD_QUERY)
//	public User findLogin(@Param("login") String login,
//			@Param("password") String password);

}
