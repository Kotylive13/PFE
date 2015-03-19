package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import domain.IdPublicationFile;
import domain.PublicationFile;

public interface PublicationFileDAO extends JpaRepository<PublicationFile, IdPublicationFile> {

	public final String FIND_FILES_OF_PUBLICATION_QUERY = 
			  " SELECT pf "
			+ " FROM PublicationFile pf " 
			+ " WHERE pf.idPublicationFile.publication = :pub AND "
			+ " pf.idPublicationFile.id = :id";
	
	@Query(FIND_FILES_OF_PUBLICATION_QUERY)
	public PublicationFile find(@Param("pub") Integer pub, @Param("id") Integer id);
}
