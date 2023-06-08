package cartitem;

import fr.lotus.entity.Costumer;
import fr.lotus.entity.Item;
import fr.lotus.entity.CartItem;
import fr.lotus.model.implement.CrudDao;
import fr.lotus.model.interfaces.ICrudDao;
import fr.lotus.utils.DataTest;
import fr.lotus.utils.Utils;

public class TCartItemCreate {
	public static void main(String[] args) {
		
		Utils.trace("*************************** Begin ************************************\n");
		
//		displayOneCartItem() ;
		
		for (int costumerId = 1; costumerId < 5 ; costumerId++) {
			for (int itemId = 1; itemId < 5 ; itemId++) {
				createOne(costumerId,itemId);
			}
		}
		
		Utils.trace("*************************** end ************************************\n");
	
	}
	//-------------------------------------------------------------------------------------------------	
	public static void createOne(int costumerId, int itemId) {
		Utils.trace("************************** create one **********************************\n");
		CartItem cartItem = new CartItem();
		CartItem cartItemAdded = new CartItem();
		cartItem.setQuantity(Utils.randInt(1, 10));
		Item item = retrieveItem(itemId);
		Costumer costumer = retrieveCostumer(costumerId);
		
		cartItem.setItem(item);
		cartItem.setCostumer(costumer);

		costumer.addCartItem(cartItem);
		item.addCartItem(cartItem);
	
		ICrudDao cartItemDao = new CrudDao(cartItem);

		try {
			cartItemAdded = (CartItem) cartItemDao.create(cartItem); // create cartItem in database
			if (cartItemAdded != null)
				Utils.trace("%s\n",cartItemAdded);
			else
				Utils.trace("%s\n","cartItemAdded is null");
		} catch (Exception e) {
			Utils.trace("catch create : %s\n", e.toString());
		}finally {
			cartItemDao.close();
		}
	}
	//-------------------------------------------------------------------------------------------------	
	public static void displayOneCartItem() {
		Utils.trace("************************** Display one cartItem**********************************\n");

		CartItem cartItem = new CartItem() ;
		cartItem.setQuantity(2);
		cartItem.setItem(DataTest.genItem());
		cartItem.setCostumer(DataTest.genCostumer());
		
		Utils.trace("%s\n",cartItem);
	}
	
	//-------------------------------------------------------------------------------------------------	
	public static Costumer retrieveCostumer(int id) {
		
		Utils.trace("=========================== read One Costumer [%d]  ===========================\n",id);
		Costumer costumer = new Costumer() ;
		ICrudDao costumerDao = new CrudDao(costumer);

		try {
			costumer = (Costumer) costumerDao.read(id);
			if (costumer == null )
				Utils.trace("costumer is null" );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			costumerDao.close();
		}
		return  costumer;
	}
	//-------------------------------------------------------------------------------------------------	
	public static Item retrieveItem(int id) {
		
		Utils.trace("=========================== read One Item [%d]  ===========================\n",id);
		Item item = new Item() ;
		ICrudDao itemDao = new CrudDao(item);
		try {
			item = (Item) itemDao.read(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (item != null )
			Utils.trace("%s\n",item);
		else 
			Utils.trace("item null\n");
		
		return  item;
	}
}
