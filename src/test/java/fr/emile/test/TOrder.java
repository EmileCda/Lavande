package fr.emile.test;

import java.util.ArrayList;
import java.util.List;

import fr.emile.ctrl.CrudCtrl;
import fr.emile.ctrl.StandardCrudCtrl;
import fr.emile.ctrl.UserCtrl;
import fr.emile.entity.Category;
import fr.emile.entity.Costumer;
import fr.emile.entity.Item;
import fr.emile.entity.Order;
import fr.emile.utils.Utils;

public class TOrder {

	public static void main(String[] args) {
		Utils.trace("*************************** Begin ************************************\n");
		TOrderUnitTest unitTest = new TOrderUnitTest();
//		unitTest.createOne();
//		unitTest.createMany();
//		unitTest.readOne(1);
//		unitTest.readMany();
//		unitTest.update();
		unitTest.delete();
		Utils.trace("*************************** end ************************************\n");

	}

}

//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%Unit Test Object %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
class TOrderUnitTest {

	private CrudCtrl ctrl;
	private int maxRetry = 10;

	public TOrderUnitTest() {
		this.setCtrl(new StandardCrudCtrl(new Order()));
	}

//-------------------------------------------------------------------------------------------------
	public void update() {
		int orderId = 3;
		Utils.trace("=========================== Update [%d]===========================\n",orderId);
		Order order = null;

		try {
			order = (Order) this.getCtrl().read(orderId);
			if (order == null)
				Utils.trace("Address null\n");
			else {
				Utils.trace("Before:\t%s\n", order);

				// -------------------------- update ----------------------
				order.setOrderNumber("**"+ order.getOrderNumber()+"**");
				this.getCtrl().update(order);

				order = (Order) this.getCtrl().read(orderId);
				if (order != null)
					Utils.trace("After:\t%s\n", order);
				else
					Utils.trace("Order null\n");
			}

		} catch (Exception e) {
			Utils.trace("catch delete %s\n", e.toString());
		}
	}

//-------------------------------------------------------------------------------------------------
	public void delete() {
		Utils.trace("=========================== Delete ===========================\n");
		int addressId = 2;
		Order order = new Order();

		try {
			order = (Order) this.getCtrl().read(addressId);
			if (order == null)
				Utils.trace("Error : l'order n'existe pas\n");
			else {
				Utils.trace("last time seen %s\n", order);
				this.getCtrl().delete(order);
				order = (Order) this.getCtrl().read(addressId);

				if (order != null)
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
		Order order = new Order();
		Order orderCrea = new Order();
//		OrderLine orderLine= getOrderLine(1);
		Costumer costumer = getCostumer(1);

		order = DataTest.genOrder();
		order.setCostumer(costumer);
//		order.setOrderLine(orderLine);
		try {
			orderCrea= (Order) this.getCtrl().create(order);
			if (orderCrea != null) Utils.trace("%s\n", orderCrea);
		} catch (Exception e) {
			Utils.trace("catch create %s\n", e.toString());
		}

	}
	// -------------------------------------------------------------------------------------------------

	public void createMany(int startCostumer,int maxOrder) {
		Utils.trace("=========================== create many  ===========================\n");
		int maxItem = 30;
		int maxIndexCostumer= 10;
		Order order = new Order();
		Item item = new Item();
		Costumer costumer = new Costumer() ;

		try {
			for (int indexCostumer = startCostumer; indexCostumer  <= maxIndexCostumer+startCostumer ; indexCostumer ++) {
				
				int maxCurrentOrder = Utils.randInt(1, maxOrder);
				costumer = getCostumer(indexCostumer);
				
				for (int indexOrder = 1; indexOrder <= maxCurrentOrder; indexOrder++) {

					order = DataTest.genOrder();
					item = getItem(Utils.randInt(1, maxItem));

					order.setCostumer(costumer);
					order.setBankCardUsed(costumer.getBankCardList().get(0));
					order.setDeliveryAddress(costumer.getAddressList().get(0));
					order.setBillingAddress(costumer.getAddressList().get(0));
					
					costumer.addOrder(order);
					this.getCtrl().create(order);
					Utils.trace("%s\n", order);

				}
			}
		} catch (Exception e) {
			Utils.trace("catch createMany %s\n", e.toString());
		}
	}

	// -------------------------------------------------------------------------------------------------
	public void readMany() {
		Utils.trace("=========================== read many  ===========================\n");

		List<Object> orderList = new ArrayList<Object>();

		try {
			orderList = this.getCtrl().list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if ((orderList.size() > 0) && (orderList != null)) {
			for (Object object : orderList) {
				Utils.trace("%s\n", (Order) object);
			}
		} else
			Utils.trace("address null");
	}

//-------------------------------------------------------------------------------------------------	
	public void readOne(int id) {
		Utils.trace("=========================== read One [%d] ===========================\n",id);

		Order order = new Order();

		try {
			for (int index = id; index < this.getMaxRetry() + id; index++) {
				order = (Order) this.getCtrl().read(id);
				if (order!= null)	break;
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (order != null)
			Utils.trace("%s\n", order);
		else
			Utils.trace("order null\n");

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
