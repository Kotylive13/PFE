package domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Embeddable
public class IdCommentFile implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "id_pub")
	private Integer publication;
	
	@Column(name = "id_com", nullable = false)
	private Integer comment;
	
	@Column(name = "id_cf")
	@GeneratedValue ( strategy = GenerationType.AUTO )
	private Integer id;

	public IdCommentFile() {}

	public Integer getPublication() {
		return publication;
	}

	public void setPublication(Integer publication) {
		this.publication = publication;
	}

	public Integer getComment() {
		return comment;
	}

	public void setComment(Integer comment) {
		this.comment = comment;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final Integer prime = 31;
		Integer result = 1;
		result = prime * result + id;
		result = prime * result + comment;
		result = prime * result + publication;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IdCommentFile other = (IdCommentFile) obj;
		if (id != other.id)
			return false;
		if (comment != other.comment)
			return false;
		if (publication != other.publication)
			return false;
		return true;
	}
}
