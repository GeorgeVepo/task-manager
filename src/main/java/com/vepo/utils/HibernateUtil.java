package com.vepo.utils;
import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;

public class HibernateUtil {

	private HibernateUtil() {
	}

	public static SessionFactory getSessionFactory() {
		try {

			return new Configuration().configure().buildSessionFactory();

		} catch (Throwable ex) {

			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}
}
