package repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import domain.IdSubcategory;
import domain.Subcategory;

public interface SubcategoryDAO extends JpaRepository<Subcategory, IdSubcategory> {

}
