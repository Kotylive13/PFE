package domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class IdUserHobby implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column (name = "nameH")
	private String nameH;
	
	@Column (name = "id_u")
	private Integer id;

	public String getNameH() {
		return nameH;
	}

	public void setNameH(String nameH) {
		this.nameH = nameH;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
