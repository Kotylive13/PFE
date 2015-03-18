package services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.CategoryDAO;
import repositories.SubcategoryDAO;
import domain.Category;
import domain.IdCategory;
import domain.IdSubcategory;
import domain.Subcategory;

@Service
@Transactional
public class CategoryService {

	
	@Autowired
	private CategoryDAO categoryDAO;
	
	@Autowired
	private SubcategoryDAO subcategoryDAO; //TODO
	
	public Subcategory findOne(IdSubcategory idSubcategory) {
		return subcategoryDAO.findOne(idSubcategory);
	}

	public void save(Category category) {
		categoryDAO.save(category);
	}

	public Category findOne(IdCategory idCategory) {
		return categoryDAO.findOne(idCategory);
	}
	
	public List<Category> findAll() {
		return categoryDAO.findAll();
	}
	
	public void delete(Category category){
		categoryDAO.delete(category);
	}
	
	public Category findByNameAndGroup(String name, String group){
		return categoryDAO.findByNameAndGroup(name, group);
	}
	

}
