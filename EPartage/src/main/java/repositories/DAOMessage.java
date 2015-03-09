package repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import domain.IdMessage;
import domain.Message;

public interface DAOMessage extends JpaRepository<Message, IdMessage>{

}
