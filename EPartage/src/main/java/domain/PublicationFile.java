package domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.validation.constraints.Size;

@Entity
public class PublicationFile {

	@EmbeddedId
	private IdPublicationFile idPublicationFile;
	
	@Column ( name = "TITLE" )
	@Size (max = 32)
	private String title;
	
	@Lob @Basic(fetch = FetchType.LAZY)
	@Column( name = "FILE", length=100000)
	private byte[] picture;

	public IdPublicationFile getIdPublicationFile() {
		return idPublicationFile;
	}

	public void setIdPublicationFile(IdPublicationFile idPublicationFile) {
		this.idPublicationFile = idPublicationFile;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public byte[] getPicture() {
		return picture;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}
}
