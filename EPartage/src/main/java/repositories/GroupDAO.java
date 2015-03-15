package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import domain.Group;

public interface GroupDAO extends JpaRepository<Group, Integer> {
	
//	SAVE2 ----------------------------------------------
	
	public final static String SAVE2 = "insert into group (nameG, avatar, description) VALUES (?1, ?2, ?3)";

	@Query(value = SAVE2, nativeQuery = true)
	public void save2(String nameG, byte[] avatar, String description);

}


/*


CREATE TABLE `Group`(
nameG       Varchar (32) NOT NULL ,
avatar      longBlob ,
description Varchar (1024) ,
PRIMARY KEY (nameG)
)ENGINE=InnoDB;


*/