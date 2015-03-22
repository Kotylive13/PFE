package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import domain.Group;

public interface GroupDAO extends JpaRepository<Group, Integer> {
	
	
//	MODIFY_QUERY --------------------------------------------------------------
	
	public final static String MODIFY_QUERY =  "UPDATE Group g SET g.name = :name, g.description = :description, g.avatar = :avatar WHERE g.name = :nameOld";

	@Query(MODIFY_QUERY)
	@Modifying
	public void modify(@Param("name") String name, @Param("description") String description, @Param("avatar") byte[] avatar, @Param("nameOld") String nameOld);
	
	
// FIND_BY_NAME_QUERY ---------------------------------------------------------
	
	public final static String FIND_BY_NAME_QUERY =  "SELECT g FROM Group g WHERE g.name = :name";
	
	@Query(FIND_BY_NAME_QUERY)
	public Group findGroupByName(@Param("name") String name);
	
	
// FIND_BY_KEYWORD_QUERY ---------------------------------------------------------
	
	public final static String FIND_BY_KEYWORD_QUERY =  "SELECT g FROM Group g WHERE g.name like %:keyword% OR g.description like %:keyword%";
		
	@Query(FIND_BY_KEYWORD_QUERY)
	public List<Group> findByKeyword(@Param("keyword") String keyword);

}
