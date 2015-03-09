package domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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
	@Size (max = 11)
	private Integer id;

	@Column ( name = "TITLE" )
	@Size (max = 32)
	private String title;
	
	@Column ( name = "CONTENT" )
	@Size (max = 1024)
	private String content;
	
	@Column ( name = "AUTHOR", nullable = false)
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "ID_U")
	private User user;
	
	@Column ( name = "NAMEC", nullable = false)
	@OneToOne
	@JoinColumn (name = "NAMEC")
	private Category category;
	
	@Column ( name = "NAMEG", nullable = false)
	@OneToOne
	@JoinColumn (name = "NAMEG")
	private Group group;

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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}
}
