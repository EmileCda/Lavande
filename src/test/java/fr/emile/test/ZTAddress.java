package fr.emile.test;


import java.util.ArrayList;
import java.util.List;


import fr.emile.ctrl.CrudCtrl;
import fr.emile.ctrl.StandardCrudCtrl;
import fr.emile.entity.Address;
import fr.emile.entity.Costumer;
import fr.emile.utils.Utils;

public class ZTAddress {

	public static void main(String[] args) {
		Utils.trace("*************************** Begin ************************************\n");
//		createOne();
		createMany();
//		readOne();
//		readMany();
//		delete();
//		update();

		Utils.trace("*************************** end ************************************\n");

	}

//-------------------------------------------------------------------------------------------------
	public static void create() {
		Utils.trace("=========================== Create ===========================\n");
		createOne();
		createMany();

	}

//-------------------------------------------------------------------------------------------------
	public static void read() {
		Utils.trace("=========================== Read ===========================\n");
		readMany();
		readOne();

	}

//-------------------------------------------------------------------------------------------------
	public static void update() {
		Utils.trace("=========================== Update ===========================\n");
		int addressId = 5;
		Address address = null;

		CrudCtrl addressCtrl = new StandardCrudCtrl(new Address());
		try {
			address = (Address) addressCtrl.read(addressId);
			if (address == null)
				Utils.trace("Address null\n");
			else {
				Utils.trace("Before  %s\n", address);

				// -------------------------- update ----------------------
				address.setCity("*** mod ***" + address.getCity() + "*** mod ***");
				addressCtrl.update(address);

				address = (Address) addressCtrl.read(addressId);
				if (address != null)
					Utils.trace("After %s\n", address);
				else
					Utils.trace("Address null\n");
			}

		} catch (

		Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//-------------------------------------------------------------------------------------------------
	public static void delete() {
		Utils.trace("=========================== Delete ===========================\n");
		int addressId = 1;
		Address address = new Address();
		CrudCtrl addressCtrl = new StandardCrudCtrl(new Address());
		try {
			address = (Address) addressCtrl.read(addressId);
			if (address == null)
				Utils.trace("Error : l'address n'existe pas\n");
			else {
				addressCtrl.delete(address);

				address = (Address) addressCtrl.read(addressId);

				if (address != null)
					Utils.trace("Error not remove\n");
				else
					Utils.trace("remove ok\n");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	// -------------------------------------------------------------------------------------------------

	public static void createOne() {

//		User user = getUser(1);
		Address address = new Address();
		address = DataTest.genAddress();
		Address addressReturn = null ; 
//		address.setUser(user);
//		user.addAddress(address);
		Utils.trace("%s\n", address);

		CrudCtrl addressCtrl = new StandardCrudCtrl(new Address());

		try {
			addressReturn= (Address) addressCtrl.create(address);
		} catch (Exception e) {
			Utils.trace("catch create %s\n", e.toString());
		} finally {

		}
		if (addressReturn != null)
			Utils.trace("%s\n", addressReturn);
		else
			Utils.trace("Address null \n");
	}
	// -------------------------------------------------------------------------------------------------

	public static void createMany() {
		Utils.trace("=========================== read many  ===========================\n");
	
		int maxIndexCostumer= 10;

		Address address = new Address();
		Costumer costumer = new Costumer ();
		

		CrudCtrl addressCtrl = new StandardCrudCtrl(new Address());

		try {

			for (int indexCostumer = 1; indexCostumer < maxIndexCostumer; indexCostumer++) {

				int maxIndex = Utils.randInt(1, 4);
				costumer = getCostumer(indexCostumer);
				
				for (int index = 0; index < maxIndex; index++) {
					address = DataTest.genAddress();
					address.setCostumer(costumer);
					costumer.addAddress(address);
					addressCtrl.create(address);
					Utils.trace("%s\n", address);
				}
			}
		} catch (Exception e) {
			Utils.trace("catch create %s\n", e.toString());
		} finally {

		}
	}

	// -------------------------------------------------------------------------------------------------
	public static void readMany() {
		Utils.trace("=========================== read many  ===========================\n");

		List<Object> addressList = new ArrayList<Object>();
		CrudCtrl addressCtrl = new StandardCrudCtrl(new Address());
		try {
			addressList =  addressCtrl.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if ((addressList.size() > 0) && (addressList != null)) {
			for (Object address : addressList) {
				Utils.trace("%s\n", (Address) address);
			}
		} else
			Utils.trace("address null\n");
	}

//-------------------------------------------------------------------------------------------------	
	public static void readOne() {
		Utils.trace("=========================== read One  ===========================\n");
		int addressId = 1;
		Address address = new Address();
		CrudCtrl addressCtrl = new StandardCrudCtrl(new Address());
		try {
			address = (Address) addressCtrl.read(addressId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (address != null)
			Utils.trace("%s\n", address);
		else
			Utils.trace("address null\n");

	}
	// -------------------------------------------------------------------------------------------------

	public static Costumer getCostumer(int costumerId) {

		Costumer costumer= new Costumer();
		CrudCtrl costumerCtrl = new StandardCrudCtrl(new Costumer());
		try {
			costumer = (Costumer ) costumerCtrl.read(costumerId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return costumer;

	}
	// -------------------------------------------------------------------------------------------------

	// -------------------------------------------------------------------------------------------------

//	public static User getUser(int userId) {
//
//		User user = new User();
//		IUserCtrl userCtrl = new UserCtrl();
//		try {
//			user = userCtrl.getUserById(userId);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return user;
//
//	}

}
