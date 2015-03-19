package domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * This class corresponds to the user's publication
 * @author Koty
 *
 */

@Entity
public class Publication {
	
	@Id
	@GeneratedValue( strategy = GenerationType.AUTO )
	@Column (name = "ID_PUB")
	private Integer id;

	@Column ( name = "TITLE" )
	@Size (max = 32)
	private String title;
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateP;
	
	@Column ( name = "CONTENT", columnDefinition="TEXT")
	private String content;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "author", nullable = false)
	private User author;
	
	@ManyToOne
	@JoinColumns ({
		@JoinColumn (name = "nameS", referencedColumnName = "nameS"),
		@JoinColumn (name = "nameC", referencedColumnName = "nameC"),
		@JoinColumn (name = "nameG", referencedColumnName = "nameG")
	})
	private Subcategory subcategory;
	
	@OneToOne
	@JoinColumn (name = "NAMEG", nullable = false, insertable = false, updatable = false)
	private Group group;
	
	@OneToMany(mappedBy = "publication", fetch = FetchType.EAGER)
	private List<Comment> comments;
	
	@OneToMany(mappedBy = "publication", fetch = FetchType.EAGER)
	private List<Opinion> opinions;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

//	public Category getCategory() {
//		return category;
//	}
//
//	public void setCategory(Category category) {
//		this.category = category;
//	}

	public Subcategory getSubcategory() {
		return subcategory;
	}

	public void setSubcategory(Subcategory subcategory) {
		this.subcategory = subcategory;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<Opinion> getOpinions() {
		return opinions;
	}

	public void setOpinions(List<Opinion> opinions) {
		this.opinions = opinions;
	}

}
