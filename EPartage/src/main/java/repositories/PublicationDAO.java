package repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import domain.Publication;

public interface PublicationDAO extends JpaRepository<Publication, Integer> {

	/*final String PUBLICATIONS_BY_SUBCATEGORY = "SELECT pub FROM Publication pub WHERE pub.subcategory.group.name = :nameG"
			+ " AND pub.subcategory.category.idCategory.name = :nameC AND pub.subcategory.idSubcategory.subcategory := nameS ";

	@Query(PUBLICATIONS_BY_SUBCATEGORY)
	public List<Publication> findBySubcategory(@Param("nameG") String nameG,
			@Param("nameC") String nameC, @Param("nameS") String nameS);*/
}
