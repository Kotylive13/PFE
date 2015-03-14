package repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import domain.Hobby;

public interface HobbyDAO extends JpaRepository<Hobby, String> {
	
}
