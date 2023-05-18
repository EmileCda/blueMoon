package fr.emile.model.interfaces;

import java.util.List;
import java.util.Map;

import fr.emile.entity.BankCard;
import fr.emile.entity.User;

public interface IBankCardDao {
	
	public BankCard create(BankCard bankCard) throws Exception;
	public BankCard read(int id) throws Exception;
	public BankCard read(String codeCard) throws Exception;
	public List<BankCard> read(User user) throws Exception ;
	

	public int update(BankCard bankCard)throws Exception;
	public int delete (int id,boolean isDeleted)throws Exception;
	public int delete (BankCard bankCard,boolean isDeleted)throws Exception;
	public int delete (int id)throws Exception;
	public int delete (BankCard bankCard)throws Exception;
}
