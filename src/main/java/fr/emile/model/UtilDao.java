package fr.lotus.model.implement;

import org.hibernate.Session;
import org.hibernate.Transaction;


import fr.lotus.utils.DBConnect;


public abstract class UtilDao  {

	private Session session;
	private Transaction transaction;

	public UtilDao() {
		this.setSession(DBConnect.getSession());
	}


	

//-------------------------------------------------------------------------------------------------	
	public void beginTransaction() {

		this.setTransaction(this.getSession().beginTransaction());

	}

	public void rollBack() {

		if (this.getTransaction() != null) {
			this.getTransaction().rollback();
		}
	}

	public void commit() {

		this.getTransaction().commit();

	}
	
	public void close() {

		DBConnect.close();
		this.setSession(null);

	}
//-------------------Getter / setter----------------------------------------------------------

	public Session getSession() {
		return this.session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}


}
