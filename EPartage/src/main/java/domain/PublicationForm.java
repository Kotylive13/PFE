package domain;

import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;

public class PublicationForm {

	@NotBlank
	private String title;
	@NotBlank
	private Date dateP;
	@NotBlank
	private String content;
	@NotBlank
	private Subcategory subcategory;
	@NotBlank
	private User author;

	private byte[] file;

	public PublicationForm() {

	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getDateP() {
		return dateP;
	}

	public void setDateP(Date dateP) {
		this.dateP = dateP;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Subcategory getSubcategory() {
		return subcategory;
	}

	public void setSubcategory(Subcategory subcategory) {
		this.subcategory = subcategory;
	}

	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}

}
