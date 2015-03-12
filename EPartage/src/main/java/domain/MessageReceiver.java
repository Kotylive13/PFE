package domain;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "MessageReceiver")
public class MessageReceiver {

	@EmbeddedId
	private IdMessageReceiver idMessageReceiver = new IdMessageReceiver();
	
	@MapsId("receiver")
	@ManyToOne
	@JoinColumn(name = "receiver")
	private User receiver;
	
	@Column(name="consult", columnDefinition="BOOLEAN default false")
	private boolean consult;

	public IdMessageReceiver getIdMessageReceiver() {
		return idMessageReceiver;
	}

	public void setIdMessageReceiver(IdMessageReceiver idMessageReceiver) {
		this.idMessageReceiver = idMessageReceiver;
	}

	public boolean isConsult() {
		return consult;
	}

	public void setConsult(boolean consult) {
		this.consult = consult;
	}

	public User getReceiver() {
		return receiver;
	}

	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}

}
