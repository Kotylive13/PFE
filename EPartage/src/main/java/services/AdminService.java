package services;

import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.AdminDAO;
import utilities.CryptPassword;
import domain.Admin;
import domain.Status;
import domain.Student;

@Service
@Transactional
public class AdminService {

	@Autowired
	private AdminDAO adminDao;
	
	/**
	 * All Admins
	 */
	public Collection<Admin> findAll() {
		return adminDao.findAll();
	}
	
	public void save(Admin admin) {
		admin.setPassword(CryptPassword.getCryptString(admin.getPassword()));
		adminDao.save(admin);
	}
	
	public Admin findByLogin(String login, String password) {
		return adminDao.findByLogin(login, password);
	}
	
	public List <Student> findByStatus(Status status) {
		return adminDao.findByStatus(status);
	}
	
	public void validateUser(Integer id) {
		adminDao.validateUser(id, Status.A);
	}
	
}