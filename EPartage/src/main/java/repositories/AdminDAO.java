package repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import domain.Admin;

public interface AdminDAO extends JpaRepository<Admin, Integer>{

}
