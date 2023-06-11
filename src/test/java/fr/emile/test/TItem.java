package fr.emile.test;

import java.util.ArrayList;
import java.util.List;

import fr.emile.ctrl.CrudCtrl;
import fr.emile.ctrl.StandardCrudCtrl;
import fr.emile.entity.Category;
import fr.emile.entity.Item;
import fr.emile.utils.Utils;

public class TItem {

	public static void main(String[] args) {
		Utils.trace("*************************** Begin ************************************\n");
		TItemUnitTest unitTest = new TItemUnitTest();
//		unitTest.createOne();
		unitTest.createMany(10);
//		unitTest.readOne(1);
//		unitTest.readMany();
//		unitTest.update();
//		unitTest.delete();

		Utils.trace("*************************** end ************************************\n");

	}

}

//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%Unit Test Object %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
class TItemUnitTest {

	private CrudCtrl ctrl;

	public TItemUnitTest() {
		this.setCtrl(new StandardCrudCtrl(new Item()));
	}



//-------------------------------------------------------------------------------------------------
	public void update() {
		Utils.trace("=========================== Update ===========================\n");
		int itemId = 3;
		Item item = null;

		try {
			item = (Item) this.getCtrl().read(itemId);
			if (item == null)
				Utils.trace("Address null\n");
			else {
				Utils.trace("Before  %s\n", item);

				// -------------------------- update ----------------------
				item.setName("%%%" + item.getName() + "%%%");
				this.getCtrl().update(item);

				item = (Item) this.getCtrl().read(itemId);
				if (item != null)
					Utils.trace("After %s\n", item);
				else
					Utils.trace("Item null\n");
			}

		} catch (Exception e) {
			Utils.trace("catch delete %s\n", e.toString());
		}
	}

//-------------------------------------------------------------------------------------------------
	public void delete() {
		Utils.trace("=========================== Delete ===========================\n");
		int addressId = 2;
		Item item = new Item();

		try {
			item = (Item) this.getCtrl().read(addressId);
			if (item == null)
				Utils.trace("Error : l'item n'existe pas\n");
			else {
				Utils.trace("last time seen %s\n", item);
				this.getCtrl().delete(item);
				item = (Item) this.getCtrl().read(addressId);

				if (item != null)
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
		Item item = new Item();
		Category category = this.getCategory(1);

		item = DataTest.genItem();
		item.setCategory(category);
		category.addItem(item);
		try {
			this.getCtrl().create(item);
			Utils.trace("%s\n", item);
		} catch (Exception e) {
			Utils.trace("catch create %s\n", e.toString());
		}

	}
	// -------------------------------------------------------------------------------------------------

	public void createMany(int maxItem ) {
		Utils.trace("=========================== create many  ===========================\n");
		int maxIndexCategory  = 10 ; 
		Item item = new Item();
		Category category = new Category();

		try {
			for (int indexCategory = 1; indexCategory <=maxIndexCategory; indexCategory++) {
				int maxCurrentItem = Utils.randInt(0, maxItem);
				category = this.getCategory(indexCategory);

				for (int indexItem = 1; indexItem <= maxCurrentItem; indexItem++) {

					item = DataTest.genItem();
					item.setCategory(category);
					category.addItem(item);
					this.getCtrl().create(item);
					Utils.trace("%s\n", item);

				}
			}
		} catch (Exception e) {
			Utils.trace("catch createMany %s\n", e.toString());
		}
	}

	// -------------------------------------------------------------------------------------------------
	public void readMany() {
		Utils.trace("=========================== read many  ===========================\n");

		List<Object> itemList = new ArrayList<Object>();

		try {
			itemList = this.getCtrl().list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if ((itemList.size() > 0) && (itemList != null)) {
			for (Object object : itemList) {
				Utils.trace("%s\n", (Item) object);
			}
		} else
			Utils.trace("address null");
	}

//-------------------------------------------------------------------------------------------------	
	public void readOne(int itemId) {
		Utils.trace("=========================== read One  ===========================\n");

		Item item = new Item();

		try {
			item = (Item) this.getCtrl().read(itemId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (item != null)
			Utils.trace("%s\n", item);
		else
			Utils.trace("item null\n");

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
	public CrudCtrl getCtrl() {
		return this.ctrl;
	}

	public void setCtrl(CrudCtrl ctrl) {
		this.ctrl = ctrl;
	}
}
