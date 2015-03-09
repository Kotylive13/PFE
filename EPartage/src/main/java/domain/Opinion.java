package domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * Represents the user's opinion
 * @author Koty
 *
 */

@Entity
public class Opinion {

	@Id
	@GeneratedValue( strategy = GenerationType.AUTO )
	@Column (name = "ID_OP")
	private Integer id;
	
	@Column ( name = "VALUE")
	private String value;
	
	@Column ( name = "AUTHOR", nullable = false)
	@OneToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "ID_U")
	private User user;
	
	@Column ( name = "ID_PUB", nullable = false)
	@OneToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "ID_PUB")
	private Publication publication;
}
