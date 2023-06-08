package cartitem;

import java.util.ArrayList;
import java.util.List;

import fr.lotus.entity.CartItem;
import fr.lotus.entity.CartItem;
import fr.lotus.model.implement.ClassDao;
import fr.lotus.model.implement.CrudDao;
import fr.lotus.model.interfaces.ICrudDao;
import fr.lotus.utils.Utils;

public class TCartItemRead {
	public static void main(String[] args) {
		Utils.trace("*************************** Begin ************************************\n");
		readMany();
//		readOne();
		Utils.trace("*************************** end ************************************\n");
		
	}
	//-------------------------------------------------------------------------------------------------	
	public static void readMany() {
		Utils.trace("=========================== read many  ===========================\n");

		List<ClassDao> cartItemList = new ArrayList<ClassDao>() ;
		CartItem cartItem = new CartItem() ;
		ICrudDao cartItemDao = new CrudDao(cartItem);
		try {
			cartItemList = cartItemDao.read();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			cartItemDao.close();
		}
		if ((cartItemList.size() >0  ) && (cartItemList != null)) {
			for (ClassDao classDao : cartItemList) {
				cartItem = (CartItem)classDao ; 
				Utils.trace("%s\n",cartItem);
			}
		}
		else
			Utils.trace(" cartItemList vide");
	}
	//-------------------------------------------------------------------------------------------------	
	public static void readOne() {
		Utils.trace("=========================== read One  ===========================\n");
		int cartItemId = 6;
		CartItem cartItem = new CartItem() ;
		ICrudDao cartItemDao = new CrudDao(cartItem);
		try {
			cartItem = (CartItem) cartItemDao.read(cartItemId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (cartItem != null )
			Utils.trace("%s\n",cartItem);
		else 
			Utils.trace("cartItem null\n");
		
	}
}
