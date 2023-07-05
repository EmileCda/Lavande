package fr.emile.test;

import java.util.ArrayList;
import java.util.List;

import fr.emile.ctrl.BankCardCtrl;
import fr.emile.ctrl.CrudCtrl;
import fr.emile.ctrl.StandardCrudCtrl;
import fr.emile.ctrl.UserCtrl;
import fr.emile.entity.Category;
import fr.emile.entity.Costumer;
import fr.emile.entity.BankCard;
import fr.emile.entity.Item;
import fr.emile.entity.BankCard;
import fr.emile.utils.Utils;

public class TBankCard {

	public static void main(String[] args) {
		Utils.trace("*************************** Begin ************************************\n");
		TBankCardUnitTest unitTest = new TBankCardUnitTest();
		unitTest.createOne();
//		unitTest.createMany(10);
//		unitTest.readOne(1);
//		unitTest.readMany();
//		unitTest.update();
//		unitTest.delete();
		Utils.trace("*************************** end ************************************\n");

	}

}

//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%Unit Test Object %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
class TBankCardUnitTest {

	private CrudCtrl ctrl;
	private int maxRetry = 10;
	public TBankCardUnitTest() {
		this.setCtrl(new BankCardCtrl());
	}

//-------------------------------------------------------------------------------------------------
	public void update() {
		int bankCardId = 3;
		Utils.trace("=========================== Update [%d]===========================\n", bankCardId);
		BankCard bankCard = null;

		try {
			bankCard = (BankCard) this.getCtrl().read(bankCardId);
			if (bankCard == null)
				Utils.trace("BankCard null\n");
			else {
				Utils.trace("Before:\t%s\n", bankCard);

				// -------------------------- update ----------------------
				bankCard.setIsValid(! bankCard.getIsValid());
				this.getCtrl().update(bankCard);

				bankCard = (BankCard) this.getCtrl().read(bankCardId);
				if (bankCard != null)
					Utils.trace("After:\t%s\n", bankCard);
				else
					Utils.trace("BankCard null\n");
			}

		} catch (Exception e) {
			Utils.trace("catch delete %s\n", e.toString());
		}
	}

//-------------------------------------------------------------------------------------------------
	public void delete() {
		Utils.trace("=========================== Delete ===========================\n");
		int bankCardId = 2;
		BankCard bankCard = new BankCard();

		try {
			bankCard = (BankCard) this.getCtrl().read(bankCardId);
			if (bankCard == null)
				Utils.trace("Error : l'bankCard n'existe pas\n");
			else {
				Utils.trace("last time seen %s\n", bankCard);
				this.getCtrl().delete(bankCard);
				bankCard = (BankCard) this.getCtrl().read(bankCardId);

				if (bankCard != null)
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
		BankCard bankCard = new BankCard();
		Costumer costumer = getCostumer(31);
		bankCard = DataTest.genBankCard(costumer);
		bankCard.setCostumer(costumer);
		costumer.addBankCard(bankCard);
		try {
			this.getCtrl().create(bankCard);
			Utils.trace("%s\n", bankCard);
		} catch (Exception e) {
			Utils.trace("catch create %s\n", e.toString());
		}

	}
	// -------------------------------------------------------------------------------------------------

	public void createMany(int startCostumer,int maxValue) {
		Utils.trace("=========================== create many  ===========================\n");
		int indexMaxCostumer = 10;
		int maxBankCard= 2; 
		Costumer costumer = new Costumer();
		BankCard bankCard = new BankCard();

		try {
			for (int indexCostumer = startCostumer; indexCostumer <= indexMaxCostumer+startCostumer; indexCostumer++) {

				costumer = getCostumer(indexCostumer);
				
				maxBankCard = Utils.randInt(1, maxValue); 
				for (int indexBankCard = 1; indexBankCard <= maxBankCard ; indexBankCard++) {
					bankCard = DataTest.genBankCard(costumer);
					bankCard.setCostumer(costumer);
					costumer.addBankCard(bankCard);
					this.getCtrl().create(bankCard);
					Utils.trace("%s\n", bankCard);
				}
			}
		} catch (Exception e) {
			Utils.trace("catch createMany %s\n", e.toString());
		}
	}

	// -------------------------------------------------------------------------------------------------
	public void readMany() {
		Utils.trace("=========================== read many  ===========================\n");

		List<Object> bankCardList = new ArrayList<Object>();

		try {
			bankCardList = this.getCtrl().list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if ((bankCardList.size() > 0) && (bankCardList != null)) {
			for (Object object : bankCardList) {
				Utils.trace("%s\n", (BankCard) object);
			}
		} else
			Utils.trace("bankCard null");
	}

//-------------------------------------------------------------------------------------------------	
	public void readOne(int id) {
		Utils.trace("=========================== read One [%d] ===========================\n", id);

		BankCard bankCard = new BankCard();

		try {
			for (int index = id; index < this.getMaxRetry() + id; index++) {
				bankCard = (BankCard) this.getCtrl().read(id);
				if (bankCard != null)
					break;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (bankCard != null)
			Utils.trace("%s\n", bankCard);
		else
			Utils.trace("bankCard null\n");

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

	public BankCard getBankCard(int id) {

		BankCard bankCard = new BankCard();
		UserCtrl bankCardCtrl = new UserCtrl();
		try {
			for (int index = id; index < this.getMaxRetry() + id; index++) {
				bankCard = (BankCard) bankCardCtrl.read(id);
				if (bankCard != null)
					break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return bankCard;

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
