package fr.emile.model.connect;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
public class DBConnect {
	
	private static DBConnect instance;
	private static SessionFactory sessionFactory;
	private static Session session;
	
	private DBConnect() {
		if(sessionFactory == null) {
			sessionFactory = new Configuration()
					.configure()
					.buildSessionFactory();
		}
	}

	public static Session getSession() {
		if (instance == null) {
			instance = new DBConnect();
		}
		
		if (session == null || !session.isOpen()) {
			session = sessionFactory.openSession();
		}
		
		return session;
		
	}

}
