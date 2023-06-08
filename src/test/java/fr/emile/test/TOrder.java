package fr.ecommerce.test;

import java.util.ArrayList;
import java.util.List;

import fr.ecommerce.Ctrl.implement.UserCtrl;

import fr.ecommerce.Ctrl.interfaces.IUserCtrl;
import fr.ecommerce.entity.Article;
import fr.ecommerce.entity.Commande;
import fr.ecommerce.entity.User;
import fr.ecommerce.model.dao.implement.ArticleDao;
import fr.ecommerce.model.dao.implement.CommandeDao;
import fr.ecommerce.model.dao.interfaces.IArticleDao;
import fr.ecommerce.model.dao.interfaces.ICommandeDao;
import fr.ecommerce.utils.DataTest;
import fr.ecommerce.utils.Utils;

public class TOrder {
	
	public static void main(String[] args) {
		Utils.trace("*************************** Begin ************************************\n");
		createOne();
//		createMany();
//		readOne(1);
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
		readOne(1);

	}

//-------------------------------------------------------------------------------------------------
	public static void update() {
		Utils.trace("=========================== Update ===========================\n");
		int commandeId = 3;
		Commande commande = null ;  
		
		ICommandeDao commandeDao = new CommandeDao();
		try {
			commande = commandeDao.getCommandeById(commandeId);
			if (commande == null )
				Utils.trace("Address null\n");
			else {
				Utils.trace("Before  %s\n", commande);

				// -------------------------- update ----------------------
				commande.setOrderNumber("ORDER MODIFYED $$$$$");
				commandeDao.updateCommande(commande);
	
				commande = commandeDao.getCommandeById(commandeId);
				if (commande != null )
					Utils.trace("After %s\n", commande);
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
		Commande commande = new Commande();
		ICommandeDao commandeDao = new CommandeDao();
		try {
			commande = commandeDao.getCommandeById(addressId);
			if (commande == null) 
				Utils.trace("Error : l'commande n'existe pas\n");
			else {
				commandeDao.deleteCommande(commande );

				commande = commandeDao.getCommandeById(addressId);

				if (commande != null)
					Utils.trace("Error not remove\n");
				else
					Utils.trace("remove ok\n");
			}
		} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}

	}
	//-------------------------------------------------------------------------------------------------	

	public static void createOne() {
		Utils.trace("=========================== create One  ===========================\n");

		Commande commande = new Commande();
		
		commande = DataTest.genCommande();
		
		User user = getUser(1);
		commande.setUser(user);
		user.addOrder(commande);
		
		ICommandeDao commandeDao= new CommandeDao();

		try {
			commandeDao.addCommande(commande);
		} catch (Exception e) {
			Utils.trace("catch create %s\n", e.toString());
		} finally {

		}

		Utils.trace("%s\n", commande);
	}
	//-------------------------------------------------------------------------------------------------	

	public static void createMany() {
		Utils.trace("=========================== create many  ===========================\n");
		int maxIndex = 3;
		int maxUserIndex= 10;
		Commande commande = new Commande();

		ICommandeDao commandeDao= new CommandeDao();
		User user = new User() ;

		try {
			for (int indexUser = 1; indexUser< maxUserIndex; indexUser++) {
		
				user = getUser(indexUser);
				for (int index = 1; index < maxIndex; index++) {
					
					commande = DataTest.genCommande();
					commande.setUser(user);
					user.addOrder(commande);
					commandeDao.addCommande(commande);
				}
			}
		} catch (Exception e) {
			Utils.trace("catch create %s\n", e.toString());
		} finally {

		}
	}
	//-------------------------------------------------------------------------------------------------	
	public static void readMany() {
		Utils.trace("=========================== read many  ===========================\n");

		List<Commande> commandeList = new ArrayList<Commande>() ;

		ICommandeDao commandeCtrl= new CommandeDao();
		try {
			commandeList = commandeCtrl.getCommandes();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if ((commandeList.size() >0  ) && (commandeList != null)) {
			for (Commande commande : commandeList) {
				Utils.trace("%s\n",commande); 
			}
		}
		else
			Utils.trace("address null");
	}
//-------------------------------------------------------------------------------------------------	
	public static void readOne(int commandeId) {
		Utils.trace("=========================== read One  ===========================\n");

		Commande commande = new Commande() ;

		ICommandeDao commandeCtrl= new CommandeDao();
		try {
			commande = commandeCtrl.getCommandeById(commandeId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (commande != null )
			Utils.trace("%s\n",commande); 
		else 
			Utils.trace("commande null\n");
		
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
		return user ; 

	}
	//-------------------------------------------------------------------------------------------------	
	
	public static Article getArticle(int idArticle) {
		Article article = new Article() ;
		IArticleDao articleDao = new ArticleDao();
		try {
			article = articleDao.getArticleById(idArticle);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return article  ; 
		
	}

}
