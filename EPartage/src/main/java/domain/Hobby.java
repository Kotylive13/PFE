package domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity
public class Hobby {
	
	@Id
	@Column (name = "nameH")
	@Size (max = 32)
	private String nameH;

	public String getNameH() {
		return nameH;
	}

	public void setNameH(String nameH) {
		this.nameH = nameH;
	}

}
