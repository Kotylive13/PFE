package domain;

/**
 * Class representing a received message
 * @author 
 *
 */
public class ReceivedMessage {

	private Message message;
	
	private boolean consulted;

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public boolean isConsulted() {
		return consulted;
	}

	public void setConsulted(boolean consulted) {
		this.consulted = consulted;
	}
}
