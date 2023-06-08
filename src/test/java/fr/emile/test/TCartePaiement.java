package fr.ecommerce.test;

import java.util.ArrayList;
import java.util.List;

import fr.ecommerce.Ctrl.implement.CartePaiementCtrl;
import fr.ecommerce.Ctrl.implement.UserCtrl;
import fr.ecommerce.Ctrl.interfaces.ICartePaiementCtrl;
import fr.ecommerce.Ctrl.interfaces.IUserCtrl;
import fr.ecommerce.entity.Article;
import fr.ecommerce.entity.CartePaiement;
import fr.ecommerce.entity.User;
import fr.ecommerce.model.dao.implement.ArticleDao;
import fr.ecommerce.model.dao.implement.CartePaiementDao;
import fr.ecommerce.model.dao.interfaces.IArticleDao;
import fr.ecommerce.model.dao.interfaces.ICartePaiementDao;
import fr.ecommerce.utils.DataTest;
import fr.ecommerce.utils.Utils;

public class TCartePaiement {

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
		int cartePaiementId = 3;
		CartePaiement cartePaiement = null;

		ICartePaiementCtrl cartePaiementCtrl = new CartePaiementCtrl();
		try {
			cartePaiement = cartePaiementCtrl.getCartePaiementById(cartePaiementId);
			if (cartePaiement == null)
				Utils.trace("cartePaiement null\n");
			else {
				Utils.trace("Before  %s\n", cartePaiement);

				// -------------------------- update ----------------------
				cartePaiement.setIsValid(true);
				cartePaiementCtrl.updateCartePaiement(cartePaiement);

				cartePaiement = cartePaiementCtrl.getCartePaiementById(cartePaiementId);
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
		CartePaiement cartePaiement = new CartePaiement();
		ICartePaiementDao cartePaiementDao = new CartePaiementDao();
		try {
			cartePaiement = cartePaiementDao.getCartePaiementById(addressId);
			if (cartePaiement == null)
				Utils.trace("Error : l'cartePaiement n'existe pas\n");
			else {
				cartePaiementDao.deleteCartePaiement(cartePaiement);

				cartePaiement = cartePaiementDao.getCartePaiementById(addressId);

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

		Utils.trace("CB %s \n", DataTest.bankCardNumber());
		Utils.trace("CB %s \n", DataTest.bankCardNumber());

		CartePaiement cartePaiement = new CartePaiement();
		User user = new User();

		user = getUser(1);

		cartePaiement = DataTest.genCartePaiement(user);

		cartePaiement.setUser(user);
		user.addBankCard(cartePaiement);

		Utils.trace("CB %s \n", cartePaiement);
		ICartePaiementCtrl cartePaiementCtrl = new CartePaiementCtrl();

		try {
			cartePaiementCtrl.addCartePaiement(cartePaiement);
		} catch (Exception e) {
			Utils.trace("catch create %s\n", e.toString());
		} finally {

		}

		Utils.trace("%s\n", cartePaiement);
	}
	// -------------------------------------------------------------------------------------------------

	public static void createMany() {
		Utils.trace("=========================== create many  ===========================\n");
//		int maxIndex = 3;
		int maxIndexUser = 10;

		CartePaiement cartePaiement = new CartePaiement();

		ICartePaiementCtrl cartePaiementCtrl = new CartePaiementCtrl();
		User user = new User();
		user = getUser(4);

		try {

			for (int indexUser = 1; indexUser < maxIndexUser; indexUser++) {

				int maxIndex = Utils.randInt(1, 4);
				user = getUser(indexUser);

				for (int index = 1; index < maxIndex; index++) {

					cartePaiement = DataTest.genCartePaiement(user);
					cartePaiement.setUser(user);

					user.addBankCard(cartePaiement);
					cartePaiementCtrl.addCartePaiement(cartePaiement);
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

		List<CartePaiement> cartePaiementList = new ArrayList<CartePaiement>();

		ICartePaiementCtrl cartePaiementCtrl = new CartePaiementCtrl();
		try {
			cartePaiementList = cartePaiementCtrl.getCartePaiements();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if ((cartePaiementList.size() > 0) && (cartePaiementList != null)) {
			for (CartePaiement cartePaiement : cartePaiementList) {
				Utils.trace("%s\n", cartePaiement);
			}
		} else
			Utils.trace("address null");
	}

//-------------------------------------------------------------------------------------------------	
	public static void readOne() {
		Utils.trace("=========================== read One  ===========================\n");
		int cartePaiementId = 14;
		CartePaiement cartePaiement = new CartePaiement();

		ICartePaiementCtrl cartePaiementCtrl = new CartePaiementCtrl();
		try {
			cartePaiement = cartePaiementCtrl.getCartePaiementById(cartePaiementId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (cartePaiement != null)
			Utils.trace("%s\n", cartePaiement);
		else
			Utils.trace("cartePaiement null\n");

	}
//-------------------------------------------------------------------------------------------------	

	public static User getUser(int userId) {

		User user = new User();
		IUserCtrl userCtrl = new UserCtrl();
		try {
			user = userCtrl.getUserById(userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;

	}
	// -------------------------------------------------------------------------------------------------

	public static Article getArticle(int idArticle) {
		Article article = new Article();
		IArticleDao articleDao = new ArticleDao();
		try {
			article = articleDao.getArticleById(idArticle);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return article;

	}

}
