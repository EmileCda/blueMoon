package fr.emile.test;

import fr.emile.entity.BankCard;
import fr.emile.entity.User;
import fr.emile.model.implement.UserDao;
import fr.emile.model.interfaces.IUserDao;
import fr.emile.utils.DataTest;
import fr.emile.utils.Utils;

//*************** test result *********************************************************************
//check create table user
//Add simple user one by one without joint table ok  
//add user and his list au bankcard : ok
//
//*************** test result *********************************************************************



public class TUserCreate {
public static void main(String[] args) {
	
	Utils.trace("------------- start ----------------------");
	User myUser = new User();
	
	myUser = DataTest.genUser();
	Utils.trace(myUser.toString());
	
	BankCard myBankCard = new BankCard();

	myBankCard = DataTest.genBankCard();
	Utils.trace(myBankCard.toString());
	myUser.addBankCard(myBankCard);
	myBankCard = DataTest.genBankCard();
	Utils.trace(myBankCard.toString());
	myUser.addBankCard(myBankCard);
	myBankCard = DataTest.genBankCard();
	Utils.trace(myBankCard.toString());
	myUser.addBankCard(myBankCard);
	
	IUserDao myUserDao = new UserDao();
	try {
		Utils.trace(myUser.toString());
		myUser.encrypt();
		Utils.trace(myUser.toString());
		myUser= myUserDao.create(myUser);
		Utils.trace(myUser.toString());

	} catch (Exception e) {
		Utils.trace("catch myUserDao.add(myUser) ");
		e.printStackTrace();
	}finally {
		Utils.trace(myUser.toString());
		
	}
	Utils.trace("------------- End ----------------------");

	}
}
