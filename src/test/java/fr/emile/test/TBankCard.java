package fr.emile.test;

import java.util.ArrayList;
import java.util.List;

import fr.emile.ctrl.BankCardCtrl;
import fr.emile.ctrl.CrudCtrl;
import fr.emile.ctrl.StandardCrudCtrl;
import fr.emile.ctrl.UserCtrl;
import fr.emile.entity.Address;
import fr.emile.entity.BankCard;
import fr.emile.entity.Costumer;
import fr.emile.entity.User;

import fr.emile.utils.Utils;

public class TBankCard {

	public static void main(String[] args) {
		Utils.trace("*************************** Begin ************************************\n");
//		createOne();
		createMany();
//		readOne();
//		readMany();
//		update();
//		delete();

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
		int bankCardId = 3;
		BankCard bankCard = null;

		BankCardCtrl bankCardCtrl = new BankCardCtrl();
		try {
			bankCard = (BankCard) bankCardCtrl.read(bankCardId);
			if (bankCard == null)
				Utils.trace("cartePaiement null\n");
			else {
				Utils.trace("Before  %s\n", bankCard);

				// -------------------------- update ----------------------
				bankCard.setIsValid(!bankCard.getIsValid());
				bankCardCtrl.update(bankCard);

				bankCard = (BankCard) bankCardCtrl.read(bankCardId);
				if (bankCard != null)
					Utils.trace("After %s\n", bankCard);
				else
					Utils.trace("cartePaiement null\n");
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
		int bankCardId = 1;
		BankCard bankCard = new BankCard();
		BankCardCtrl bankCardCtrl = new BankCardCtrl();
		try {
			bankCard = (BankCard) bankCardCtrl.read(bankCardId);
			if (bankCard== null)
				Utils.trace("Error : l'cartePaiement n'existe pas\n");
			else {
				bankCardCtrl .delete(bankCard);

				bankCard = (BankCard) bankCardCtrl.read(bankCardId);

				if (bankCard != null)
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
		Utils.trace("=========================== create One  ===========================\n");


		BankCard bankCard = new BankCard();
		BankCardCtrl bankCardCtrl = new BankCardCtrl();
		Costumer costumer = new Costumer ();

		costumer = getCostumer(1);

		bankCard = DataTest.genBankCard(costumer);

		bankCard.setCostumer(costumer);
		costumer.addBankCard(bankCard );

		Utils.trace("CB %s \n", bankCard);

		try {
			bankCardCtrl.create(bankCard);
		} catch (Exception e) {
			Utils.trace("catch create %s\n", e.toString());

		}

		Utils.trace("%s\n", bankCard);
	}
	// -------------------------------------------------------------------------------------------------

	public static void createMany() {
		Utils.trace("=========================== create many  ===========================\n");
		int maxIndexCostumer= 10;

		BankCard bankCard = new BankCard();
		BankCardCtrl bankCardCtrl = new BankCardCtrl();
		Costumer costumer = new Costumer ();

		try {

			for (int indexCostumer = 1; indexCostumer < maxIndexCostumer; indexCostumer++) {

				int maxIndex = Utils.randInt(1, 4);
				costumer = getCostumer(indexCostumer);

				for (int index = 1; index < maxIndex; index++) {

					bankCard = DataTest.genBankCard(costumer);
					bankCard.setCostumer(costumer);

					costumer.addBankCard(bankCard);
					bankCardCtrl.create(bankCard);
					if (bankCard != null) 
						Utils.trace("CB %s \n", bankCard);
					
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

		List<Object> bankCardList = new ArrayList<Object>();

		BankCardCtrl bankCardCtrl = new BankCardCtrl();
		try {
			bankCardList = bankCardCtrl.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if ((bankCardList.size() > 0) && (bankCardList != null)) {
			for (Object object : bankCardList) {
				Utils.trace("%s\n", ( BankCard)object );
			}
		} else
			Utils.trace("bankCardList");
	}

//-------------------------------------------------------------------------------------------------	
	public static void readOne() {
		Utils.trace("=========================== read One  ===========================\n");
		int bankCardId  = 14;
		BankCard bankCard = new BankCard();

		BankCardCtrl bankCardCtrl = new BankCardCtrl();
		try {
			bankCard = (BankCard) bankCardCtrl.read(bankCardId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (bankCard != null)
			Utils.trace("%s\n", bankCard);
		else
			Utils.trace("bankCard null\n");

	}
//-------------------------------------------------------------------------------------------------	

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
//	// -------------------------------------------------------------------------------------------------
	public static User getUser(int userId) {

		User user = new User();
		UserCtrl userCtrl = new UserCtrl();
		try {
			user = (User) userCtrl.read(userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;

	}
//	// -------------------------------------------------------------------------------------------------
//
//	public static Article getArticle(int idArticle) {
//		Article article = new Article();
//		IArticleDao articleDao = new ArticleDao();
//		try {
//			article = articleDao.getArticleById(idArticle);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return article;
//
//	}

}
