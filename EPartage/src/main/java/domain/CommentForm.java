package domain;

import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;

public class CommentForm {
	

	private Date dateC;
	
	@NotBlank(message = "Veuillez saisir un contenu !")
	private String content;

	
	private Publication publication;

	
	private User author;

	
	private byte[] file;

	private String fileTile;


	public Date getDateC() {
		return dateC;
	}

	public void setDateC(Date dateC) {
		this.dateC = dateC;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}

	public String getFileTile() {
		return fileTile;
	}

	public void setFileTile(String fileTile) {
		this.fileTile = fileTile;
	}

	
	
}
