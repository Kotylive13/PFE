package domain;

import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;


/**
 * Class for publications with only one file (used as a model atributte for new publications)
 * @author Asma
 *
 */
public class PublicationForm {

	@NotBlank(message = "Veuillez saisir un titre !")
	private String title;

	private Date dateP;
	
	@NotBlank(message = "Veuillez saisir un contenu !")
	private String content;

	
	private Subcategory subcategory;

	
	private User author;

	@SuppressWarnings(value = { "" })
	private byte[] file;

	private String fileTile;
	
	
	

	public String getFileTile() {
		return fileTile;
	}

	public void setFileTile(String fileTile) {
		this.fileTile = fileTile;
	}

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
