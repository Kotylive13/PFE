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
	
	@OneToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "ID_PUB", nullable = false)
	private Publication publication;

	public IdOpinion getIdOpinion() {
		return idOpinion;
	}

	public void setIdOpinion(IdOpinion idOpinion) {
		this.idOpinion = idOpinion;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Publication getPublication() {
		return publication;
	}

	public void setPublication(Publication publication) {
		this.publication = publication;
	}
}
