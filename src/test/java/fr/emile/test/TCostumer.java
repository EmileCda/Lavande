package fr.emile.test;

import java.util.ArrayList;
import java.util.List;

import fr.emile.ctrl.CostumerCtrl;
import fr.emile.ctrl.CrudCtrl;
import fr.emile.ctrl.StandardCrudCtrl;
import fr.emile.ctrl.UserCtrl;
import fr.emile.entity.Category;
import fr.emile.entity.Costumer;
import fr.emile.entity.Item;
import fr.emile.entity.Costumer;
import fr.emile.utils.Utils;

public class TCostumer {

	public static void main(String[] args) {
		Utils.trace("*************************** Begin ************************************\n");
		TCostumerUnitTest unitTest = new TCostumerUnitTest();
//		unitTest.createOne();
//		unitTest.createMany(10);
//		unitTest.readOne(1);
//		unitTest.readMany();
		unitTest.update();
//		unitTest.delete();
		Utils.trace("*************************** end ************************************\n");

	}

}

//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%Unit Test Object %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
class TCostumerUnitTest {

	private CostumerCtrl ctrl;
	private int maxRetry = 10;

	public TCostumerUnitTest() {
		this.setCtrl(new CostumerCtrl());
	}

//-------------------------------------------------------------------------------------------------
	public void update() {
		int costumerId = 30;
		Utils.trace("=========================== Update [%d]===========================\n",costumerId);
		Costumer costumer = null;

		try {
			costumer = (Costumer) this.getCtrl().read(costumerId);
			if (costumer == null)
				Utils.trace("Address null\n");
			else {
				Utils.trace("Before:\t%s\n", costumer);

				// -------------------------- update ----------------------
				costumer.setFirstname("*"+ costumer.getFirstname()+"*");
				this.getCtrl().update(costumer);

				costumer = (Costumer) this.getCtrl().read(costumerId);
				if (costumer != null)
					Utils.trace("After:\t%s\n", costumer);
				else
					Utils.trace("Costumer null\n");
			}

		} catch (Exception e) {
			Utils.trace("catch delete %s\n", e.toString());
		}
	}

//-------------------------------------------------------------------------------------------------
	public void delete() {
		Utils.trace("=========================== Delete ===========================\n");
		int id = 2;
		Costumer costumer = new Costumer();

		try {
			costumer = (Costumer) this.getCtrl().read(id);
			if (costumer == null)
				Utils.trace("Error : l'costumer n'existe pas\n");
			else {
				Utils.trace("last time seen %s\n", costumer);
				this.getCtrl().delete(costumer);
				costumer = (Costumer) this.getCtrl().read(id
						);

				if (costumer != null)
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
		Costumer costumer = new Costumer();

		costumer = DataTest.genCostumer();
		try {
			this.getCtrl().create(costumer);
			Utils.trace("%s\n", costumer);
		} catch (Exception e) {
			Utils.trace("catch create %s\n", e.toString());
		}

	}
	// -------------------------------------------------------------------------------------------------

	public void createMany( int maxCostumer) {
		Utils.trace("=========================== create many  ===========================\n");
		
		Costumer costumer = new Costumer();

		try {
			for (int indexCostumer = 1; indexCostumer  <= maxCostumer; indexCostumer ++) {
					costumer = DataTest.genCostumer();
					this.getCtrl().create(costumer);
					Utils.trace("%s\n", costumer);
			}
		} catch (Exception e) {
			Utils.trace("catch createMany %s\n", e.toString());
		}
	}

	// -------------------------------------------------------------------------------------------------
	public void readMany() {
		Utils.trace("=========================== read many  ===========================\n");

		List<Object> costumerList = new ArrayList<Object>();

		try {
			costumerList = this.getCtrl().list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if ((costumerList.size() > 0) && (costumerList != null)) {
			for (Object object : costumerList) {
				Utils.trace("%s\n", (Costumer) object);
			}
		} else
			Utils.trace("address null");
	}

//-------------------------------------------------------------------------------------------------	
	public void readOne(int id) {
		Utils.trace("=========================== read One [%d] ===========================\n",id);

		Costumer costumer = new Costumer();

		try {
			for (int index = id; index < this.getMaxRetry() + id; index++) {
				costumer = (Costumer) this.getCtrl().read(id);
				if (costumer!= null)	break;
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (costumer != null)
			Utils.trace("%s\n", costumer);
		else
			Utils.trace("costumer null\n");

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
	public CostumerCtrl getCtrl() {
		return this.ctrl;
	}

	public void setCtrl(CostumerCtrl ctrl) {
		this.ctrl = ctrl;
	}

	public int getMaxRetry() {
		return maxRetry;
	}

	public void setMaxRetry(int maxRetry) {
		this.maxRetry = maxRetry;
	}
}
