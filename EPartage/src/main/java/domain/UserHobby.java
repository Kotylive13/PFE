package domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserHobby {
	
	@Id
	private IdUserHobby idUserHobby;
	
	public UserHobby(){}
	
	public IdUserHobby getIdUserHobby() {
		return idUserHobby;
	}

	public void setIdUserHobby(IdUserHobby idUserHobby) {
		this.idUserHobby = idUserHobby;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idUserHobby == null) ? 0 : idUserHobby.hashCode());
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
		UserHobby other = (UserHobby) obj;
		if (idUserHobby == null) {
			if (other.idUserHobby != null)
				return false;
		} else if (!idUserHobby.equals(other.idUserHobby))
			return false;
		return true;
	}
	
	
	
}
