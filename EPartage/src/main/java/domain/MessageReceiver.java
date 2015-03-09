package domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "MessageReceiver")
public class MessageReceiver {

	@EmbeddedId
	private IdMessageReceiver idMessageReceiver;
	
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

}
