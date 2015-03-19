package domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.Size;

@Embeddable
public class IdPublicationFile implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@GeneratedValue( strategy = GenerationType.AUTO )
	@Column (name = "ID_PF")
	@Size (max = 11)
	private Integer id;
	
	@Column(name = "ID_PUB", nullable = false)
	private Integer publication;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPublication() {
		return publication;
	}

	public void setPublication(Integer publication) {
		this.publication = publication;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((publication == null) ? 0 : publication.hashCode());
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
		IdPublicationFile other = (IdPublicationFile) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (publication == null) {
			if (other.publication != null)
				return false;
		} else if (!publication.equals(other.publication))
			return false;
		return true;
	}
}
