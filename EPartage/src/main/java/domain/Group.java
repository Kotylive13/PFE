package domain;

import java.util.List;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@Table(name ="`Group`")
public class Group {

	@Id
	@Column(name = "nameG")
	private String name;
	
	@Lob @Basic(fetch = FetchType.LAZY)
	@Column( name = "avatar", length=100000)
	private byte[] avatar;
	
	@Column(name = "description", columnDefinition="TEXT")
	private String description;
	
	@OneToMany(mappedBy="group", fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	private List<Category> categories;

	@ManyToMany (mappedBy = "groups", cascade=CascadeType.ALL)
	private Set<User> members;
	

	
	public Set<User> getMembers() {
		return members;
	}

	public void setMembers(Set<User> members) {
		this.members = members;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte[] getAvatar() {
		return avatar;
	}

	public void setAvatar(byte[] avatar) {
		this.avatar = avatar;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

}
