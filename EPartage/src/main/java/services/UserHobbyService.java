package services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.UserHobbyDAO;
import domain.UserHobby;


@Service
@Transactional
public class UserHobbyService {
	
	@Autowired
	UserHobbyDAO userHobbyDAO;
	
	public List<UserHobby> findAll(Integer idUser){
		return userHobbyDAO.findAll(idUser); 
	}

	
	
}
