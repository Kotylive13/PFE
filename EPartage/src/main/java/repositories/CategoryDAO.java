package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import domain.Category;
import domain.IdCategory;

public interface CategoryDAO extends JpaRepository<Category, IdCategory> {
	
// FIND_BY_NAME_AND_GROUP ---------------------------------------------------------
	
	public final static String FIND_BY_NAME_AND_GROUP =  "SELECT c FROM Category c WHERE c.idCategory.name = :name AND c.idCategory.group = :group";
	
	@Query(FIND_BY_NAME_AND_GROUP)
	public Category findByNameAndGroup(@Param("name") String name, @Param("group") String group);

}
