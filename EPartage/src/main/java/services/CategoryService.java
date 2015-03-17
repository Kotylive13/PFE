package services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domain.IdSubcategory;
import domain.Subcategory;
import repositories.SubcategoryDAO;

@Service
@Transactional
public class CategoryService {

	@Autowired
	private SubcategoryDAO subcategoryDAO;
	
	public Subcategory findOne(IdSubcategory idSubcategory) {
		return subcategoryDAO.findOne(idSubcategory);
	}
	
}
