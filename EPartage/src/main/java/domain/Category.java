package domain;

import java.util.Set;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Category")
public class Category {

	@EmbeddedId
	private IdCategory idCategory;
	
	@MapsId("group")
	@ManyToOne
	@JoinColumn(name = "nameG")
	private Group group;

	@OneToMany
	@JoinColumns({
		@JoinColumn(name = "nameG", referencedColumnName = "nameG"),
		@JoinColumn(name = "nameC_parent", referencedColumnName = "nameC")
	})
	private Set<Category> subcategories;

	public IdCategory getIdCategory() {
		return idCategory;
	}

	public void setIdCategory(IdCategory idCategory) {
		this.idCategory = idCategory;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public Set<Category> getSubcategories() {
		return subcategories;
	}

	public void setSubcategories(Set<Category> subcategories) {
		this.subcategories = subcategories;
	}
}
