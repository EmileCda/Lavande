package fr.emile.model;

import org.hibernate.query.Query;

import fr.emile.common.IConstant;
import fr.emile.entity.Costumer;
import fr.emile.utils.Utils;

public class CostumerDao extends CrudDao implements IConstant {



	public CostumerDao() {
		super(new Costumer());
	}

//-------------------------------------------------------------------------------------------------	
	public Costumer read(String email) {
		Costumer costumer = null;
		try {
			String  stringQuery = "FROM costumer c WHERE c.email = :email";
			Query<Costumer> query = this.getSession().createQuery(stringQuery, Costumer.class);
			query.setParameter("email", email);
			costumer = query.uniqueResult(); 
		} catch (Exception e) {
			Utils.trace("catch  read(String email) %s \n", e.toString());

		} finally {
		}
		return costumer;
	}
	


}
