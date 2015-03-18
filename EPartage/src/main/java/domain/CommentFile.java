package domain;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "CommentFile")
public class CommentFile {

	@EmbeddedId
	private IdCommentFile idCommentFile;
	
	@MapsId("publication")
	@ManyToOne
	@JoinColumn(name = "id_pub")
	private Publication publication;
	
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name = "id_com", referencedColumnName = "id_com",
					insertable=false, updatable=false),
		@JoinColumn(name = "id_pub", referencedColumnName = "id_pub",
					insertable=false, updatable=false)
	})
	private Comment comment;
	
	@Column(name = "title", nullable = false)
	private String title;
	
	@Lob
	@Column(name = "file", nullable = false)
	private byte[] file;

	public IdCommentFile getIdCommentFile() {
		return idCommentFile;
	}

	public void setIdCommentFile(IdCommentFile idCommentFile) {
		this.idCommentFile = idCommentFile;
	}

	public Publication getPublication() {
		return publication;
	}

	public void setPublication(Publication publication) {
		this.publication = publication;
	}

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}
}
