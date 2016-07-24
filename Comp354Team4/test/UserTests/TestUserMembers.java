package UserTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import java.text.ParseException;
 

import org.junit.Test;

import daos.UserDao;
import models.User;

public class TestUserMembers {
	
	private UserDao userDao = UserDao.getInstance();

	@Test
	//testing assignment of members
	public void testMembers() {

		System.out.println("Testing assignment of members");
		User manager = userDao.GetUserGivenUserId(11);
		User member1 = userDao.GetUserGivenUserId(12);
		User member2 = userDao.GetUserGivenUserId(13);


		List<User> members ;
		members = userDao.GetMembersGivenManagerId(11);

		
		assertEquals(members.get(0).getId(),member1.getId()   );
		assertEquals(members.get(1).getId(),member2.getId()   );
		
	}	
}
