package repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import domain.User;

public interface UserDAO extends JpaRepository<User, Integer>{

}
