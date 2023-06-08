package fr.ecommerce.test;


import java.util.ArrayList;
import java.util.List;

import fr.ecommerce.Ctrl.implement.UserCtrl;
import fr.ecommerce.Ctrl.interfaces.IUserCtrl;
import fr.ecommerce.entity.Adresse;
import fr.ecommerce.entity.User;
import fr.ecommerce.model.dao.implement.AdresseDao;
import fr.ecommerce.model.dao.interfaces.IAdresseDao;
import fr.ecommerce.utils.DataTest;
import fr.ecommerce.utils.Utils;

public class TAdresse {

	public static void main(String[] args) {
		Utils.trace("*************************** Begin ************************************\n");
//		createOne();
		createMany();
//		readMany();
//		readOne();
//		delete();
//		update();

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
		int adresseId = 5;
		Adresse adresse = null;

		IAdresseDao adresseDao = new AdresseDao();
		try {
			adresse = adresseDao.getAdresseById(adresseId);
			if (adresse == null)
				Utils.trace("Address null\n");
			else {
				Utils.trace("Before  %s\n", adresse);

				// -------------------------- update ----------------------
				adresse.setCity("*** mod ***" + adresse.getCity() + "*** mod ***");
				adresseDao.updateAdresse(adresse);

				adresse = adresseDao.getAdresseById(adresseId);
				if (adresse != null)
					Utils.trace("After %s\n", adresse);
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
		Adresse adresse = new Adresse();
		IAdresseDao adresseDao = new AdresseDao();
		try {
			adresse = adresseDao.getAdresseById(addressId);
			if (adresse == null)
				Utils.trace("Error : l'adresse n'existe pas\n");
			else {
				adresseDao.deleteAdresse(adresse);

				adresse = adresseDao.getAdresseById(addressId);

				if (adresse != null)
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

		User user = getUser(1);
		Adresse adresse = new Adresse();
		adresse = DataTest.genAdresse();
		adresse.setUser(user);
		user.addAddress(adresse);
		Utils.trace("%s\n", adresse);

		IAdresseDao adresseDao = new AdresseDao();

		try {
			adresseDao.addAdresse(adresse);
		} catch (Exception e) {
			Utils.trace("catch create %s\n", e.toString());
		} finally {

		}

		Utils.trace("%s\n", adresse);
	}
	// -------------------------------------------------------------------------------------------------

	public static void createMany() {
		Utils.trace("=========================== read many  ===========================\n");
		int maxIndex = 10;
		int maxIndexUser = 10;

		Adresse adresse = new Adresse();
		User user = new User();
		Utils.trace("%s\n", adresse);

		IAdresseDao adresseDao = new AdresseDao();

		try {

			for (int indexUser = 1; indexUser < maxIndexUser; indexUser++) {
				user = getUser(indexUser);
				for (int index = 0; index < maxIndex; index++) {
					adresse = DataTest.genAdresse();
					adresse.setUser(user);
					user.addAddress(adresse);
					adresseDao.addAdresse(adresse);
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

		List<Adresse> adresseList = new ArrayList<Adresse>();
		IAdresseDao adresseDao = new AdresseDao();
		try {
			adresseList = adresseDao.getAdresses();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if ((adresseList.size() > 0) && (adresseList != null)) {
			for (Adresse adresse : adresseList) {
				Utils.trace("%s\n", adresse);
			}
		} else
			Utils.trace("address null");
	}

//-------------------------------------------------------------------------------------------------	
	public static void readOne() {
		Utils.trace("=========================== read One  ===========================\n");
		int addressId = 1;
		Adresse adresse = new Adresse();
		IAdresseDao adresseDao = new AdresseDao();
		try {
			adresse = adresseDao.getAdresseById(addressId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (adresse != null)
			Utils.trace("%s\n", adresse);
		else
			Utils.trace("address null\n");

	}
	// -------------------------------------------------------------------------------------------------

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

}
