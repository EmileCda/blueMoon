package fr.emile.model.implement;

import java.util.List;
import java.util.Map;

import fr.emile.entity.User;
import fr.emile.model.connect.DBConnect;
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
			transaction = session.beginTransaction();
			session.persist(user);
			transaction.commit();

		} catch (Exception e) {

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
