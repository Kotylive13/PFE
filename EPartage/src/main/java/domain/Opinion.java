package domain;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.Size;

/**
 * Represents the user's opinion
 * @author Koty
 *
 */

@Entity
public class Opinion {

	@EmbeddedId
	private IdOpinion idOpinion;
	
	@Column ( name = "VALUE")
	@Size (max = 32)
	private String value;
	

	@Column(name = "AUTHOR", nullable = false)
	private Integer author;


}
