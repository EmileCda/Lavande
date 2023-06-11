package fr.emile.model;

import org.hibernate.query.Query;

import fr.emile.common.IConstant;
import fr.emile.ctrl.UserCtrl;
import fr.emile.entity.Costumer;
import fr.emile.entity.User;
import fr.emile.utils.Utils;

public class CostumerDao extends CrudDao implements IConstant {



	public CostumerDao() {
		super(new Costumer());
	}

//-------------------------------------------------------------------------------------------------	
	public Costumer read(String email) {
		Costumer costumerRetreive = null;
		User userRetreive = new User();
		User user= null;
		UserCtrl userCtrl = new UserCtrl() ;
		
		try {
				userRetreive= (User) userCtrl.read(email);
				if (userRetreive != null) {
					costumerRetreive  = (Costumer) this.read(userRetreive.getId());
					
				}
		
		} catch (Exception e) {
			Utils.trace("ereur read email %s\n", e.toString());
		}
		return costumerRetreive;
	}
	


}
