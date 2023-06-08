package fr.ecommerce.test;

import java.util.ArrayList;
import java.util.List;

import fr.ecommerce.Ctrl.implement.UserCtrl;

import fr.ecommerce.Ctrl.interfaces.IUserCtrl;
import fr.ecommerce.entity.Article;
import fr.ecommerce.entity.Commentaire;
import fr.ecommerce.entity.User;
import fr.ecommerce.model.dao.implement.ArticleDao;
import fr.ecommerce.model.dao.implement.CommentaireDao;
import fr.ecommerce.model.dao.interfaces.IArticleDao;
import fr.ecommerce.model.dao.interfaces.ICommentaireDao;
import fr.ecommerce.utils.DataTest;
import fr.ecommerce.utils.Utils;

public class TCommentaire {
	
	public static void main(String[] args) {
		Utils.trace("*************************** Begin ************************************\n");
//		createOne();
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
		int commentaireId = 3;
		Commentaire commentaire = null ;  
		
		ICommentaireDao commentaireDao = new CommentaireDao();
		try {
			commentaire = commentaireDao.getCommentaireById(commentaireId);
			if (commentaire == null )
				Utils.trace("Address null\n");
			else {
				Utils.trace("Before  %s\n", commentaire);

				// -------------------------- update ----------------------
				commentaire.setGrade(5);
				commentaireDao.updateCommentaire(commentaire);
	
				commentaire = commentaireDao.getCommentaireById(commentaireId);
				if (commentaire != null )
					Utils.trace("After %s\n", commentaire);
				else
					Utils.trace("commentaire null\n");
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
		Commentaire commentaire = new Commentaire();
		ICommentaireDao commentaireDao = new CommentaireDao();
		try {
			commentaire = commentaireDao.getCommentaireById(addressId);
			if (commentaire == null) 
				Utils.trace("Error : l'commentaire n'existe pas\n");
			else {
				commentaireDao.deleteCommentaire(commentaire );

				commentaire = commentaireDao.getCommentaireById(addressId);

				if (commentaire != null)
					Utils.trace("Error not remove\n");
				else
					Utils.trace("remove ok\n");
			}
		} catch (Exception e) {
				 				e.printStackTrace();
		}

	}
	//-------------------------------------------------------------------------------------------------	

	public static void createOne() {
		Utils.trace("=========================== create One  ===========================\n");

		Commentaire commentaire = new Commentaire();
		
		commentaire = DataTest.genCommentaire();
		
		User user = getUser(1);
		Article article = getArticle(1); 
		commentaire.setUser(user);
		commentaire.setItem(article);
		
		user.addComment(commentaire);
		article.addComment(commentaire);
		
		
		ICommentaireDao commentaireDao= new CommentaireDao();

		try {
			commentaireDao.addCommentaire(commentaire);
		} catch (Exception e) {
			Utils.trace("catch create %s\n", e.toString());
		} finally {

		}

		Utils.trace("%s\n", commentaire);
	}
	//-------------------------------------------------------------------------------------------------	

	public static void createMany() {
		Utils.trace("=========================== create many  ===========================\n");
		int maxIndex = 30;
		int maxUserIndex= 10;
		Commentaire commentaire = new Commentaire();

		ICommentaireDao commentaireDao= new CommentaireDao();
		User user = new User() ;

		try {
			for (int indexUser = 1; indexUser< maxUserIndex; indexUser++) {
		
				user = getUser(indexUser);
				for (int index = 1; index < maxIndex; index++) {
					Article article = getArticle(1);
					commentaire = DataTest.genCommentaire();
					commentaire.setUser(user);
					commentaire.setItem(article);
					article.addComment(commentaire);
					user.addComment(commentaire);
					
					commentaireDao.addCommentaire(commentaire);
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

		List<Commentaire> commentaireList = new ArrayList<Commentaire>() ;

		ICommentaireDao commentaireCtrl= new CommentaireDao();
		try {
			commentaireList = commentaireCtrl.getCommentaires();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if ((commentaireList.size() >0  ) && (commentaireList != null)) {
			for (Commentaire commentaire : commentaireList) {
				Utils.trace("%s\n",commentaire); 
			}
		}
		else
			Utils.trace("address null");
	}
//-------------------------------------------------------------------------------------------------	
	public static void readOne(int commentaireId) {
		Utils.trace("=========================== read One  ===========================\n");

		Commentaire commentaire = new Commentaire() ;

		ICommentaireDao commentaireCtrl= new CommentaireDao();
		try {
			commentaire = commentaireCtrl.getCommentaireById(commentaireId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (commentaire != null )
			Utils.trace("%s\n",commentaire); 
		else 
			Utils.trace("commentaire null\n");
		
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
