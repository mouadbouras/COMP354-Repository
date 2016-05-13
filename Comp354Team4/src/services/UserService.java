package services;

import data.UserDao;
import models.User;

public class UserService 
{
	private UserDao userDao = new UserDao();
	
	//get the user role, given the user id
	public User GetUser(int userId)
	{
		User user = userDao.GetUserGivenUserId(userId);
		return user;		
	}
}
