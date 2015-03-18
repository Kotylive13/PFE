package repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import domain.Publication;

public interface PublicationDAO extends JpaRepository<Publication, Integer> {

}
