package fr.emile.model.implement;

import java.util.List;
import java.util.Map;

import fr.emile.entity.BankCard;
import fr.emile.model.connect.DBConnect;
import fr.emile.model.interfaces.IBankCardDao;
import fr.emile.utils.Utils;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class BankCardDao implements IBankCardDao {

	@Override
	public BankCard create(BankCard bankCard) throws Exception {
		bankCard.encrypt();
		Session session = DBConnect.getSession();
		Transaction transaction = null;
		try {

			transaction = session.beginTransaction();
			session.save(bankCard);
			transaction.commit();

		} catch (Exception e) {

			Utils.trace("catch reate(BankCard bankCard)");
			Utils.trace(e.toString());
			if (transaction != null) {
				transaction.rollback();
			}

		} finally {
			this.closeSession( session);

		}
		return bankCard;
	}	

	@Override
	public BankCard read(int id) throws Exception {
			Session session = DBConnect.getSession();
			BankCard bankCard = null;
			try {
				bankCard= new BankCard();
				bankCard = session.find(BankCard.class, id);
				bankCard.decrypt();

			} catch (Exception e) {
				Utils.trace("catch Read");
				Utils.trace(e.toString());

			} finally {
				this.closeSession( session);
				Utils.trace(bankCard.toString());
			}

			return bankCard;
	}

	@Override
	public BankCard read(String string) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public List<BankCard> read() throws Exception{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(BankCard bankCard) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int id, boolean isDeleted) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(BankCard bankCard, boolean isDeleted) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int id) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(BankCard bankCard) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}
	
	private void closeSession(Session session) {

		// session will be close by the end of the application		
//				if (session != null && session.isOpen())
//					session.close();
				
			}
}
