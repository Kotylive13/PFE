package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.UserDAO;
import utilities.CryptPassword;
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
	
	public User findByLogin(String email, String password) {
		return userDao.findByLogin(email, password);
	}
	
	public User findByLogin(String email) {
		return userDao.findByLogin(email);
	}

	public void save(User user) {
		user.setPassword(CryptPassword.getCryptString(user.getPassword()));
		userDao.save(user);
	}
	
}
