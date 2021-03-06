package domain;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
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
import javax.persistence.OrderBy;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * This class corresponds to the user's publication
 * @author Koty
 *
 */

@Entity
public class Publication implements Comparable<Publication>{
	
	@Id
	@GeneratedValue( strategy = GenerationType.AUTO )
	@Column (name = "ID_PUB")
	private Integer id;

	@Column ( name = "TITLE" )
	@Size (max = 32)
	@NotEmpty(message = "Veuillez saisir un titre !")
	@NotNull
	private String title;
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateP;
	
	@Column ( name = "CONTENT", columnDefinition="TEXT")
	@NotEmpty(message = "Veuillez saisir un contenu !")
	@NotNull
	private String content;
	
	@ManyToOne (fetch = FetchType.EAGER)
	@JoinColumn (name = "author", nullable = false)
	@NotNull
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
	
	@OneToMany(mappedBy = "publication", fetch = FetchType.EAGER, cascade=CascadeType.REMOVE)
	@OrderBy("dateC DESC")
	private Set<Comment> comments = new LinkedHashSet<Comment>();
	
	@OneToMany(mappedBy = "publication", fetch = FetchType.EAGER, cascade=CascadeType.REMOVE)
	@Where(clause = "value = 'good'")
	private List<Opinion> goodOpinions;
	
	@OneToMany(mappedBy = "publication", fetch = FetchType.EAGER, cascade=CascadeType.REMOVE)
	@Where(clause = "value = 'bad'")
	private List<Opinion> badOpinions;
	
	@OneToMany(mappedBy = "publication", fetch = FetchType.EAGER, cascade=CascadeType.REMOVE)
	private List<PublicationFile> files;

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

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	public List<PublicationFile> getFiles() {
		return files;
	}

	public void setFiles(List<PublicationFile> files) {
		this.files = files;
	}

	@Override
	public int compareTo(Publication p) {
		return p.dateP.compareTo(this.dateP);
	}

	public List<Opinion> getGoodOpinions() {
		return goodOpinions;
	}

	public void setGoodOpinions(List<Opinion> goodOpinions) {
		this.goodOpinions = goodOpinions;
	}

	public List<Opinion> getBadOpinions() {
		return badOpinions;
	}

	public void setBadOpinions(List<Opinion> badOpinions) {
		this.badOpinions = badOpinions;
	}
}
