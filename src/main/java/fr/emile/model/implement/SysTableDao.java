package fr.emile.model.implement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import fr.emile.entity.SysTable;
import fr.emile.entity.User;
import fr.emile.model.connect.DBConnect;
import fr.emile.model.interfaces.ISysTableDao;
import fr.emile.model.interfaces.IUserDao;
import fr.emile.utils.Utils;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class SysTableDao implements ISysTableDao {



	@Override
	public SysTable create(SysTable sysTable) throws Exception {
		Session session = DBConnect.getSession();
		Transaction transaction = null;
		try {
			Utils.trace("create");

			transaction = session.beginTransaction();
			session.save(sysTable);
//			session.persist(sysTable);
			transaction.commit();

		} catch (Exception e) {

			Utils.trace("catch create");
			Utils.trace(e.toString());

			if (transaction != null) {
				transaction.rollback();
			}

		} finally {
			this.closeSession( session);

		}
		return sysTable;
	}

	@Override
	public SysTable read(int id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public int update(SysTable sysTable) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int id) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public SysTable readByFunctionCode(int functionCode) throws Exception {

		Session session = DBConnect.getSession();
		SysTable mySysTable=  null ; 
		
		try {

			Query<SysTable> myQuery = session.createQuery(
					"FROM SysTable WHERE functionCode = :functionCode", 
					SysTable.class);
			
			myQuery.setParameter("functionCode",functionCode );

			mySysTable = myQuery.uniqueResult();

		} catch (Exception e) {
			Utils.trace("catch getByFunctionCode");
			Utils.trace(e.toString());
			

		} finally {
			this.closeSession( session);
		}
		return mySysTable;

	}	


	
	private void closeSession(Session session) {

// session will be close by the end of the application		
//		if (session != null && session.isOpen())
//			session.close();
		
	}
}
