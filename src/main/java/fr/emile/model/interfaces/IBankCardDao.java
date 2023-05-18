package fr.emile.model.interfaces;

import java.util.List;
import java.util.Map;

import fr.emile.entity.BankCard;

public interface IBankCardDao {
	
	BankCard create(BankCard bankCard) throws Exception;
	BankCard read(int id) throws Exception;
	BankCard read(String codeCard) throws Exception;
	public List<BankCard> read() throws Exception ;

	int update(BankCard bankCard)throws Exception;
	int delete (int id,boolean isDeleted)throws Exception;
	int delete (BankCard bankCard,boolean isDeleted)throws Exception;
	int delete (int id)throws Exception;
	int delete (BankCard bankCard)throws Exception;
}
