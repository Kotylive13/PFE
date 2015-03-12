package domain;

import java.util.Arrays;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@Table(name = "Group")
public class Group {

	@Id
	@Column(name = "nameG")
	private String name;
	
	@Lob @Basic(fetch = FetchType.LAZY)
	@Column( name = "avatar", length=100000)
	private byte[] avatar;
	
	@Column(name = "description")
	private String description;
	
	@ManyToMany (mappedBy = "groups")
	private Set<User> members;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(avatar);
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((members == null) ? 0 : members.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Group other = (Group) obj;
		if (!Arrays.equals(avatar, other.avatar))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (members == null) {
			if (other.members != null)
				return false;
		} else if (!members.equals(other.members))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
