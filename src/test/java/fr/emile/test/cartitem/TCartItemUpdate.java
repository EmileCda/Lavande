package cartitem;

import fr.lotus.entity.CartItem;
import fr.lotus.entity.CartItem;
import fr.lotus.model.implement.CrudDao;
import fr.lotus.model.interfaces.ICrudDao;
import fr.lotus.utils.Utils;

public class TCartItemUpdate {
	public static void main(String[] args) {
		
		Utils.trace("*************************** Begin ************************************\n");
		int rowId = 3;
		CartItem cartItem =  new CartItem();
		ICrudDao cartItemDao = new CrudDao(cartItem) ;
		try {
			cartItem = (CartItem) cartItemDao.read(rowId );
			if (cartItem == null )
				Utils.trace("CartItem null\n");
			else {
				Utils.trace("Before: %s\n",cartItem);

				// -------------------------- update ----------------------
				cartItem.setQuantity(1);
				cartItemDao.update(cartItem);
	
				cartItem = (CartItem) cartItemDao.read(rowId);
				if (cartItem != null )
					Utils.trace("After %s\n",cartItem);
				else
					Utils.trace("CartItem null\n");
			}

		} catch (

				Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}finally {
			cartItemDao.close();
		}
		
		
		Utils.trace("*************************** end ************************************\n");
		
	}
}
