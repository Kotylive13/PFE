package domain;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@Entity
@Table(name = "Subcategory")
public class Subcategory {

	@EmbeddedId
	private IdSubcategory idSubcategory = new IdSubcategory();
	
	@MapsId("group")
	@ManyToOne()
	@JoinColumn(name = "nameG")
	private Group group;
	
	@ManyToOne()
	@JoinColumns({
		@JoinColumn(name = "nameC", referencedColumnName = "nameC", insertable = false, updatable = false),
		@JoinColumn(name = "nameG", referencedColumnName = "nameG", insertable = false, updatable = false)
	})
	private Category category;
	
	@OneToMany(mappedBy = "subcategory", fetch = FetchType.EAGER, cascade=CascadeType.REMOVE)
	@OrderBy("dateP DESC")
	private Set<Publication> publications = new LinkedHashSet<Publication>();

	public IdSubcategory getIdSubcategory() {
		return idSubcategory;
	}

	public void setIdSubcategory(IdSubcategory idSubcategory) {
		this.idSubcategory = idSubcategory;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Set<Publication> getPublications() {
		return publications;
	}

	public void setPublications(Set<Publication> publications) {
		this.publications = publications;
	}

}
