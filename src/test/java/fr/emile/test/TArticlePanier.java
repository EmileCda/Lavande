package fr.ecommerce.test;

import java.util.ArrayList;
import java.util.List;

import fr.ecommerce.Ctrl.implement.UserCtrl;
import fr.ecommerce.Ctrl.interfaces.IUserCtrl;
import fr.ecommerce.entity.Article;
import fr.ecommerce.entity.ArticlePanier;
import fr.ecommerce.entity.User;
import fr.ecommerce.model.dao.implement.ArticleDao;
import fr.ecommerce.model.dao.implement.ArticlePanierDao;
import fr.ecommerce.model.dao.interfaces.IArticleDao;
import fr.ecommerce.model.dao.interfaces.IArticlePanierDao;
import fr.ecommerce.utils.DataTest;
import fr.ecommerce.utils.Utils;

public class TArticlePanier {

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
		int articlePanierId = 5;
		ArticlePanier articlePanier = null;

		IArticlePanierDao articlePanierDao = new ArticlePanierDao();
		try {
			articlePanier = articlePanierDao.getArticlePanierById(articlePanierId);
			if (articlePanier == null)
				Utils.trace("Address null\n");
			else {
				Utils.trace("Before  %s\n", articlePanier);

				// -------------------------- update ----------------------
				articlePanier.setQuantity(10000);
				articlePanierDao.updateArticlePanier(articlePanier);

				articlePanier = articlePanierDao.getArticlePanierById(articlePanierId);
				if (articlePanier != null)
					Utils.trace("After %s\n", articlePanier);
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
		ArticlePanier articlePanier = new ArticlePanier();
		IArticlePanierDao articlePanierDao = new ArticlePanierDao();
		try {
			articlePanier = articlePanierDao.getArticlePanierById(addressId);
			if (articlePanier == null)
				Utils.trace("Error : l'articlePanier n'existe pas\n");
			else {
				articlePanierDao.deleteArticlePanier(articlePanier);

				articlePanier = articlePanierDao.getArticlePanierById(addressId);

				if (articlePanier != null)
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
		User user = new User();
		Article article = new Article();
		ArticlePanier articlePanier = new ArticlePanier();
		
		
		user = getUser(1);
		article = getArticle(1);
		
		articlePanier = DataTest.genArticlePanier();

		articlePanier.setUser(user);
		user.addCartItem(articlePanier);

		articlePanier.setArticle(article);
		article.addCartItem(articlePanier);
		Utils.trace("%s\n", articlePanier);

		IArticlePanierDao articlePanierDao = new ArticlePanierDao();

		try {
			articlePanierDao.addArticlePanier(articlePanier);
		} catch (Exception e) {
			Utils.trace("catch create %s\n", e.toString());
		} finally {

		}

		Utils.trace("%s\n", articlePanier);
	}
	// -------------------------------------------------------------------------------------------------

	public static void createMany() {
		Utils.trace("=========================== create many  ===========================\n");
		int maxIndex = 10;
		int maxIndexUser = 10;
		User user = new User();
		Article article = new Article();

		ArticlePanier articlePanier = new ArticlePanier();

		IArticlePanierDao articlePanierDao = new ArticlePanierDao();

		try {
			for (int indexUser = 1; indexUser < maxIndexUser; indexUser++) {
				user = getUser(indexUser);

				for (int index = 1; index < maxIndex; index++) {
					article = getArticle(index);
					articlePanier = DataTest.genArticlePanier();
					articlePanier.setUser(user);
					user.addCartItem(articlePanier);

					articlePanier.setArticle(article);
					article.addCartItem(articlePanier);

					articlePanierDao.addArticlePanier(articlePanier);

					Utils.trace("%s\n", articlePanier);
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

		List<ArticlePanier> articlePanierList = new ArrayList<ArticlePanier>();
		IArticlePanierDao articlePanierDao = new ArticlePanierDao();
		try {
			articlePanierList = articlePanierDao.getArticlePaniers();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if ((articlePanierList.size() > 0) && (articlePanierList != null)) {
			for (ArticlePanier articlePanier : articlePanierList) {
				Utils.trace("%s\n", articlePanier);
			}
		} else
			Utils.trace("address null");
	}

//-------------------------------------------------------------------------------------------------	
	public static void readOne() {
		Utils.trace("=========================== read One  ===========================\n");
		int addressId = 1;
		ArticlePanier articlePanier = new ArticlePanier();
		IArticlePanierDao articlePanierDao = new ArticlePanierDao();
		try {
			articlePanier = articlePanierDao.getArticlePanierById(addressId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (articlePanier != null)
			Utils.trace("%s\n", articlePanier);
		else
			Utils.trace("address null\n");

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
