package fr.emile.model;

import org.hibernate.query.Query;

import fr.emile.common.IConstant;
import fr.emile.entity.User;
import fr.emile.utils.Utils;

public class UserDao extends CrudDao implements IConstant {



	public UserDao() {
		super(new User());
	}

//-------------------------------------------------------------------------------------------------	
	public User read(String email) {
		User user = null;
		try {
			String  stringQuery = "FROM User u WHERE u.email = :email";
			Query<User> query = this.getSession().createQuery(stringQuery, User.class);
			query.setParameter("email", email);
			user = query.uniqueResult(); 
		} catch (Exception e) {
			Utils.trace("catch  read(String email) %s \n", e.toString());

		} finally {
		}
		return user;
	}
	


}
