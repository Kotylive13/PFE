package domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

public class UserGroup extends Group {

	@Id
	@Column(name = "nameG")
	private String name;
	
}
