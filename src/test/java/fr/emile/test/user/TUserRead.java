package user;

import java.util.ArrayList;
import java.util.List;

import fr.lotus.entity.User;
import fr.lotus.entity.User;
import fr.lotus.model.implement.ClassDao;
import fr.lotus.model.implement.CrudDao;
import fr.lotus.model.implement.UserDao;
import fr.lotus.model.interfaces.ICrudDao;
import fr.lotus.utils.Utils;

public class TUserRead {
	public static void main(String[] args) {
		Utils.trace("*************************** Begin ************************************\n");
		
//		readByEmail();
//		readMany();
		readOne();
		Utils.trace("*************************** end ************************************\n");
		
	}
	//-------------------------------------------------------------------------------------------------	
	public static void readByEmail() {
		Utils.trace("=========================== read readByEmail  ===========================\n");
		
		String email ="lazare.michel@neflier.be";
		User user = new User() ;
		UserDao userDao = new UserDao();
		try {
			user = (User) userDao.read(email);
		} catch (Exception e) {
			Utils.trace("catch readOne %s\n ", e.toString());
		}
		if (user != null )
			Utils.trace(user.toString());
		else 
			Utils.trace("user null\n");
		
		
	}
	//-------------------------------------------------------------------------------------------------	
	public static void readMany() {
		Utils.trace("=========================== read many  ===========================\n");

		List<ClassDao> userList = new ArrayList<ClassDao>() ;
		User user = new User() ;
		ICrudDao userDao = new CrudDao(user);
		try {
			userList = userDao.read();
		} catch (Exception e) {
			Utils.trace("catch readMany %s\n ", e.toString());
		}finally {
			userDao.close();
		}
		if ((userList.size() >0  ) && (userList != null)) {
			for (ClassDao classDao : userList) {
				user = (User)classDao ; 
				Utils.trace("%s\n",user); 
			}
		}
		else
			Utils.trace(" userList vide\n");
	}
	//-------------------------------------------------------------------------------------------------	
	public static void readOne() {
		Utils.trace("=========================== read One  ===========================\n");
		int userId = 12;
		User user = new User() ;
		ICrudDao userDao = new CrudDao(user);
		try {
			user = (User) userDao.read(userId);
		} catch (Exception e) {
			Utils.trace("catch readOne %s\n ", e.toString());
		}
		if (user != null )
			Utils.trace(user.toString());
		else 
			Utils.trace("user null\n");
		
	}
}
