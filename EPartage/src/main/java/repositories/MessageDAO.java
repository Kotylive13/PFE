package repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import domain.IdMessage;
import domain.Message;

public interface MessageDAO extends JpaRepository<Message, IdMessage>{

}
