package fr.emile.test;

import java.util.ArrayList;
import java.util.List;

import fr.emile.ctrl.CrudCtrl;
import fr.emile.entity.Address;
import fr.emile.entity.BankCard;
import fr.emile.entity.User;

import fr.emile.utils.Utils;

public class TBankCard {

	public static void main(String[] args) {
		Utils.trace("*************************** Begin ************************************\n");
		createOne();
//		createMany();
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
		int cartePaiementId = 3;
		BankCard cartePaiement = null;

		BankCardCtrl cartePaiementCtrl = new BankCardCtrl();
		try {
			cartePaiement = cartePaiementCtrl.getBankCardById(cartePaiementId);
			if (cartePaiement == null)
				Utils.trace("cartePaiement null\n");
			else {
				Utils.trace("Before  %s\n", cartePaiement);

				// -------------------------- update ----------------------
				cartePaiement.setIsValid(true);
				cartePaiementCtrl.updateBankCard(cartePaiement);

				cartePaiement = cartePaiementCtrl.getBankCardById(cartePaiementId);
				if (cartePaiement != null)
					Utils.trace("After %s\n", cartePaiement);
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
		int addressId = 1;
		BankCard cartePaiement = new BankCard();
		IBankCardDao cartePaiementDao = new BankCardDao();
		try {
			cartePaiement = cartePaiementDao.getBankCardById(addressId);
			if (cartePaiement == null)
				Utils.trace("Error : l'cartePaiement n'existe pas\n");
			else {
				cartePaiementDao.deleteBankCard(cartePaiement);

				cartePaiement = cartePaiementDao.getBankCardById(addressId);

				if (cartePaiement != null)
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
//		User user = new User();

//		user = getUser(1);

//		cartePaiement = DataTest.genBankCardt(user);
		bankCard = DataTest.genBankCardNoName();

//		cartePaiement.setUser(user);
//		user.addBankCard(cartePaiement);

		Utils.trace("CB %s \n", bankCard);
		CrudCtrl bankCardCtrl = new CrudCtrl(new BankCard());

		try {
			bankCardCtrl.create(bankCard);
		} catch (Exception e) {
			Utils.trace("catch create %s\n", e.toString());
		} finally {

		}

		Utils.trace("%s\n", bankCard);
	}
	// -------------------------------------------------------------------------------------------------

	public static void createMany() {
		Utils.trace("=========================== create many  ===========================\n");
//		int maxIndex = 3;
		int maxIndexUser = 10;

		BankCard cartePaiement = new BankCard();

		IBankCardCtrl cartePaiementCtrl = new BankCardCtrl();
		User user = new User();
		user = getUser(4);

		try {

			for (int indexUser = 1; indexUser < maxIndexUser; indexUser++) {

				int maxIndex = Utils.randInt(1, 4);
				user = getUser(indexUser);

				for (int index = 1; index < maxIndex; index++) {

					cartePaiement = DataTest.genBankCard(user);
					cartePaiement.setUser(user);

					user.addBankCard(cartePaiement);
					cartePaiementCtrl.addBankCard(cartePaiement);
					Utils.trace("CB %s \n", cartePaiement);

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

		List<BankCard> cartePaiementList = new ArrayList<BankCard>();

		IBankCardCtrl cartePaiementCtrl = new BankCardCtrl();
		try {
			cartePaiementList = cartePaiementCtrl.getBankCards();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if ((cartePaiementList.size() > 0) && (cartePaiementList != null)) {
			for (BankCard cartePaiement : cartePaiementList) {
				Utils.trace("%s\n", cartePaiement);
			}
		} else
			Utils.trace("address null");
	}

//-------------------------------------------------------------------------------------------------	
	public static void readOne() {
		Utils.trace("=========================== read One  ===========================\n");
		int cartePaiementId = 14;
		BankCard cartePaiement = new BankCard();

		IBankCardCtrl cartePaiementCtrl = new BankCardCtrl();
		try {
			cartePaiement = cartePaiementCtrl.getBankCardById(cartePaiementId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (cartePaiement != null)
			Utils.trace("%s\n", cartePaiement);
		else
			Utils.trace("cartePaiement null\n");

	}
//-------------------------------------------------------------------------------------------------	

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
