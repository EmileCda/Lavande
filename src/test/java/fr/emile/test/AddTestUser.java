package fr.emile.test;

import fr.emile.ctrl.CostumerCtrl;
import fr.emile.ctrl.UserCtrl;
import fr.emile.entity.Costumer;
import fr.emile.entity.Item;
import fr.emile.entity.User;
import fr.emile.enums.Profile;
import fr.emile.utils.Utils;

public class AddTestUser {

	public static void main(String[] args) {

		Utils.trace("*************************** Begin ************************************\n");
		addUserTestAccount();
		addCostumerTestAccount();
		Utils.trace("*************************** end ************************************\n");
	}

	// -------------------------------------------------------------------------------------------------
	public static void addCostumerTestAccount() {
		CostumerCtrl costumerCtrl = new CostumerCtrl();
		Costumer costumerRetreive = new Costumer();
		String accountName = "c";
		try {
			costumerRetreive = (Costumer) costumerCtrl.read(accountName); // C for costumer

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (costumerRetreive == null)costumerRetreive = DataTest.genCostumer();

		costumerRetreive.setEmail(accountName);
		costumerRetreive.setPassword(accountName);
		costumerRetreive.setProfile(Profile.COSTUMER);
		try {
			if (costumerRetreive.getId() >0 )  costumerCtrl.update(costumerRetreive);
			else 	costumerCtrl.create(costumerRetreive);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// -------------------------------------------------------------------------------------------------
	public static void addUserTestAccount() {
		String StringArray[] = { "a", "m" };
		UserCtrl userCtrl = new UserCtrl();
		User user = new User();
		User userRetreive = new User();

		for (String string : StringArray) {
			try {
				userRetreive = (User) userCtrl.read(string); // admin
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (userRetreive == null) userRetreive = DataTest.genUser();
			
			userRetreive.setEmail(string);
			userRetreive.setPassword(string);
			userRetreive.setProfile(findProfile(string));
				
			try {
				if (userRetreive.getId() >0 ) userCtrl.update(userRetreive);
				else	userCtrl.create(userRetreive);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

//-------------------------------------------------------------------------------------------------
	public static Profile findProfile(String codeName) {
		
		
		switch (codeName) {
		case "a": return Profile.MANAGER; 
		case "m": return Profile.STORE_KEEPER ; 
			
		default: return null ; 
			
		}
		
		
	}
		


}
