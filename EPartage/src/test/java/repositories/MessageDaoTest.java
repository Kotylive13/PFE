package repositories;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import domain.Message;
import domain.User;

/**
 * Class for MessageDao Testing
 * @author
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/data.xml"})
@Transactional
public class MessageDaoTest {

	@Autowired
	MessageDAO messageDAO;
	
	@Autowired
	UserDAO userDAO;
	
	private User u1;
	private User u2;
	private User u3;
	private Message m1;
	private Date date;
	
	@Before
	public void setUp() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		date = sdf.parse("21/12/2012");
		
		u1 = new User();
		u1.setFirstName("Yoann");
		u1.setLastName("M");
		u1.setAdress("44 bd du test unitaire");
		u1.setBirthDate(date);
		u1.setPassword("mdp");
		u1.setEmail("yoann.m@gmail.com");
		
		u2 = new User();
		u2.setFirstName("u2");
		u2.setLastName("u2");
		u2.setAdress("44 bd du test unitaire");
		u2.setBirthDate(date);
		u2.setPassword("mdp");
		u2.setEmail("u2.u@gmail.com");
		
		u3 = new User();
		u3.setFirstName("u3");
		u3.setLastName("u3");
		u3.setAdress("44 bd du test unitaire");
		u3.setBirthDate(date);
		u3.setPassword("mdp");
		u3.setEmail("u3.u@gmail.com");
		
		userDAO.save(u1);
		userDAO.save(u2);
		
		m1 = new Message();
		m1.setAuthor(u1);
		m1.getIdMessage().setDateM(new Date());
		Set<User> receivers = new HashSet<User>();
		receivers.add(u2);
		m1.setReceivers(receivers);
		m1.setContent("Message de test");
		
		messageDAO.save(m1);
	}

	@After
	public void tearDown() throws Exception {
		//Nothing to do, rollback is automatic
	}
	
	/**
	 * User exists and he sent messages
	 */
	@Test
	public void findAllSentMessagesByUserIdTest0() {
		List<Message> expected = new ArrayList<Message>();
		expected.add(m1);

		List<Message> actual = messageDAO.findAllSentMessagesByUserId(u1.getId());

		Assert.assertTrue(expected.equals(actual));
	}
	
	/**
	 * User exists and he didn't send messages
	 */
	@Test
	public void findAllSentMessagesByUserIdTest1() {		
		Assert.assertTrue(messageDAO.findAllSentMessagesByUserId(
				u2.getId()).isEmpty());
	}
	
	/**
	 * User doesn't exist in database
	 */
	@Test
	public void findAllSentMessagesByUserIdTest2() {
		Assert.assertTrue(messageDAO.findAllSentMessagesByUserId(
				u3.getId()).isEmpty());
	}
	
	/**
	 * User exists and he received messages
	 */
	@Test
	public void findAllReceivedMessagesTest0() {
		List<Message> expected = new ArrayList<Message>();
		expected.add(m1);

		List<Message> actual = messageDAO.findAllReceivedMessages(u2.getId());

		Assert.assertTrue(expected.equals(actual));
	}
	
	/**
	 * User exists and he didn't receive messages
	 */
	@Test
	public void findAllReceivedMessagesTest1() {
		Assert.assertTrue(messageDAO.findAllReceivedMessages(
				u1.getId()).isEmpty());
	}
	
	/**
	 * User doesn't exist in database
	 */
	@Test
	public void findAllReceivedMessagesTest2() {
		Assert.assertTrue(messageDAO.findAllReceivedMessages(
				u3.getId()).isEmpty());
	}
	
	/**
	 * User exists and he has non consulted messages
	 */
	@Test
	public void getNbOfUnconsultedMessagesTest0() {
		Assert.assertTrue(
				messageDAO.getNbOfUnconsultedMessages(u2.getId()) == 1);
	}
	
	/**
	 * User exists and he hasn't non consulted messages
	 */
	@Test
	public void getNbOfUnconsultedMessagesTest1() {
		Assert.assertTrue(
				messageDAO.getNbOfUnconsultedMessages(u1.getId()) == 0);
	}
	
	/**
	 * User doesn't exist in database
	 */
	@Test
	public void getNbOfUnconsultedMessagesTest2() {
		Assert.assertTrue(
				messageDAO.getNbOfUnconsultedMessages(u3.getId()) == 0);
	}
	
	/**
	 * Change message status from false to true
	 */
	@Test
	public void setConsultedMessageTest() {
		messageDAO.setConsultedMessage(
				m1.getIdMessage().getSender(), 
				m1.getIdMessage().getDateM(), 
				u2.getId(), true);
		
		Assert.assertTrue(messageDAO.getMessageStatus(
				m1.getIdMessage().getSender(), 
				m1.getIdMessage().getDateM(),
				u2.getId()));
	}
	
	/**
	 * Message status is false
	 */
	@Test
	public void getMessageStatus() {
		Assert.assertFalse(messageDAO.getMessageStatus(
				m1.getIdMessage().getSender(), 
				m1.getIdMessage().getDateM(),
				u2.getId()));
	}
}
