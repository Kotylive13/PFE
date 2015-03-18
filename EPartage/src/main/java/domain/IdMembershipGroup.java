package domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class IdMembershipGroup implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column (name = "id_u")
	private Integer id;
	
	@Column (name = "nameG")
	private String nameG;

	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNameG() {
		return nameG;
	}

	public void setNameG(String nameG) {
		this.nameG = nameG;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nameG == null) ? 0 : nameG.hashCode());
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
		IdMembershipGroup other = (IdMembershipGroup) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nameG == null) {
			if (other.nameG != null)
				return false;
		} else if (!nameG.equals(other.nameG))
			return false;
		return true;
	}
	

}
