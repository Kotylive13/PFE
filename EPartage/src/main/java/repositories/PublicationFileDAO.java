package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import domain.IdPublicationFile;
import domain.PublicationFile;

public interface PublicationFileDAO extends JpaRepository<PublicationFile, IdPublicationFile> {

	public final String FIND_FILES_OF_PUBLICATION_QUERY = 
			  " SELECT pf "
			+ " FROM PublicationFile pf " 
			+ " WHERE pf.idPublicationFile.idPublication = :id "
			+ " ORDER BY pf.idPublicationFile.id";
	
	@Query(FIND_FILES_OF_PUBLICATION_QUERY)
	public List<PublicationFile> findByPublication(@Param("id") Integer id);

}
