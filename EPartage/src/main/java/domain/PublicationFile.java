package domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.validation.constraints.Size;

/**
 * This class represents the file in the publication
 * @author Koty
 *
 */
@Entity
public class PublicationFile {

	@EmbeddedId
	private IdPublicationFile idPublicationFile;
	
	@MapsId("publication")
	@ManyToOne
	@JoinColumn(name = "ID_PUB")
	private Publication publication;
	
	/**
	 * The file's title
	 */
	@Column ( name = "TITLE" )
	@Size (max = 32)
	private String title;
	
	/**
	 * The file
	 */
	@Lob @Basic(fetch = FetchType.LAZY)
	@Column( name = "FILE", length=5000)
	private byte[] file;

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

	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}
}
