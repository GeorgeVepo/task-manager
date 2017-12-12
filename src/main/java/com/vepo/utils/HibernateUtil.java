package com.vepo.utils;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class HibernateUtil {

	private static Transaction transaction;
	private static Session session;

	private HibernateUtil() {
	}

	public static void openSession()  throws HibernateException{
		try {		
			session =  new Configuration().configure().buildSessionFactory().openSession();
		} catch (HibernateException ex) {
			throw new HibernateException(ex);
		}
	}

	public static void beginTransaction()  throws HibernateException{
		try {		
			transaction = session.beginTransaction();
		} catch (HibernateException ex) {
			throw new HibernateException(ex);
		}
	}

	public static void rollbackTransaction()
			throws HibernateException {
		try {
			if ( transaction != null && !transaction.wasCommitted() && !transaction.wasRolledBack() ) {
				transaction.rollback();
			}
		} catch (HibernateException ex) {
			throw new HibernateException(ex);
		}
	}
	
	public static void commitTransaction()  throws HibernateException{
		try {		
			transaction.commit();
		} catch (HibernateException ex) {
			throw new HibernateException(ex);
		}
	}

	public static void closeSession()  throws HibernateException{
		try {		
			transaction = null;
			session.close();
			session = null;
		} catch (HibernateException ex) {
			throw new HibernateException(ex);
		}
	}


	public static Transaction getTransaction() {
		return transaction;
	}

	public static Session getSession() {
		return session;
	}
	
	
	
}
