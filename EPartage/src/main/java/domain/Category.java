package domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Category")
public class Category {

	@EmbeddedId
	private IdCategory idCategory = new IdCategory();
	
	@MapsId("group")
	@ManyToOne ()
	@JoinColumn(name = "nameG")
	private Group group;

	@OneToMany(mappedBy="category", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	private List<Subcategory> subcategories;	

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

	public List<Subcategory> getSubcategories() {
		return subcategories;
	}

	public void setSubcategories(List<Subcategory> subcategories) {
		this.subcategories = subcategories;
	}

}
