package user;

import fr.lotus.entity.User;
import fr.lotus.entity.User;
import fr.lotus.model.implement.CrudDao;
import fr.lotus.model.interfaces.ICrudDao;
import fr.lotus.utils.Utils;

public class TUserDelete {
	public static void main(String[] args) {
		
		Utils.trace("*************************** Begin ************************************\n");
// can delete all user profile except for Costumer
		
		int rowId =  20;
		User user = new User();
		ICrudDao userDao = new CrudDao(user);
		try {
			user = (User) userDao.read(rowId);
			if (user == null) 
				Utils.trace("Error : le user id:%d n'existe pas\n",rowId);
			else {
				Utils.trace("Last time seen %d %s\n",rowId,user);
				userDao.delete(user );
				user = (User) userDao.read(rowId);
				if (user != null)
					Utils.trace("Error not remove\n");
				else
					Utils.trace("remove ok\n");
			}
		} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}finally {
			userDao.close();
		}
		
		Utils.trace("*************************** end ************************************\n");
		
	}
}
