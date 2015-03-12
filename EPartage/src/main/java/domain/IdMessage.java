package domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class IdMessage implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "dateM")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateM;
	
	@Column(name = "author")
	private Integer sender;
	
	public IdMessage() {}

	public Date getDateM() {
		return dateM;
	}

	public void setDateM(Date dateM) {
		this.dateM = dateM;
	}
	
	public Integer getSender() {
		return sender;
	}

	public void setSender(Integer sender) {
		this.sender = sender;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IdMessage other = (IdMessage) obj;
		if (dateM == null) {
			if (other.dateM != null)
				return false;
		} else if (!dateM.equals(other.dateM))
			return false;
		if (sender != other.sender)
			return false;
		return true;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateM == null) ? 0 : dateM.hashCode());
		result = prime * result + sender;
		return result;
	}
	
}
