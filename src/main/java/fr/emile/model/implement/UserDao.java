package fr.emile.model.implement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import fr.emile.entity.BankCard;
import fr.emile.entity.User;
import fr.emile.model.connect.DBConnect;
import fr.emile.model.interfaces.IBankCardDao;
import fr.emile.model.interfaces.IUserDao;
import fr.emile.utils.Utils;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UserDao implements IUserDao {

	@Override
	public User create(User user) throws Exception {
		Session session = DBConnect.getSession();
		Transaction transaction = null;
		try {
			user.encrypt();
			Utils.trace(user.toString());
			transaction = session.beginTransaction();
			session.save(user);
			Utils.trace(user.toString());
			transaction.commit();
			IBankCardDao myBankCardDao = new BankCardDao();
			for (BankCard bankCard : user.getBankCardList()) {
				
				bankCard.setUserId(user.getId());
				myBankCardDao.create(bankCard);
			}

		} catch (Exception e) {

			Utils.trace("catch create");
			Utils.trace(e.toString());
			if (transaction != null) {
				transaction.rollback();
			}

		} finally {
			this.closeSession( session);

		}
		return user;
	}	

	@Override
	public User read(int id) throws Exception {
			Session session = DBConnect.getSession();
			User user = null;
			try {
				user= new User();
				user = session.find(User.class, id);
				user.decrypt();
				IBankCardDao myBankCardDao = new BankCardDao();
				List<BankCard> myBankCardList = new ArrayList<BankCard>();
				myBankCardList = myBankCardDao.read(user);
				user.addBankCard(myBankCardList);
			} catch (Exception e) {
				Utils.trace("catch Read");
				Utils.trace(e.toString());

			} finally {
				this.closeSession( session);
				Utils.trace(user.toString());
			}

			return user;
	}

	@Override
	public User read(String string) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> readByCity(String email) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, List<User>> readByCity() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> get() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(User user) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int id, boolean isDeleted) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(User user, boolean isDeleted) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int id) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(User user) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}
	private void closeSession(Session session) {

		// session will be close by the end of the application		
//				if (session != null && session.isOpen())
//					session.close();
				
			}
}
