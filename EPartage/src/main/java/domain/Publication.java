package domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * This class corresponds to the user's publication
 * @author Koty
 *
 */

@Entity
public class Publication {
	
	@Id
	@GeneratedValue( strategy = GenerationType.AUTO )
	@Column (name = "ID_PUB")
	private Integer id;

}
