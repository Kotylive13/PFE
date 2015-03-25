package repositories;


import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.User;

/**
 * Class for UserDao Testing
 * @author Asma
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/data.xml"})
@Transactional
public class UserDaoTest {
	
	@Autowired
	UserDAO userDAO;
	
	private User u1;
	
	@Before
	public void setUp() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date date = sdf.parse("21/12/2012");
		
		u1 = new User();
		u1.setFirstName("Yoann");
		u1.setLastName("M");
		u1.setAdress("44 bd du test unitaire");
		u1.setBirthDate(date);
		u1.setPassword("mdp");
		u1.setEmail("yoann.m@gmail.com");
		
		userDAO.save(u1);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void findByLoginTest() {
		
		Assert.notNull(userDAO.findByLogin(u1.getEmail()));
	}

}
