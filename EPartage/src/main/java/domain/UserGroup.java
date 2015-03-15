package domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class UserGroup extends Group {

	@Column(name = "ID_U")
	private Integer id;
	
}
