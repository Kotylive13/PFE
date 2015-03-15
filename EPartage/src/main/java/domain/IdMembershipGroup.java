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
	private String nameH;

	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNameH() {
		return nameH;
	}

	public void setNameH(String nameH) {
		this.nameH = nameH;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nameH == null) ? 0 : nameH.hashCode());
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
		if (nameH == null) {
			if (other.nameH != null)
				return false;
		} else if (!nameH.equals(other.nameH))
			return false;
		return true;
	}
	

}
