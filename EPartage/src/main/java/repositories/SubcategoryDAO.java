package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import domain.IdSubcategory;
import domain.Subcategory;

public interface SubcategoryDAO extends JpaRepository<Subcategory, IdSubcategory> {

	
// FIND_BY_GROUP_AND_CATEGORY_QUERY ---------------------------------------------------------

	public final static String FIND_BY_GROUP_AND_CATEGORY_QUERY =  "SELECT s FROM Subcategory s "
			+ "WHERE s.idSubcategory.group = :group "
			+ "AND s.idSubcategory.category = :category";
	
	@Query(FIND_BY_GROUP_AND_CATEGORY_QUERY)
	public List<Subcategory> findByGroupAndCategory(@Param("group") String groupe, @Param("category") String category);

}
