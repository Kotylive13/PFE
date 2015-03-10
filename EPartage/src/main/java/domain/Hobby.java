package domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Size;

@Entity
public class Hobby {
	
	@Id
	@Column (name = "nameH")
	@Size (max = 32)
	private String nameH;
	
	@ManyToMany(mappedBy="hobbys")
	private Set<User> users;

	public String getNameH() {
		return nameH;
	}

	public void setNameH(String nameH) {
		this.nameH = nameH;
	}

}
