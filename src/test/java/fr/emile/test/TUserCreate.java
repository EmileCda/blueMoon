package fr.emile.test;

import fr.emile.entity.User;
import fr.emile.model.implement.UserDao;
import fr.emile.model.interfaces.IUserDao;
import fr.emile.utils.DataTest;
import fr.emile.utils.Utils;

//*************** test result *********************************************************************
//check create table user
//
//
//
//*************** test result *********************************************************************



public class TUserCreate {
public static void main(String[] args) {
	
	Utils.trace("------------- start ----------------------");
	User myUser = new User();
	
	myUser = DataTest.genUser();
	
	IUserDao myUserDao = new UserDao();
	try {
		myUser.encrypt();
		myUserDao.create(myUser);
	} catch (Exception e) {
		Utils.trace("catch myUserDao.add(myUser) ");
		e.printStackTrace();
	}finally {
		Utils.trace(myUser.toString());
		
	}
	Utils.trace("------------- End ----------------------");

	}
}
