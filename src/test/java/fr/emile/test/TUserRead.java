package fr.emile.test;

import fr.emile.entity.User;
import fr.emile.model.implement.UserDao;
import fr.emile.model.interfaces.IUserDao;
import fr.emile.utils.DataTest;
import fr.emile.utils.Utils;

//*************** test result *********************************************************************
//check read user by id (ok)
//check decript password : ok 
//check retreive bank card list : ok 
// chech decript bank card list : ok 
////*************** test result *********************************************************************



public class TUserRead {
public static void main(String[] args) {
	
	Utils.trace("------------- start ----------------------");
	int userId = 3 ;
	User myUser = new User();
	
	IUserDao myUserDao = new UserDao();
	try {
		myUser = myUserDao.read(userId);
	} catch (Exception e) {
		Utils.trace("catch myUserDao.add(myUser) ");
		e.printStackTrace();
	}finally {
		Utils.trace(myUser.toString());
		myUser.decrypt();
		Utils.trace(myUser.toString());
		
	}
	Utils.trace("------------- End ----------------------");

	}
}
