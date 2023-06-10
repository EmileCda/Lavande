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
import fr.emile.entity.OrderLine;
import fr.emile.utils.Utils;

public class TOrderLine {

	public static void main(String[] args) {
		Utils.trace("*************************** Begin ************************************\n");
		TOrderLineUnitTest unitTest = new TOrderLineUnitTest();
		unitTest.createOne();
//		unitTest.createMany(10);
//		unitTest.readOne(45);
//		unitTest.readMany();
//		unitTest.update();
//		unitTest.delete();
		Utils.trace("*************************** end ************************************\n");

	}

}

//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%Unit Test Object %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
class TOrderLineUnitTest {

	private CrudCtrl ctrl;
	private int maxRetry = 10;

	public TOrderLineUnitTest() {
		this.setCtrl(new StandardCrudCtrl(new OrderLine()));
	}

//-------------------------------------------------------------------------------------------------
	public void update() {
		int orderLineId = 50;
		Utils.trace("=========================== Update [%d]===========================\n",orderLineId);
		OrderLine orderLine = null;

		try {
			orderLine = (OrderLine) this.getCtrl().read(orderLineId);
			if (orderLine == null)
				Utils.trace("Address null\n");
			else {
				Utils.trace("Before:\t%s\n", orderLine);

				// -------------------------- update ----------------------
				orderLine.setQuantity(orderLine.getQuantity()+1);
				this.getCtrl().update(orderLine);

				orderLine = (OrderLine) this.getCtrl().read(orderLineId);
				if (orderLine != null)
					Utils.trace("After:\t%s\n", orderLine);
				else
					Utils.trace("OrderLine null\n");
			}

		} catch (Exception e) {
			Utils.trace("catch delete %s\n", e.toString());
		}
	}

//-------------------------------------------------------------------------------------------------
	public void delete() {
		Utils.trace("=========================== Delete ===========================\n");
		int addressId = 50;
		OrderLine orderLine = new OrderLine();

		try {
			orderLine = (OrderLine) this.getCtrl().read(addressId);
			if (orderLine == null)
				Utils.trace("Error : l'orderLine n'existe pas\n");
			else {
				Utils.trace("last time seen %s\n", orderLine);
				this.getCtrl().delete(orderLine);
				orderLine = (OrderLine) this.getCtrl().read(addressId);

				if (orderLine != null)
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
		OrderLine orderLine = new OrderLine();
		OrderLine orderLineCrea = new OrderLine();
		Item item = getItem(1);
//		OrderLineLine orderLineLine= getOrderLineLine(1);
		Order order = getOrder(1);

		orderLine = DataTest.genOrderLine();
		orderLine.setOrder(order);
		orderLine.setItem(item);
		order.addOrderLine(orderLine);
		item.addOrderLine(orderLine);
		try {
			orderLineCrea= (OrderLine) this.getCtrl().create(orderLine);
			if (orderLineCrea != null) Utils.trace("%s\n", orderLineCrea);
		} catch (Exception e) {
			Utils.trace("catch create %s\n", e.toString());
		}

	}
	// -------------------------------------------------------------------------------------------------

	public void createMany(int maxOrderLine) {
		Utils.trace("=========================== create many  ===========================\n");
		int maxOrder = 80;
		int maxItem = 100;

		Item item = new Item();
		Order order = new Order() ; 
		OrderLine orderLine = new OrderLine();
		OrderLine orderLineCrea = new OrderLine();


		try {
			for (int indexOrder  = 1; indexOrder<= maxOrder; indexOrder++) {
				
				int maxCurrentOrderLine = Utils.randInt(0, maxOrderLine);
				order = getOrder(indexOrder);
				if (order != null) Utils.trace("%s\n",order);else Utils.trace("order %d null \n",indexOrder);

				for (int indexOrderLine = 1; indexOrderLine <= maxCurrentOrderLine; indexOrderLine++) {

					orderLine = DataTest.genOrderLine();
					item = getItem(Utils.randInt(1, maxItem));

					orderLine.setOrder(order);
					orderLine.setItem(item);
					order.addOrderLine(orderLine);
					item.addOrderLine(orderLine);
					orderLineCrea= (OrderLine) this.getCtrl().create(orderLine);
					if (orderLineCrea != null) Utils.trace("%s\n", orderLineCrea);
					else  Utils.trace("orderLineCrea = null \n");
				}
			}
		} catch (Exception e) {
			Utils.trace("catch createMany %s\n", e.toString());
		}
	}

	// -------------------------------------------------------------------------------------------------
	public void readMany() {
		Utils.trace("=========================== read many  ===========================\n");

		List<Object> orderLineList = new ArrayList<Object>();

		try {
			orderLineList = this.getCtrl().list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if ((orderLineList.size() > 0) && (orderLineList != null)) {
			for (Object object : orderLineList) {
				Utils.trace("%s\n", (OrderLine) object);
			}
		} else
			Utils.trace("address null");
	}

//-------------------------------------------------------------------------------------------------	
	public void readOne(int id) {
		Utils.trace("=========================== read One [%d] ===========================\n",id);

		OrderLine orderLine = new OrderLine();

		try {
			for (int index = id; index < this.getMaxRetry() + id; index++) {
				orderLine = (OrderLine) this.getCtrl().read(index);
				if (orderLine!= null)	break;
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (orderLine != null)
			Utils.trace("%s\n", orderLine);
		else
			Utils.trace("orderLine null\n");

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
				item = (Item) itemCtrl.read(index );
				if (item != null)
					break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return item;
	}

//-------------------------------------------------------------------------------------------------	
	public Order getOrder(int id) {
		Order  order = new Order ();
		CrudCtrl orderCtrl = new StandardCrudCtrl(new Order ());

		try {
			for (int index = id; index < this.getMaxRetry() + id; index++) {
				order  = (Order ) orderCtrl.read(index );
				if (order != null)
					break;
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return order ;
	}

//-------------------------------------------------------------------------------------------------	

	public Costumer getCostumer(int id) {

		Costumer costumer = new Costumer();
		UserCtrl costumerCtrl = new UserCtrl();
		try {
			for (int index = id; index < this.getMaxRetry() + id; index++) {
				costumer = (Costumer) costumerCtrl.read(index );
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
