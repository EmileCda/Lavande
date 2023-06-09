package fr.emile.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import fr.emile.ctrl.CrudCtrl;
import fr.emile.ctrl.StandardCrudCtrl;
import fr.emile.ctrl.UserCtrl;
import fr.emile.entity.Address;
import fr.emile.entity.User;
import fr.emile.enums.Profile;
import fr.emile.utils.Utils;



public class TUser {

	public static void main(String[] args) {
		Utils.trace("*************************** Begin ************************************\n");
//		addArticlePanier();
//		initUserTest();
//		createOne();
//		createMany();
//		readOne(1);
//		readMany();
//		update();
		delete();

		Utils.trace("*************************** end ************************************\n");

	}

	// -------------------------------------------------------------------------------------------------
	public static void initUserTest() {
		String StringArray[] = {"a","c","m"};
		User user = null ;
		int id;
		for (String string : StringArray) {
			user = getUser(string);
			if (user !=null) {
				remove(user.getId());
			}
			id = createOne();
			user = getUser(id);
			user.setEmail(string);
			user.setPassword(string);
			
			switch (string) {
			case "a" : user.setProfile(Profile.MANAGER); break ; 
			case "m" : user.setProfile(Profile.STORE_KEEPER); break ;
			default  : user.setProfile(Profile.COSTUMER); break ; 
			}
			
			update(user);
		}
//		

	}

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
	public static void update(User user) {

		UserCtrl userCtrl = new UserCtrl();
		try {
			userCtrl.update(user );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// -------------------------------------------------------------------------------------------------
	public static void update() {
		int userId = 1;
		User usrCheck ; 

		UserCtrl userCtrl = new UserCtrl();
		try {
			usrCheck = (User) userCtrl.read(userId);
			if (usrCheck == null)
				Utils.trace("User null \n");
			else {
				Utils.trace("Before  %s\n", usrCheck);
				usrCheck .setIsActif(false);
				userCtrl.update(usrCheck );
				usrCheck = (User) userCtrl.read(userId);
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
		int userId = 1;
		remove(userId);

	}

	// -------------------------------------------------------------------------------------------------
	public static void remove(int userId) {
		User user = new User();
		UserCtrl userCtrl = new UserCtrl();

		try {
			user = (User) userCtrl.read(userId);
			if (user == null)
				Utils.trace("Error : l'user n'existe pas\n");
			else {
				userCtrl.delete(user);

				user = (User) userCtrl.read(userId);

				if (user != null)
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

		User user = new User();
		user = DataTest.genUser();
		Utils.trace("%s\n", user);

		UserCtrl userCtrl = new UserCtrl();

		try {
			userCtrl.create(user);
		} catch (Exception e) {
			Utils.trace("catch create %s\n", e.toString());
		} finally {

		}

		Utils.trace("%s\n", user);
		return user.getId();
	}
	// -------------------------------------------------------------------------------------------------

	public static void createMany() {
		Utils.trace("=========================== read many  ===========================\n");
		int maxIndex = 10;
		User user = new User();
		UserCtrl userCtrl = new UserCtrl();
		try {
			for (int index = 0; index < maxIndex; index++) {
				user = DataTest.genUser();
				userCtrl.create(user);
				Utils.trace("%s\n", user);
			}
		} catch (Exception e) {
			Utils.trace("catch create %s\n", e.toString());

		}
	}

	// -------------------------------------------------------------------------------------------------
	public static void readMany() {
		Utils.trace("=========================== read many  ===========================\n");

		List<Object> userList = new ArrayList<Object>();
		UserCtrl userCtrl = new UserCtrl();
		try {
			userList = userCtrl.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if ((userList.size() > 0) && (userList != null)) {
			for (Object object : userList) {
				Utils.trace("%s\n", (User) object);
			}
		} else
			Utils.trace("user null");
	}

	// -------------------------------------------------------------------------------------------------
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

	// -------------------------------------------------------------------------------------------------
	public static User getUser(String email) {

		User user = new User();
		UserCtrl userCtrl = new UserCtrl();
		try {
			user = (User) userCtrl.read(email);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return user;

	}

	// -------------------------------------------------------------------------------------------------
	public static void readOne(String email) {
		Utils.trace("=========================== read One by email  ===========================\n");

		User user = getUser(email);
		if (user != null)
			Utils.trace("%s\n", user);
		else
			Utils.trace("user null\n");

	}

	
	// -------------------------------------------------------------------------------------------------
	public static void readOne(int userId) {
		Utils.trace("=========================== read One  by Id===========================\n");
		
		User user = getUser( userId);
		if (user != null)
			Utils.trace("%s\n", user);
		else
			Utils.trace("user null\n");

	}
// -------------------------------------------------------------------------------------------------
//		public static void addArticlePanier() {
//			Utils.trace("%s\n", "ici");
//			User user = new User();
//			Utils.trace("%s\n",user);
//			Article article = new Article();
//
//			for (int index = 10; index < 20; index ++) {
//				user = getUser(index);
//				if (user != null ) break; 
//				
//			}
//			
//			Utils.trace("%s\n",user);
//		
//			ArticlePanier  articlePanier;
//			for (int index= 10 ; index < 20 ; index++) {
//				
//				article= getArticle(index);
//				Utils.trace("%s\n",article);
//				
//				articlePanier = new ArticlePanier(100+index, article);
//				
//				user.addCartItem(articlePanier);
//				
//			}
//			Utils.trace("%s\n",user);
//			Utils.trace("%d\n",user.getCartItemList().size());
//			
//			for (ArticlePanier aPanier : user.getCartItemList()) {
//				Utils.trace("%s\n",
//						aPanier.getArticle().getName());
//				
//				Utils.trace("quatity %s\n",
//						aPanier.getQuantity());
//				
//				Utils.trace("%s \n",
//						aPanier.getUser().getEmail());
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
