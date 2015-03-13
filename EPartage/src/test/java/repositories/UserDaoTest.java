package repositories;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

/**
 * Class for UserDao Testing
 * @author Asma
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/data.xml"})

public class UserDaoTest {
	
	@Autowired
	UserDAO userDAO;

	/*@Before
	public void setUp() throws Exception {
		
	
	}

	@After
	public void tearDown() throws Exception {
	}*/

	@Test
	public void findByLoginTest() {
		
		Assert.notNull(userDAO.findByLogin("asma@asma.com"));
	}

}
