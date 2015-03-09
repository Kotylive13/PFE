package domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class IdCommentFile implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "id_pub")
	private int pubblicationId;
	
	@Column(name = "id_com")
	private int commentId;
	
	@Column(name = "id_cf")
	private int commentFileId;

	public IdCommentFile() {}

	public int getPubblicationId() {
		return pubblicationId;
	}

	public void setPubblicationId(int pubblicationId) {
		this.pubblicationId = pubblicationId;
	}

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public int getCommentFileId() {
		return commentFileId;
	}

	public void setCommentFileId(int commentFileId) {
		this.commentFileId = commentFileId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + commentFileId;
		result = prime * result + commentId;
		result = prime * result + pubblicationId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IdCommentFile other = (IdCommentFile) obj;
		if (commentFileId != other.commentFileId)
			return false;
		if (commentId != other.commentId)
			return false;
		if (pubblicationId != other.pubblicationId)
			return false;
		return true;
	}
}
