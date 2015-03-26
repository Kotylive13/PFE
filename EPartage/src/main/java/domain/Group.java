package domain;

import java.util.Arrays;
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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@Table(name ="`Group`")
public class Group {

	@Id
	@Column(name = "nameG")
	@Size(max=32, message= "Le nom du groupe est trop long !")
	private String name;
	
	@Lob @Basic(fetch = FetchType.LAZY)
	@Column( name = "avatar")
	private byte[] avatar;
	
	@Column(name = "description", columnDefinition="TEXT")
	private String description;
	
	@OneToMany(mappedBy="group", fetch = FetchType.EAGER, cascade=CascadeType.REMOVE)
	private List<Category> categories;

	@ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.MERGE)
	@JoinTable(
		      name="MembershipGroup",
		      joinColumns={@JoinColumn(name="nameG", referencedColumnName="nameG")},
		      inverseJoinColumns={@JoinColumn(name="id_u", referencedColumnName="ID_U")})
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(avatar);
		result = prime * result
				+ ((categories == null) ? 0 : categories.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((members == null) ? 0 : members.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		
		if (obj instanceof Group) {
			Group group = (Group) obj;
			
			if (this.name.equals(group.name))
				return true;
		}
		return false;
	}

}
