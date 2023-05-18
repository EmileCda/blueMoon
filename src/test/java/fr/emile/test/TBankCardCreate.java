package fr.emile.test;

import fr.emile.entity.BankCard;
import fr.emile.model.implement.BankCardDao;
import fr.emile.model.interfaces.IBankCardDao;
import fr.emile.utils.DataTest;
import fr.emile.utils.Utils;

//*************** test result *********************************************************************
//check create table bankcard
//encrypte card number and cryptogram
//save card in database
//*************** test result *********************************************************************



public class TBankCardCreate {
public static void main(String[] args) {
	
	Utils.trace("------------- start ----------------------");
	BankCard myBankCard = new BankCard();
	
	myBankCard = DataTest.genBankCard();
	
	IBankCardDao myBankCardDao = new BankCardDao();
	try {
		
		for(int index = 0 ; index < 10 ; index ++) {
			myBankCard = DataTest.genBankCard();
			myBankCard= myBankCardDao.create(myBankCard);
		}
	} catch (Exception e) {
		Utils.trace("catch myBankCardDao.create(myBankCard) ");
		Utils.trace(e.toString());
	}finally {
		Utils.trace(myBankCard.toString());
		
	}
	Utils.trace("------------- End ----------------------");

	}
}
