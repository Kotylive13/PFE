package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import domain.IdOpinion;
import domain.Opinion;
import domain.User;

public interface OpinionDAO extends JpaRepository<Opinion, IdOpinion> {
	
//	FIND_BY_AUTHOR_AND_PUBLICATION_QUERY ----------------------------------------------
	
	public final static String FIND_BY_AUTHOR_AND_PUBLICATION_QUERY = "SELECT o "
			+ " FROM Opinion o " + " WHERE o.author = :author "
			+ " AND o.idOpinion.id_pub = :publication ";

	@Query(FIND_BY_AUTHOR_AND_PUBLICATION_QUERY)
	public Opinion findByAuthorAndPublication(@Param("author") User author,
			@Param("publication") int publication);
	
}
