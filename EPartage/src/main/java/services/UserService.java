package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.UserDAO;
import domain.User;



@Service
@Transactional
public class UserService {
	
	@Autowired
	private UserDAO userDao;
	
	/**
	 * All Users
	 */
	public Collection<User> findAll() {
		return userDao.findAll();
	}
	
	
//	public User findLogin(String login, String password) {
//		return userDao.findLogin(login, password);
//	}
	
}
