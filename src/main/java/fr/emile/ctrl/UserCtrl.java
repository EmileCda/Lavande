package fr.emile.ctrl;

import fr.emile.entity.User;
import fr.emile.model.CrudDao;
import fr.emile.model.UserDao;
import fr.emile.utils.Code;
import fr.emile.utils.Utils;

public class UserCtrl extends CrudCtrl implements IClassCtrl {

	public UserCtrl() {
		super(new User());
		// TODO Auto-generated constructor stub
	}

	@Override
	public User preWrite(Object object) {
		User user = (User) object;
		if (user != null) {
			user.setPasswordEncrpted(Code.encrypt(user.getPassword()));
			user.setEmail(user.getEmail().toLowerCase());

		}

		return user;
	}

	@Override
	public Object postWrite(Object object) {
		// TODO Auto-generated method stub
		return object;
	}

	@Override
	public Object preRead(Object object) {
		// TODO Auto-generated method stub
		return object;
	}

	@Override
	public User postRead(Object object) {
		User user = (User) object;
		if (user != null) {
			user.setPassword(Code.decrypt2String(user.getPasswordEncrpted()));
		}
		return user;
	}
	public User  read(String email) throws Exception {
		User user = new User(); 
		UserDao UserDao= new UserDao();
		user= UserDao.read(email.toLowerCase());
		user = (User) postRead(user);
		return user;
	}
	

}
