package fr.emile.test;

import fr.emile.entity.BankCard;
import fr.emile.model.implement.BankCardDao;
import fr.emile.model.interfaces.IBankCardDao;
import fr.emile.utils.DataTest;
import fr.emile.utils.Utils;

//*************** test result *********************************************************************
//check  retreive card from data base : ok 
//check décrypt data : ok
//*************** test result *********************************************************************



public class TBankCardRead {
public static void main(String[] args) {
	int cardId = 6;
	Utils.trace("------------- start ----------------------");
	BankCard myBankCard = new BankCard();
	
	
	Utils.trace(myBankCard.toString());
	
	IBankCardDao myBankCardDao = new BankCardDao();
	try {
		
		myBankCard = myBankCardDao.read(cardId);
	} catch (Exception e) {
		Utils.trace("catch myBankCardDao.create(myBankCard) ");
		Utils.trace(e.toString());
	}finally {
		Utils.trace(myBankCard.toString());
		
	}
	Utils.trace(myBankCard.toString());
	Utils.trace("------------- End ----------------------");

	}
}
