package domain;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "CommentFile")
public class CommentFile {

	@EmbeddedId
	private IdCommentFile idCommentFile;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "file")
	private byte[] file;

	public IdCommentFile getIdCommentFile() {
		return idCommentFile;
	}

	public void setIdCommentFile(IdCommentFile idCommentFile) {
		this.idCommentFile = idCommentFile;
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
