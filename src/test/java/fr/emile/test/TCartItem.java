package fr.emile.test;

import java.util.ArrayList;
import java.util.List;

import fr.emile.ctrl.CrudCtrl;
import fr.emile.ctrl.StandardCrudCtrl;
import fr.emile.ctrl.UserCtrl;
import fr.emile.entity.Category;
import fr.emile.entity.Costumer;
import fr.emile.entity.Item;
import fr.emile.entity.CartItem;
import fr.emile.utils.Utils;

public class TCartItem {

	public static void main(String[] args) {
		Utils.trace("*************************** Begin ************************************\n");
		TCartItemUnitTest unitTest = new TCartItemUnitTest();
//		unitTest.createOne(3);
//		unitTest.createMany();
//		unitTest.readOne(1);
//		unitTest.readMany();
//		unitTest.update();
		unitTest.delete();
		Utils.trace("*************************** end ************************************\n");

	}

}

//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%Unit Test Object %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
class TCartItemUnitTest {

	private CrudCtrl ctrl;
	private int maxRetry = 10;

	public TCartItemUnitTest() {
		this.setCtrl(new StandardCrudCtrl(new CartItem()));
	}

//-------------------------------------------------------------------------------------------------
	public void update() {
		int cartItemId = 3;
		Utils.trace("=========================== Update [%d]===========================\n",cartItemId);
		CartItem cartItem = null;

		try {
			cartItem = (CartItem) this.getCtrl().read(cartItemId);
			if (cartItem == null)
				Utils.trace("Address null\n");
			else {
				Utils.trace("Before:\t%s\n", cartItem);

				// -------------------------- update ----------------------
				cartItem.setQuantity(cartItem.getQuantity()*2);
				this.getCtrl().update(cartItem);

				cartItem = (CartItem) this.getCtrl().read(cartItemId);
				if (cartItem != null)
					Utils.trace("After:\t%s\n", cartItem);
				else
					Utils.trace("CartItem null\n");
			}

		} catch (Exception e) {
			Utils.trace("catch delete %s\n", e.toString());
		}
	}

//-------------------------------------------------------------------------------------------------
	public void delete() {
		Utils.trace("=========================== Delete ===========================\n");
		int addressId = 2;
		CartItem cartItem = new CartItem();

		try {
			cartItem = (CartItem) this.getCtrl().read(addressId);
			if (cartItem == null)
				Utils.trace("Error : l'cartItem n'existe pas\n");
			else {
				Utils.trace("last time seen %s\n", cartItem);
				this.getCtrl().delete(cartItem);
				cartItem = (CartItem) this.getCtrl().read(addressId);

				if (cartItem != null)
					Utils.trace("Error not remove\n");
				else
					Utils.trace("remove ok\n");
			}
		} catch (Exception e) {
			Utils.trace("catch delete %s\n", e.toString());
		}

	}
	// -------------------------------------------------------------------------------------------------

	public void createOne() {
		Utils.trace("=========================== create One  ===========================\n");
		CartItem cartItem = new CartItem();
		Item item = getItem(1);
		Costumer costumer = getCostumer(1);

		cartItem = DataTest.genCartItem();
		cartItem.setCostumer(costumer);
		cartItem.setItem(item);
		try {
			this.getCtrl().create(cartItem);
			Utils.trace("%s\n", cartItem);
		} catch (Exception e) {
			Utils.trace("catch create %s\n", e.toString());
		}

	}
	// -------------------------------------------------------------------------------------------------

	public void createMany(int startCostumer,int maxCartItem) {
		Utils.trace("=========================== create many  ===========================\n");
		int maxItem = 59;
		int maxIndexCostumer= 10;
		CartItem cartItem = new CartItem();
		Item item = new Item();
		Costumer costumer = new Costumer() ;

		try {
			for (int indexCostumer = startCostumer; indexCostumer  <= maxIndexCostumer+startCostumer ; indexCostumer ++) {
				
				int maxCurrentCartItem = Utils.randInt(0, maxCartItem);
				costumer = getCostumer(indexCostumer);
				
				for (int indexCartItem = 1; indexCartItem <= maxCurrentCartItem; indexCartItem++) {

					cartItem = DataTest.genCartItem();
					item = getItem(Utils.randInt(1, maxItem));

					cartItem.setCostumer(costumer);
					cartItem.setItem(item);
					costumer.addCartItem(cartItem);
					item.addCartItem(cartItem);
					this.getCtrl().create(cartItem);
					Utils.trace("%s\n", cartItem);

				}
			}
		} catch (Exception e) {
			Utils.trace("catch createMany %s\n", e.toString());
		}
	}

	// -------------------------------------------------------------------------------------------------
	public void readMany() {
		Utils.trace("=========================== read many  ===========================\n");

		List<Object> cartItemList = new ArrayList<Object>();

		try {
			cartItemList = this.getCtrl().list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if ((cartItemList.size() > 0) && (cartItemList != null)) {
			for (Object object : cartItemList) {
				Utils.trace("%s\n", (CartItem) object);
			}
		} else
			Utils.trace("address null");
	}

//-------------------------------------------------------------------------------------------------	
	public void readOne(int id) {
		Utils.trace("=========================== read One [%d] ===========================\n",id);

		CartItem cartItem = new CartItem();

		try {
			for (int index = id; index < this.getMaxRetry() + id; index++) {
				cartItem = (CartItem) this.getCtrl().read(id);
				if (cartItem!= null)	break;
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (cartItem != null)
			Utils.trace("%s\n", cartItem);
		else
			Utils.trace("cartItem null\n");

	}
//-------------------------------------------------------------------------------------------------	

	public Category getCategory(int categoryId) {
		Category category = new Category();
		CrudCtrl categoryCtrl = new StandardCrudCtrl(new Category());

		try {
			category = (Category) categoryCtrl.read(categoryId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return category;
	}

//-------------------------------------------------------------------------------------------------	
	public Item getItem(int id) {
		Item item = new Item();
		CrudCtrl itemCtrl = new StandardCrudCtrl(new Item());

		try {
			for (int index = id; index < this.getMaxRetry() + id; index++) {
				item = (Item) itemCtrl.read(id);
				if (item != null)
					break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return item;
	}

//-------------------------------------------------------------------------------------------------	

	public Costumer getCostumer(int id) {

		Costumer costumer = new Costumer();
		UserCtrl costumerCtrl = new UserCtrl();
		try {
			for (int index = id; index < this.getMaxRetry() + id; index++) {
				costumer = (Costumer) costumerCtrl.read(id);
				if (costumer != null)
					break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return costumer;

	}

//-------------------------------------------------------------------------------------------------	
	public CrudCtrl getCtrl() {
		return this.ctrl;
	}

	public void setCtrl(CrudCtrl ctrl) {
		this.ctrl = ctrl;
	}

	public int getMaxRetry() {
		return maxRetry;
	}

	public void setMaxRetry(int maxRetry) {
		this.maxRetry = maxRetry;
	}
}
