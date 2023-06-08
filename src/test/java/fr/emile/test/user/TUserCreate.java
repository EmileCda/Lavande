package user;

import fr.lotus.entity.User;
import fr.lotus.model.implement.CrudDao;
import fr.lotus.model.interfaces.ICrudDao;
import fr.lotus.utils.DataTest;
import fr.lotus.utils.Utils;

public class TUserCreate {
	public static void main(String[] args) {
		
		Utils.trace("*************************** Begin ************************************\n");
		int maxIndex = 10;
		User user = DataTest.genUser() ;
		User userAdded = new User() ;
		ICrudDao userDao = new CrudDao(user);
		
		try {
			for (int index = 0 ; index < maxIndex; index++) {

				user = DataTest.genUser() ;		// generate random user
				userAdded = (User) userDao.create(user); // create user in database
				if (userAdded != null)
					Utils.trace("%s\n",userAdded);
				else
					Utils.trace("userAdded is null\n");
			}
		} catch (Exception e) {
			Utils.trace("catch create %s\n ", e.toString());
		}finally {
			userDao.close();
		}
		
		
		
		Utils.trace("*************************** end ************************************\n");
		
	}
}
