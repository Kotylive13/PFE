package domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.Size;

@Embeddable
public class IdOpinion implements Serializable{

	
	private static final long serialVersionUID = 1L;


	@GeneratedValue( strategy = GenerationType.AUTO )
	@Column (name = "ID_OP")
	@Size (max = 11)
	private Integer id;
	
	@Column (name = "ID_PUB", nullable = false)
	private Integer id_pub;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId_pub() {
		return id_pub;
	}

	public void setId_pub(Integer id_pub) {
		this.id_pub = id_pub;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((id_pub == null) ? 0 : id_pub.hashCode());
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
		IdOpinion other = (IdOpinion) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (id_pub == null) {
			if (other.id_pub != null)
				return false;
		} else if (!id_pub.equals(other.id_pub))
			return false;
		return true;
	}
}