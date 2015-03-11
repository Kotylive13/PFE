package domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserHobby {
	
	@Id
	private IdUserHobby idUserHobby;
	
	public IdUserHobby getIdUserHobby() {
		return idUserHobby;
	}

	public void setIdUserHobby(IdUserHobby idUserHobby) {
		this.idUserHobby = idUserHobby;
	}
	
}
