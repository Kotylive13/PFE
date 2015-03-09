package domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class IdMessageReceiver implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id_u;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateM;
	
	private Integer receiver;
	
	public Integer getId_u() {
		return id_u;
	}

	public void setId_u(Integer id_u) {
		this.id_u = id_u;
	}

	public Date getDateM() {
		return dateM;
	}

	public void setDateM(Date dateM) {
		this.dateM = dateM;
	}

	public Integer getReceiver() {
		return receiver;
	}

	public void setReceiver(Integer receiver) {
		this.receiver = receiver;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IdMessageReceiver other = (IdMessageReceiver) obj;
		if (dateM == null) {
			if (other.dateM != null)
				return false;
		} else if (!dateM.equals(other.dateM))
			return false;
		if (id_u == null) {
			if (other.id_u != null)
				return false;
		} else if (!id_u.equals(other.id_u))
			return false;
		if (receiver == null) {
			if (other.receiver != null)
				return false;
		} else if (!receiver.equals(other.receiver))
			return false;
		return true;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateM == null) ? 0 : dateM.hashCode());
		result = prime * result + ((id_u == null) ? 0 : id_u.hashCode());
		result = prime * result
				+ ((receiver == null) ? 0 : receiver.hashCode());
		return result;
	}
	
}
