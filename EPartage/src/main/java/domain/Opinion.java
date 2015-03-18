package domain;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
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

	@MapsId("id_pub")
	@ManyToOne
	@JoinColumn(name = "id_pub")
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

	public Integer getAuthor() {
		return author;
	}

	public void setAuthor(Integer author) {
		this.author = author;
	}

	public Publication getPublication() {
		return publication;
	}

	public void setPublication(Publication publication) {
		this.publication = publication;
	}
	
}
