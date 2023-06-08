package fr.lotus.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public final class DBConnect {
	private static DBConnect instance;
	private static SessionFactory sessionFactory;
	private static Session session;
	private static int nbOpenSession;
	private static boolean verboseMode = false;

	private DBConnect() {
		if (sessionFactory == null) {
			sessionFactory = new Configuration().configure().buildSessionFactory();
		}
	}

	public static Session getSession() {

		if (DBConnect.isVerboseMode())
			Utils.trace("**** request to connect and open session : %d ****\n", DBConnect.nbOpenSession);

		if (instance == null) {
			if (DBConnect.isVerboseMode())
				Utils.trace("**** reel connect : open session %d ****\n", DBConnect.nbOpenSession);
			instance = new DBConnect();
		}

		if (session == null || !session.isOpen()) {
			if (DBConnect.isVerboseMode())
				Utils.trace("****real open connection : %d ****\n", DBConnect.nbOpenSession);
			session = sessionFactory.openSession();
		}
		DBConnect.nbOpenSession++;

		return session;

	}

	public static int close() {

		if (DBConnect.isVerboseMode())
			Utils.trace("**** request to close : open connection : %d ****\n", DBConnect.nbOpenSession);

		DBConnect.nbOpenSession--;
		if (DBConnect.nbOpenSession <= 0) {

			if (session != null && session.isOpen()) {
				if (DBConnect.isVerboseMode())
					Utils.trace("**** real close open connection : %d ****\n", DBConnect.nbOpenSession);
				session.close();
			}
		}
		return DBConnect.nbOpenSession;

	}

//-----------------------------------------------getter /setter -----------------------------------

	public static DBConnect getInstance() {
		return DBConnect.instance;
	}

	public static void setInstance(DBConnect instance) {
		DBConnect.instance = instance;
	}

	public static SessionFactory getSessionFactory() {
		return DBConnect.sessionFactory;
	}

	public static void setSessionFactory(SessionFactory sessionFactory) {
		DBConnect.sessionFactory = sessionFactory;
	}

	public static int getNbOpenSession() {
		return DBConnect.nbOpenSession;
	}

	public static void setNbOpenSession(int nbOpenSession) {
		DBConnect.nbOpenSession = nbOpenSession;
	}

	public static void setSession(Session session) {
		DBConnect.session = session;
	}

	public static boolean isVerboseMode() {
		return DBConnect.verboseMode;
	}

	public static void setVerboseMode(boolean verboseMode) {
		DBConnect.verboseMode = verboseMode;
	}

}
