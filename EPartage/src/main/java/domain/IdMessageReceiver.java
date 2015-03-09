package domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embeddable;

@Embeddable
public class IdMessageReceiver implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id_u;
	
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

	public boolean equals(Object obj) {
		return true;
	}
	
	public int hashCode() {
		return 1;
	}
	
}
