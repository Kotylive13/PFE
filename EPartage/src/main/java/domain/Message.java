package domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Message")
public class Message {

	@EmbeddedId
	private IdMessage idMessage;

	@OneToMany
	@JoinTable(name="MessageReceiver",
	joinColumns={
			@JoinColumn(name="id_u"),
			@JoinColumn(name="dateM")},
	inverseJoinColumns=
			@JoinColumn(name="receiver"))
	private List<User> receivers;

	@Column(name = "content")
	private String content;

	public IdMessage getIdMessage() {
		return idMessage;
	}

	public void setIdMessage(IdMessage idMessage) {
		this.idMessage = idMessage;
	}

	public List<User> getReceivers() {
		return receivers;
	}

	public void setReceivers(List<User> receivers) {
		this.receivers = receivers;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}
