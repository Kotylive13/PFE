package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import domain.Admin;

public interface AdminDAO extends JpaRepository<Admin, Integer>{
	
	public final static String FIND_BY_LOGIN_PASSWORD_QUERY = "SELECT a "
			+ " FROM Admin a " + " WHERE a.login = :login "
			+ " AND a.password = :password ";

	@Query(FIND_BY_LOGIN_PASSWORD_QUERY)
	public Admin findByLogin(@Param("login") String login,
			@Param("password") String password);

}
