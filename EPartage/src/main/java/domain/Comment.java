package domain;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "Comment")
public class Comment {
	
	@EmbeddedId
	private IdComment idComment;
	
	@MapsId("publication")
	@ManyToOne
	@JoinColumn(name = "id_pub")
	private Publication publication;

	@ManyToOne
	@JoinColumn(name = "author")
	private User author;
	
	@Column(name = "content", nullable = false)
	String content;

	public IdComment getIdComment() {
		return idComment;
	}

	public void setIdComment(IdComment idComment) {
		this.idComment = idComment;
	}

	public Publication getPublication() {
		return publication;
	}

	public void setPublication(Publication publication) {
		this.publication = publication;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
