package domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Message")
public class Message {

	@EmbeddedId
	private IdMessage idMessage = new IdMessage();
	
	@MapsId("sender")
	@ManyToOne
	@JoinColumn(name = "author")
	private User author;

	@OneToMany (fetch = FetchType.EAGER)
	@JoinTable(name="MessageReceiver",
	joinColumns={
			@JoinColumn(name="dateM", referencedColumnName = "dateM"),
			@JoinColumn(name="id_u", referencedColumnName = "author")},
	inverseJoinColumns=
			@JoinColumn(name="receiver", referencedColumnName = "id_u"))
	private List<User> receivers;

	@Column(name = "content")
	@Size (min = 2, max = 2048, message = "Le message doit contenir entre 2 et 2048 caractères")
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

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}
	
}
