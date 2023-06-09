package fr.emile.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import fr.emile.ctrl.CrudCtrl;
import fr.emile.ctrl.StandardCrudCtrl;
import fr.emile.ctrl.UserCtrl;
import fr.emile.entity.Address;
import fr.emile.entity.Costumer;
import fr.emile.enums.Profile;
import fr.emile.utils.Utils;



public class TCostumer {

	public static void main(String[] args) {
		Utils.trace("*************************** Begin ************************************\n");
//		createOne();
		createMany();
//		readOne(10);
//		readMany();
//		update();
//		delete();

		Utils.trace("*************************** end ************************************\n");

	}

	// -------------------------------------------------------------------------------------------------

	// -------------------------------------------------------------------------------------------------
	public static void create() {
		Utils.trace("=========================== Create ===========================\n");
		createOne();
		createMany();

	}

//-------------------------------------------------------------------------------------------------
	public static void read() {
		Utils.trace("=========================== Read ===========================\n");
		readMany();
		readOne(1);

	}

	// -------------------------------------------------------------------------------------------------
	public static void update(Costumer costumer) {

		UserCtrl costumerCtrl = new UserCtrl();
		try {
			costumerCtrl.update(costumer );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// -------------------------------------------------------------------------------------------------
	public static void update() {
		int costumerId = 1;
		Costumer usrCheck ; 

		UserCtrl costumerCtrl = new UserCtrl();
		try {
			usrCheck = (Costumer) costumerCtrl.read(costumerId);
			if (usrCheck == null)
				Utils.trace("Costumer null \n");
			else {
				Utils.trace("Before  %s\n", usrCheck);
				usrCheck .setIsActif(false);
				costumerCtrl.update(usrCheck );
				usrCheck = (Costumer) costumerCtrl.read(costumerId);
				if (usrCheck != null)
					Utils.trace("After %s\n", usrCheck);
				else
					Utils.trace("Address null\n");
			}

		} catch (

		Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// -------------------------------------------------------------------------------------------------
	public static void delete() {
		int costumerId = 1;
		remove(costumerId);

	}

	// -------------------------------------------------------------------------------------------------
	public static void remove(int costumerId) {
		Costumer costumer = new Costumer();
		UserCtrl costumerCtrl = new UserCtrl();

		try {
			costumer = (Costumer) costumerCtrl.read(costumerId);
			if (costumer == null)
				Utils.trace("Error : l'costumer n'existe pas\n");
			else {
				costumerCtrl.delete(costumer);

				costumer = (Costumer) costumerCtrl.read(costumerId);

				if (costumer != null)
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

	public static int createOne() {

		Costumer costumer = new Costumer();
		costumer = DataTest.genCostumer();
		Utils.trace("%s\n", costumer);

		UserCtrl costumerCtrl = new UserCtrl();

		try {
			costumerCtrl.create(costumer);
		} catch (Exception e) {
			Utils.trace("catch create %s\n", e.toString());
		} finally {

		}

		Utils.trace("%s\n", costumer);
		return costumer.getId();
	}
	// -------------------------------------------------------------------------------------------------

	public static void createMany() {
		Utils.trace("=========================== read many  ===========================\n");
		int maxIndex = 10;
		Costumer costumer = new Costumer();
		UserCtrl costumerCtrl = new UserCtrl();
		try {
			for (int index = 0; index < maxIndex; index++) {
				costumer = DataTest.genCostumer();
				costumerCtrl.create(costumer);
				Utils.trace("%s\n", costumer);
			}
		} catch (Exception e) {
			Utils.trace("catch create %s\n", e.toString());

		}
	}

	// -------------------------------------------------------------------------------------------------
	public static void readMany() {
		Utils.trace("=========================== read many  ===========================\n");

		List<Object> costumerList = new ArrayList<Object>();
		UserCtrl costumerCtrl = new UserCtrl();
		try {
			costumerList = costumerCtrl.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if ((costumerList.size() > 0) && (costumerList != null)) {
			for (Object object : costumerList) {
				Utils.trace("%s\n", (Costumer) object);
			}
		} else
			Utils.trace("costumer null");
	}

	// -------------------------------------------------------------------------------------------------
	public static Costumer getCostumer(int costumerId) {

		Costumer costumer = new Costumer();
		UserCtrl costumerCtrl = new UserCtrl();
		try {
			costumer = (Costumer) costumerCtrl.read(costumerId);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return costumer;

	}

	// -------------------------------------------------------------------------------------------------
	public static Costumer getCostumer(String email) {

		Costumer costumer = new Costumer();
		UserCtrl costumerCtrl = new UserCtrl();
		try {
			costumer = (Costumer) costumerCtrl.read(email);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return costumer;

	}

	// -------------------------------------------------------------------------------------------------
	public static void readOne(String email) {
		Utils.trace("=========================== read One by email  ===========================\n");

		Costumer costumer = getCostumer(email);
		if (costumer != null)
			Utils.trace("%s\n", costumer);
		else
			Utils.trace("costumer null\n");

	}

	
	// -------------------------------------------------------------------------------------------------
	public static void readOne(int costumerId) {
		Utils.trace("=========================== read One  by Id===========================\n");
		
		Costumer costumer = getCostumer( costumerId);
		if (costumer != null)
			Utils.trace("%s\n", costumer);
		else
			Utils.trace("costumer null\n");

	}
// -------------------------------------------------------------------------------------------------
//		public static void addArticlePanier() {
//			Utils.trace("%s\n", "ici");
//			Costumer costumer = new Costumer();
//			Utils.trace("%s\n",costumer);
//			Article article = new Article();
//
//			for (int index = 10; index < 20; index ++) {
//				costumer = getCostumer(index);
//				if (costumer != null ) break; 
//				
//			}
//			
//			Utils.trace("%s\n",costumer);
//		
//			ArticlePanier  articlePanier;
//			for (int index= 10 ; index < 20 ; index++) {
//				
//				article= getArticle(index);
//				Utils.trace("%s\n",article);
//				
//				articlePanier = new ArticlePanier(100+index, article);
//				
//				costumer.addCartItem(articlePanier);
//				
//			}
//			Utils.trace("%s\n",costumer);
//			Utils.trace("%d\n",costumer.getCartItemList().size());
//			
//			for (ArticlePanier aPanier : costumer.getCartItemList()) {
//				Utils.trace("%s\n",
//						aPanier.getArticle().getName());
//				
//				Utils.trace("quatity %s\n",
//						aPanier.getQuantity());
//				
//				Utils.trace("%s \n",
//						aPanier.getCostumer().getEmail());
//			}
//			
//		}
//
//
//		
////-------------------------------------------------------------------------------------------------		
//		public static Article getArticle(int ArticleId) {
//			Article article = new Article();
//			IArticleDao articleDao = new ArticleDao();
//			try {
//				article = articleDao.getArticleById(ArticleId);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			return article ; 
//
//		}

}
