package domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity

public class Hobby {

	@Id
	@Column(name = "nameH")
	@Size(max = 32)
	private String nameH;

	public Hobby() {

	}

	public Hobby(String nameH) {
		this.nameH = nameH;
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
		Hobby other = (Hobby) obj;
		if (nameH == null) {
			if (other.nameH != null)
				return false;
		} else if (!nameH.equals(other.nameH))
			return false;
		return true;
	}
}
