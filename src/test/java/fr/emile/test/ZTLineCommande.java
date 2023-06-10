package fr.ecommerce.test;

import java.util.ArrayList;
import java.util.List;

import fr.ecommerce.Ctrl.implement.UserCtrl;

import fr.ecommerce.Ctrl.interfaces.IUserCtrl;
import fr.ecommerce.entity.Article;
import fr.ecommerce.entity.Commande;
import fr.ecommerce.entity.LigneDeCommande;
import fr.ecommerce.entity.User;
import fr.ecommerce.model.dao.implement.ArticleDao;
import fr.ecommerce.model.dao.implement.CommandeDao;
import fr.ecommerce.model.dao.implement.LigneDeCommandeDao;
import fr.ecommerce.model.dao.interfaces.IArticleDao;
import fr.ecommerce.model.dao.interfaces.ICommandeDao;
import fr.ecommerce.model.dao.interfaces.ILigneDeCommandeDao;
import fr.ecommerce.utils.DataTest;
import fr.ecommerce.utils.Utils;

public class TLineCommande {
	
	public static void main(String[] args) {
		Utils.trace("*************************** Begin ************************************\n");
//		createOne();
//		createMany();
//		readOne(1);
//		readMany();
//		update();
		delete();

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
		int ligneDeCommandeId = 1;
		LigneDeCommande ligneDeCommande = null ;  
		
		ILigneDeCommandeDao ligneDeCommandeDao = new LigneDeCommandeDao();
		try {
			ligneDeCommande = ligneDeCommandeDao.getLigneDeCommandeById(ligneDeCommandeId);
			if (ligneDeCommande == null )
				Utils.trace("Address null\n");
			else {
				Utils.trace("Before  %s\n", ligneDeCommande);

				// -------------------------- update ----------------------
				ligneDeCommande.setQuantity(1000);
				ligneDeCommandeDao.updateLigneDeCommande(ligneDeCommande);
	
				ligneDeCommande = ligneDeCommandeDao.getLigneDeCommandeById(ligneDeCommandeId);
				if (ligneDeCommande != null )
					Utils.trace("After %s\n", ligneDeCommande);
				else
					Utils.trace("ligneDeCommande null\n");
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
		int id = 1;
		LigneDeCommande ligneDeCommande = new LigneDeCommande();
		ILigneDeCommandeDao ligneDeCommandeDao = new LigneDeCommandeDao();
		try {
			ligneDeCommande = ligneDeCommandeDao.getLigneDeCommandeById(id);
			if (ligneDeCommande == null) 
				Utils.trace("Error : l'ligneDeCommande n'existe pas\n");
			else {
				ligneDeCommandeDao.deleteLigneDeCommande(ligneDeCommande );

				ligneDeCommande = ligneDeCommandeDao.getLigneDeCommandeById(id);

				if (ligneDeCommande != null)
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

		LigneDeCommande ligneDeCommande = new LigneDeCommande();
		
		ligneDeCommande = DataTest.genLigneDeCommande();
		
		Article article = getArticle(2); 
		ligneDeCommande.setItem(article);
		
		Commande commande = getCommande(2); 
		ligneDeCommande.setOrder(commande);
		
		commande.addOrderLine(ligneDeCommande);
		article.addOrderLine(ligneDeCommande);
		
		
		ILigneDeCommandeDao ligneDeCommandeDao= new LigneDeCommandeDao();

		try {
			ligneDeCommandeDao.addLigneDeCommande(ligneDeCommande);
		} catch (Exception e) {
			Utils.trace("catch create %s\n", e.toString());
		} finally {

		}

		Utils.trace("%s\n", ligneDeCommande);
	}
	//-------------------------------------------------------------------------------------------------	

	public static void createMany() {
		Utils.trace("=========================== create many  ===========================\n");
		Commande commande = new Commande();
		Article article = new Article ();
		
		int maxArticleIndex= 5;
		int maxOrderIndex= 5;
		LigneDeCommande ligneDeCommande = new LigneDeCommande();

		ILigneDeCommandeDao ligneDeCommandeDao= new LigneDeCommandeDao();

		try {
			for (int indexArticle = 1; indexArticle< maxArticleIndex; indexArticle++) {

				article = getArticle(indexArticle ); 

				for (int indexOrder = 1; indexOrder< maxOrderIndex; indexOrder++) {
		
					commande = getCommande(3); 
					
					
					ligneDeCommande.setItem(article);			
					ligneDeCommande.setOrder(commande);
				
					commande.addOrderLine(ligneDeCommande);
					article.addOrderLine(ligneDeCommande);
					
					ligneDeCommandeDao.addLigneDeCommande(ligneDeCommande);
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

		List<LigneDeCommande> ligneDeCommandeList = new ArrayList<LigneDeCommande>() ;

		ILigneDeCommandeDao ligneDeCommandeDao= new LigneDeCommandeDao();
		try {
			ligneDeCommandeList = ligneDeCommandeDao.getLigneDeCommandes();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if ((ligneDeCommandeList.size() >0  ) && (ligneDeCommandeList != null)) {
			for (LigneDeCommande ligneDeCommande : ligneDeCommandeList) {
				Utils.trace("%s\n",ligneDeCommande); 
			}
		}
		else
			Utils.trace("address null");
	}
//-------------------------------------------------------------------------------------------------	
	public static void readOne(int ligneDeCommandeId) {
		Utils.trace("=========================== read One  ===========================\n");

		LigneDeCommande ligneDeCommande = new LigneDeCommande() ;

		ILigneDeCommandeDao ligneDeCommandeDao= new LigneDeCommandeDao();
		try {
			ligneDeCommande = ligneDeCommandeDao.getLigneDeCommandeById(ligneDeCommandeId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (ligneDeCommande != null )
			Utils.trace("%s\n",ligneDeCommande); 
		else 
			Utils.trace("ligneDeCommande null\n");
		
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
	
	//-------------------------------------------------------------------------------------------------	
	public static Commande getCommande(int commandeId) {

		Commande commande = new Commande() ;

		ICommandeDao commandeDao= new CommandeDao();
		try {
			commande = commandeDao.getCommandeById(commandeId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return commande ;
		
		
	}

}
