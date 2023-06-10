package fr.emile.test;

import java.util.ArrayList;
import java.util.List;

import fr.emile.ctrl.CrudCtrl;
import fr.emile.ctrl.StandardCrudCtrl;
import fr.emile.entity.Address;
import fr.emile.entity.Category;
import fr.emile.entity.User;
import fr.emile.utils.Utils;
import net.bytebuddy.asm.Advice.This;

public class TCategory {


	public static void main(String[] args) {
		Utils.trace("*************************** Begin ************************************\n");
		TCategoryUnitTest unitTest = new TCategoryUnitTest();
//		unitTest.createOne();
		unitTest.createMany();
//		unitTest.readOne(1);
//		unitTest.readMany();
//		unitTest.update();
//		unitTest.delete();

		Utils.trace("*************************** end ************************************\n");

	
	}
	
}

//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%Unit Test Object %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
class TCategoryUnitTest{

	private CrudCtrl ctrl;

	public TCategoryUnitTest() {
		this.setCtrl(new StandardCrudCtrl(new Category()));
	}

	
//-------------------------------------------------------------------------------------------------
	public void create() {
		Utils.trace("=========================== Create ===========================\n");
		createOne();
		createMany();

	}

//-------------------------------------------------------------------------------------------------
	public void read() {
		Utils.trace("=========================== Read ===========================\n");
		readMany();
		readOne(1);

	}

//-------------------------------------------------------------------------------------------------
	public void update() {
		Utils.trace("=========================== Update ===========================\n");
		int categoryId = 3;
		Category category = null;

		try {
			category = (Category) this.getCtrl().read(categoryId );
			if (category == null)
				Utils.trace("Address null\n");
			else {
				Utils.trace("Before  %s\n", category);

				// -------------------------- update ----------------------
				category.setName("%%%" + category.getName() + "%%%");
				this.getCtrl().update(category);

				category = (Category) this.getCtrl().read(categoryId );
				if (category != null)
					Utils.trace("After %s\n", category);
				else
					Utils.trace("Category null\n");
			}

		} catch (Exception e) {
			Utils.trace("catch delete %s\n", e.toString());
		}
	}

//-------------------------------------------------------------------------------------------------
	public void delete() {
		Utils.trace("=========================== Delete ===========================\n");
		int addressId = 2;
		Category category = new Category();

		try {
			category = (Category) this.getCtrl().read(addressId);
			if (category == null)
				Utils.trace("Error : l'category n'existe pas\n");
			else {
				Utils.trace("last time seen %s\n", category);
				this.getCtrl().delete(category);
				category = (Category) this.getCtrl().read(addressId);

				if (category != null)
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
		Category category = new Category();
		category = DataTest.genCategory();
		try {
			this.getCtrl().create(category);
			Utils.trace("%s\n", category);
		} catch (Exception e) {
			Utils.trace("catch create %s\n", e.toString());
		}

	}
	// -------------------------------------------------------------------------------------------------

	public void createMany() {
		Utils.trace("=========================== create many  ===========================\n");
		int maxIndex = 10;
		Category category = new Category();

		try {
			for (int index = 0; index < maxIndex; index++) {

				category = DataTest.genCategory();
				this.getCtrl().create(category);
				Utils.trace("%s\n", category);

			}
		} catch (Exception e) {
			Utils.trace("catch create %s\n", e.toString());
		}
	}

	// -------------------------------------------------------------------------------------------------
	public void readMany() {
		Utils.trace("=========================== read many  ===========================\n");

		List<Object> categoryList = new ArrayList<Object>();

		try {
			categoryList = this.getCtrl().list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if ((categoryList.size() > 0) && (categoryList != null)) {
			for (Object object : categoryList) {
				Utils.trace("%s\n", (Category) object);
			}
		} else
			Utils.trace("address null");
	}

//-------------------------------------------------------------------------------------------------	
	public void readOne(int categoryId) {
		Utils.trace("=========================== read One  ===========================\n");

		Category category = new Category();

		try {
			category = (Category) this.getCtrl().read(categoryId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (category != null)
			Utils.trace("%s\n", category);
		else
			Utils.trace("category null\n");

	}
//-------------------------------------------------------------------------------------------------	

	public CrudCtrl getCtrl() {
		return this.ctrl;
	}

	public void setCtrl(CrudCtrl ctrl) {
		this.ctrl = ctrl;
	}
}

	
