package services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.SubcategoryDAO;
import domain.IdSubcategory;
import domain.Subcategory;

@Service
@Transactional
public class SubcategoryService {

	
	@Autowired
	private SubcategoryDAO subcategoryDAO;
	
	@Autowired
	private PublicationService publicationService;	
	
	public Subcategory findOne(IdSubcategory idSubcategory) {
		return subcategoryDAO.findOne(idSubcategory);
	}

	public void save(Subcategory subcategory) {
		subcategoryDAO.save(subcategory);
	}
	
	public List<Subcategory> findAll() {
		return subcategoryDAO.findAll();
	}

	public List<Subcategory> findByGroupAndCategory(String group, String category) {
		return subcategoryDAO.findByGroupAndCategory(group, category);
	}

	public void delete(Subcategory deleteSubcategory) {
		subcategoryDAO.delete(deleteSubcategory);		
	}
}
