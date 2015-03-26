package domain;

import java.text.DateFormat;
import java.util.HashSet;
import java.util.Set;

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
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Class representing a message
 * @author 
 *
 */
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
	private Set<User> receivers = new HashSet<User>();

	@Column(name = "content")
	@Pattern(regexp = "^[\\sa-zA-Z0-9ÀÂÇÈÉÊËÎÔÙÛàâçèéêëîïôöùû\\'\\.\\?\\!\\,\\;\\:\\(\\)\\[\\]\"\\-\\/\\{\\}]*$", 
		message = "Seul les caractères alphanumériques et de ponctuation sont admis")
	@Size (min = 2, max = 2048, message = "Le message doit contenir entre 2 et 2048 caractères")
	private String content;

	public IdMessage getIdMessage() {
		return idMessage;
	}

	public void setIdMessage(IdMessage idMessage) {
		this.idMessage = idMessage;
	}

	public Set<User> getReceivers() {
		return receivers;
	}

	public void setReceivers(Set<User> receivers) {
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

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		
		if (obj instanceof Message) {
			Message m = (Message) obj;
			
			if (this.idMessage.getSender().compareTo(m.idMessage.getSender()) == 0) {
				
				DateFormat mediumDateFormat = DateFormat.getDateTimeInstance(
						DateFormat.MEDIUM,
						DateFormat.MEDIUM);
				
				String date1 = mediumDateFormat.format(this.idMessage.getDateM());
				String date2 = mediumDateFormat.format(m.idMessage.getDateM());
				
				if (date1.compareTo(date2) == 0)
					return true;
			}
		}
		
		return false;
	}
	
}
