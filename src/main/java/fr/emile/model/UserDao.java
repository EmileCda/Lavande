package fr.lotus.model.implement;

import org.hibernate.query.Query;

import fr.lotus.common.IConstant;
import fr.lotus.entity.User;
import fr.lotus.utils.Utils;

public class UserDao extends CrudDao implements IConstant {

	private 		User user;  

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
			if (user != null)
				user.postRead();
		} catch (Exception e) {
			Utils.trace("catch  read(String email) %s \n", e.toString());

		} finally {
		}
		return user;
	}
	

//-------------------------------------------------------------------------------------------------	
	public  User getUser() {
		return user;
	}

//-------------------------------------------------------------------------------------------------	
	public void setUser(User user) {
		this.user = user;
	}

}
