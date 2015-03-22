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
	private int publication;
	
	@Column(name = "id_com")
	private int comment;
	
	@Column(name = "id_cf")
	@GeneratedValue ( strategy = GenerationType.AUTO )
	private int id;

	public IdCommentFile() {}

	public int getPublication() {
		return publication;
	}

	public void setPublication(int publication) {
		this.publication = publication;
	}

	public int getComment() {
		return comment;
	}

	public void setComment(int comment) {
		this.comment = comment;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
