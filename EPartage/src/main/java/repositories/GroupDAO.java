package repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import domain.Group;

public interface GroupDAO extends JpaRepository<Group, Integer> {
	
	

}
