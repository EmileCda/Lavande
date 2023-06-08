package fr.ecommerce.test;

import java.util.ArrayList;
import java.util.List;

import fr.ecommerce.Ctrl.implement.UserCtrl;

import fr.ecommerce.Ctrl.interfaces.IUserCtrl;
import fr.ecommerce.entity.Article;
import fr.ecommerce.entity.Categorie;
import fr.ecommerce.entity.User;
import fr.ecommerce.model.dao.implement.ArticleDao;
import fr.ecommerce.model.dao.implement.CategorieDao;
import fr.ecommerce.model.dao.interfaces.IArticleDao;
import fr.ecommerce.model.dao.interfaces.ICategorieDao;
import fr.ecommerce.utils.DataTest;
import fr.ecommerce.utils.Utils;

public class TCategorie {
	
	public static void main(String[] args) {
		Utils.trace("*************************** Begin ************************************\n");
		createOne();
		createMany();
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
		int categorieId = 3;
		Categorie categorie = null ;  
		
		ICategorieDao categorieDao = new CategorieDao();
		try {
			categorie = categorieDao.getCategorieById(categorieId);
			if (categorie == null )
				Utils.trace("Address null\n");
			else {
				Utils.trace("Before  %s\n", categorie);

				// -------------------------- update ----------------------
				categorie.setName("%%%" + categorie.getName() + "%%%");
				categorieDao.updateCategorie(categorie);
	
				categorie = categorieDao.getCategorieById(categorieId);
				if (categorie != null )
					Utils.trace("After %s\n", categorie);
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
		Categorie categorie = new Categorie();
		ICategorieDao categorieDao = new CategorieDao();
		try {
			categorie = categorieDao.getCategorieById(addressId);
			if (categorie == null) 
				Utils.trace("Error : l'categorie n'existe pas\n");
			else {
				categorieDao.deleteCategorie(categorie );

				categorie = categorieDao.getCategorieById(addressId);

				if (categorie != null)
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

		Categorie categorie = new Categorie();
		
		categorie = DataTest.genCategorie();
		

		Utils.trace("CB %s \n",categorie);
		ICategorieDao categorieDao= new CategorieDao();
		Utils.trace("CB %s \n",categorie);

		try {
			categorieDao.addCategorie(categorie);
		} catch (Exception e) {
			Utils.trace("catch create %s\n", e.toString());
		} finally {

		}

		Utils.trace("%s\n", categorie);
	}
	//-------------------------------------------------------------------------------------------------	

	public static void createMany() {
		Utils.trace("=========================== create many  ===========================\n");
		int maxIndex = 10;

		Categorie categorie = new Categorie();

		ICategorieDao categorieDao= new CategorieDao();
		User user = new User() ;
		user = getUser(4 );

		try {
			for (int index = 0; index < maxIndex; index++) {
				
				categorie = DataTest.genCategorie();
				categorieDao.addCategorie(categorie);
				Utils.trace("CB %s \n",categorie);

			}
		} catch (Exception e) {
			Utils.trace("catch create %s\n", e.toString());
		} finally {

		}
	}
	//-------------------------------------------------------------------------------------------------	
	public static void readMany() {
		Utils.trace("=========================== read many  ===========================\n");

		List<Categorie> categorieList = new ArrayList<Categorie>() ;

		ICategorieDao categorieCtrl= new CategorieDao();
		try {
			categorieList = categorieCtrl.getCategories();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if ((categorieList.size() >0  ) && (categorieList != null)) {
			for (Categorie categorie : categorieList) {
				Utils.trace("%s\n",categorie); 
			}
		}
		else
			Utils.trace("address null");
	}
//-------------------------------------------------------------------------------------------------	
	public static void readOne(int categorieId) {
		Utils.trace("=========================== read One  ===========================\n");

		Categorie categorie = new Categorie() ;

		ICategorieDao categorieCtrl= new CategorieDao();
		try {
			categorie = categorieCtrl.getCategorieById(categorieId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (categorie != null )
			Utils.trace("%s\n",categorie); 
		else 
			Utils.trace("categorie null\n");
		
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
