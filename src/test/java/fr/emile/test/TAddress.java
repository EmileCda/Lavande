package fr.emile.test;

import java.util.ArrayList;
import java.util.List;

import fr.emile.ctrl.CrudCtrl;
import fr.emile.ctrl.StandardCrudCtrl;
import fr.emile.ctrl.UserCtrl;
import fr.emile.entity.Category;
import fr.emile.entity.Costumer;
import fr.emile.entity.Address;
import fr.emile.entity.Item;
import fr.emile.entity.Address;
import fr.emile.utils.Utils;

public class TAddress {

	public static void main(String[] args) {
		Utils.trace("*************************** Begin ************************************\n");
		TAddressUnitTest unitTest = new TAddressUnitTest();
		unitTest.createOne();
//		unitTest.createMany(31,10);
//		unitTest.readOne(1);
//		unitTest.readMany();
//		unitTest.update();
//		unitTest.delete();
		Utils.trace("*************************** end ************************************\n");

	}

}

//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%Unit Test Object %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
class TAddressUnitTest {

	private CrudCtrl ctrl;
	private int maxRetry = 10;

	public TAddressUnitTest() {
		this.setCtrl(new StandardCrudCtrl(new Address()));
	}

//-------------------------------------------------------------------------------------------------
	public void update() {
		int addressId = 3;
		Utils.trace("=========================== Update [%d]===========================\n", addressId);
		Address address = null;

		try {
			address = (Address) this.getCtrl().read(addressId);
			if (address == null)
				Utils.trace("Address null\n");
			else {
				Utils.trace("Before:\t%s\n", address);

				// -------------------------- update ----------------------
				address.setStreetNumber(address.getStreetNumber() + 2);
				this.getCtrl().update(address);

				address = (Address) this.getCtrl().read(addressId);
				if (address != null)
					Utils.trace("After:\t%s\n", address);
				else
					Utils.trace("Address null\n");
			}

		} catch (Exception e) {
			Utils.trace("catch delete %s\n", e.toString());
		}
	}

//-------------------------------------------------------------------------------------------------
	public void delete() {
		Utils.trace("=========================== Delete ===========================\n");
		int addressId = 2;
		Address address = new Address();

		try {
			address = (Address) this.getCtrl().read(addressId);
			if (address == null)
				Utils.trace("Error : l'address n'existe pas\n");
			else {
				Utils.trace("last time seen %s\n", address);
				this.getCtrl().delete(address);
				address = (Address) this.getCtrl().read(addressId);

				if (address != null)
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
		Address address = new Address();
		Costumer costumer = getCostumer(1);
		address = DataTest.genAddress();
		address.setCostumer(costumer);
		costumer.addAddress(address);
		try {
			this.getCtrl().create(address);
			Utils.trace("%s\n", address);
		} catch (Exception e) {
			Utils.trace("catch create %s\n", e.toString());
		}

	}
	// -------------------------------------------------------------------------------------------------

	public void createMany(int startCostumer, int maxValue) {
		Utils.trace("=========================== create many  ===========================\n");
		int indexMaxCostumer = 10;
		int maxAddress= 2; 
		Costumer costumer = new Costumer();
		Address address = new Address();

		try {
			for (int indexCostumer = startCostumer; indexCostumer <= indexMaxCostumer+startCostumer; indexCostumer++) {

				costumer = getCostumer(indexCostumer);
				
				maxAddress = Utils.randInt(1, maxValue); 
				for (int indexAddress = 1; indexAddress <= maxAddress ; indexAddress++) {
					address = DataTest.genAddress();
					address.setCostumer(costumer);
					costumer.addAddress(address);
					this.getCtrl().create(address);
					Utils.trace("%s\n", address);
				}
			}
		} catch (Exception e) {
			Utils.trace("catch createMany %s\n", e.toString());
		}
	}

	// -------------------------------------------------------------------------------------------------
	public void readMany() {
		Utils.trace("=========================== read many  ===========================\n");

		List<Object> addressList = new ArrayList<Object>();

		try {
			addressList = this.getCtrl().list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if ((addressList.size() > 0) && (addressList != null)) {
			for (Object object : addressList) {
				Utils.trace("%s\n", (Address) object);
			}
		} else
			Utils.trace("address null");
	}

//-------------------------------------------------------------------------------------------------	
	public void readOne(int id) {
		Utils.trace("=========================== read One [%d] ===========================\n", id);

		Address address = new Address();

		try {
			for (int index = id; index < this.getMaxRetry() + id; index++) {
				address = (Address) this.getCtrl().read(id);
				if (address != null)
					break;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (address != null)
			Utils.trace("%s\n", address);
		else
			Utils.trace("address null\n");

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

	public Address getAddress(int id) {

		Address address = new Address();
		UserCtrl addressCtrl = new UserCtrl();
		try {
			for (int index = id; index < this.getMaxRetry() + id; index++) {
				address = (Address) addressCtrl.read(id);
				if (address != null)
					break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return address;

	}

	// -------------------------------------------------------------------------------------------------
	public Costumer getCostumer(int id) {

		Costumer costumer = new Costumer();
		UserCtrl costumerCtrl = new UserCtrl();
		try {
			for (int index = id; index < this.getMaxRetry() + id; index++) {
				costumer = (Costumer) costumerCtrl.read(index);
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
