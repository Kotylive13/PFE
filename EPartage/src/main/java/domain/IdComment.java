package domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;

@Embeddable
public class IdComment implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "id_pub")
	private int publicationId;
	
	@Column(name = "id_com")
	@GeneratedValue
	private int num;

	public IdComment() {}

	public int getPublicationId() {
		return publicationId;
	}

	public void setPublicationId(int publicationId) {
		this.publicationId = publicationId;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + num;
		result = prime * result + publicationId;
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
		IdComment other = (IdComment) obj;
		if (num != other.num)
			return false;
		if (publicationId != other.publicationId)
			return false;
		return true;
	}
}
