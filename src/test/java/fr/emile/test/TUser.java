package fr.emile.test;

import java.util.ArrayList;
import java.util.List;

import fr.emile.ctrl.CrudCtrl;
import fr.emile.ctrl.StandardCrudCtrl;
import fr.emile.ctrl.UserCtrl;
import fr.emile.entity.Category;
import fr.emile.entity.Costumer;
import fr.emile.entity.Item;
import fr.emile.entity.User;
import fr.emile.utils.Utils;

public class TUser {

	public static void main(String[] args) {
		Utils.trace("*************************** Begin ************************************\n");
		TUserUnitTest unitTest = new TUserUnitTest();
//		unitTest.createOne();
//		unitTest.createMany(2);
//		unitTest.readOne(1);
//		unitTest.readOneByEmail("Zedmond.michel@noisetier.us");
//		unitTest.readMany();
//		unitTest.update();
//		unitTest.delete();
		Utils.trace("*************************** end ************************************\n");

	}

}

//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%Unit Test Object %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
class TUserUnitTest {

	private UserCtrl ctrl;
	private int maxRetry = 10;

	public TUserUnitTest() {
		this.setCtrl(new UserCtrl());
	}

//-------------------------------------------------------------------------------------------------
	public void update() {
		int userId = 3;
		Utils.trace("=========================== Update [%d]===========================\n", userId);
		User user = null;

		try {
			user = (User) this.getCtrl().read(userId);
			if (user == null)
				Utils.trace("Address null\n");
			else {
				Utils.trace("Before:\t%s\n", user);

				// -------------------------- update ----------------------
				user.setEmail(user.getEmail() + ".mod");
				this.getCtrl().update(user);

				user = (User) this.getCtrl().read(userId);
				if (user != null)
					Utils.trace("After:\t%s\n", user);
				else
					Utils.trace("User null\n");
			}

		} catch (Exception e) {
			Utils.trace("catch delete %s\n", e.toString());
		}
	}

//-------------------------------------------------------------------------------------------------
	public void delete() {
		Utils.trace("=========================== Delete ===========================\n");
		int addressId = 2;
		User user = new User();

		try {
			user = (User) this.getCtrl().read(addressId);
			if (user == null)
				Utils.trace("Error : l'user n'existe pas\n");
			else {
				Utils.trace("last time seen %s\n", user);
				this.getCtrl().delete(user);
				user = (User) this.getCtrl().read(addressId);

				if (user != null)
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
		User user = new User();

		user = DataTest.genUser();
		try {
			this.getCtrl().create(user);
			Utils.trace("%s\n", user);
		} catch (Exception e) {
			Utils.trace("catch create %s\n", e.toString());
		}

	}
	// -------------------------------------------------------------------------------------------------

	public void createMany(int maxUser) {
		Utils.trace("=========================== create many  ===========================\n");
		User user = new User();

		try {

			for (int indexUser = 1; indexUser <= maxUser; indexUser++) {

				user = DataTest.genUser();
				this.getCtrl().create(user);
				Utils.trace("%s\n", user);

			}
		} catch (Exception e) {
			Utils.trace("catch createMany %s\n", e.toString());
		}
	}

	// -------------------------------------------------------------------------------------------------
	public void readMany() {
		Utils.trace("=========================== read many  ===========================\n");

		List<Object> userList = new ArrayList<Object>();

		try {
			userList = this.getCtrl().list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if ((userList.size() > 0) && (userList != null)) {
			for (Object object : userList) {
				Utils.trace("%s\n", (User) object);
			}
		} else
			Utils.trace("address null");
	}

//-------------------------------------------------------------------------------------------------	
	public void readOne(int id) {
		Utils.trace("=========================== read One [%d] ===========================\n", id);

		User user = new User();

		try {
			for (int index = id; index < this.getMaxRetry() + id; index++) {
				user = (User) this.getCtrl().read(id);
				if (user != null)
					break;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (user != null)
			Utils.trace("%s\n", user);
		else
			Utils.trace("user null\n");

	}

//-------------------------------------------------------------------------------------------------	
	public void readOneByEmail(String email) {
		Utils.trace("=========================== read One [] ===========================\n");

		User user = new User();

		try {
			user = (User) this.getCtrl().read(email);

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (user != null)
			Utils.trace("%s\n", user);
		else
			Utils.trace("user null\n");

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
	public UserCtrl getCtrl() {
		return this.ctrl;
	}

	public void setCtrl(UserCtrl ctrl) {
		this.ctrl = ctrl;
	}

	public int getMaxRetry() {
		return maxRetry;
	}

	public void setMaxRetry(int maxRetry) {
		this.maxRetry = maxRetry;
	}
}
