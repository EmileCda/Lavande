package fr.ecommerce.test;

import java.util.ArrayList;
import java.util.List;

import fr.ecommerce.entity.Article;
import fr.ecommerce.entity.Categorie;
import fr.ecommerce.model.dao.implement.ArticleDao;
import fr.ecommerce.model.dao.implement.CategorieDao;
import fr.ecommerce.model.dao.interfaces.IArticleDao;
import fr.ecommerce.model.dao.interfaces.ICategorieDao;
import fr.ecommerce.utils.DataTest;
import fr.ecommerce.utils.Utils;

public class TArticle {

	public static void main(String[] args) {
		Utils.trace("*************************** Begin ************************************\n");
//		createOne();
//		createMany();
		readOne();
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
		int articleId = 5;
		Article article = null;

		IArticleDao articleDao = new ArticleDao();
		try {
			article = articleDao.getArticleById(articleId);
			if (article == null)
				Utils.trace("Address null\n");
			else {
				Utils.trace("Before  %s\n", article);

				// -------------------------- update ----------------------
				article.setDescription("*** mod ***" + article.getDescription() + "*** mod ***");
				articleDao.updateArticle(article);

				article = articleDao.getArticleById(articleId);
				if (article != null)
					Utils.trace("After %s\n", article);
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
		Article article = new Article();
		IArticleDao articleDao = new ArticleDao();
		try {
			article = articleDao.getArticleById(addressId);
			if (article == null)
				Utils.trace("Error : l'article n'existe pas\n");
			else {
				articleDao.deleteArticle(article);

				article = articleDao.getArticleById(addressId);

				if (article != null)
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

		Article article = new Article();
		article = DataTest.genArticle();
		Utils.trace("%s\n", article);
		Categorie categorie = readOneCategory(1);
		article.setCategorie(categorie);
		categorie.addItem(article);

		IArticleDao articleDao = new ArticleDao();

		try {
			articleDao.addArticle(article);
		} catch (Exception e) {
			Utils.trace("catch create %s\n", e.toString());
		} finally {

		}

		Utils.trace("%s\n", article);
	}
	// -------------------------------------------------------------------------------------------------

	public static void createMany() {
		Utils.trace("=========================== read many  ===========================\n");
		int maxIndex = 5;
		int maxIndexCat = 17;

		Article article = new Article();
		Utils.trace("%s\n", article);
		Categorie categorie = readOneCategory(2);

		IArticleDao articleDao = new ArticleDao();

		try {
			for (int indexCat = 1; indexCat <= maxIndexCat; indexCat++) {
				categorie = readOneCategory(indexCat);
				maxIndex = Utils.randInt(1, 10);
				for (int index = 0; index < maxIndex; index++) {
					article = DataTest.genArticle();
					article.setCategorie(categorie);
					categorie.addItem(article);
					articleDao.addArticle(article);
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

		List<Article> articleList = new ArrayList<Article>();
		IArticleDao articleDao = new ArticleDao();
		try {
			articleList = articleDao.getArticles();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if ((articleList.size() > 0) && (articleList != null)) {
			for (Article article : articleList) {
				Utils.trace("%s\n", article);
			}
		} else
			Utils.trace("address null");
	}

//-------------------------------------------------------------------------------------------------	
	public static void readOne() {
		Utils.trace("=========================== read One  ===========================\n");
		int id = 1;
		Article article = new Article();
		IArticleDao articleDao = new ArticleDao();
		try {
			article = articleDao.getArticleById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (article != null) {
			Utils.trace("%s\n", article);
			Utils.trace("%s\n", article.toItemLabel());
		} else
			Utils.trace("article null\n");

	}

	public static Categorie readOneCategory(int categorieId) {

		Categorie categorie = null;

		ICategorieDao categorieCtrl = new CategorieDao();
		try {
			categorie = categorieCtrl.getCategorieById(categorieId);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Utils.trace("=======read One catégorie id %d  %s ==============\n", categorieId, categorie.getName());
		return categorie;

	}
}
