package cartitem;

import fr.lotus.entity.CartItem;
import fr.lotus.entity.CartItem;
import fr.lotus.model.implement.CrudDao;
import fr.lotus.model.interfaces.ICrudDao;
import fr.lotus.utils.Utils;

public class TCartItemDelete {
	public static void main(String[] args) {
		
		Utils.trace("*************************** Begin ************************************\n");
		
		int rowId =  1;
		CartItem cartItem = new CartItem();
		ICrudDao cartItemDao = new CrudDao(cartItem);
		try {
			cartItem = (CartItem) cartItemDao.read(rowId);
			if (cartItem == null) 
				Utils.trace("Error : CartItem n'existe pas\n");
			else {
				Utils.trace("last time seen: %s\n",cartItem);
				cartItemDao.delete(cartItem );
				cartItem = (CartItem) cartItemDao.read(rowId);
				if (cartItem != null)	
					Utils.trace("Error : not remove\n");
				else
					Utils.trace("remove ok\n");
			}
		} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}finally {
			cartItemDao.close();
		}
		
		Utils.trace("*************************** end ************************************\n");
		
	}
}
