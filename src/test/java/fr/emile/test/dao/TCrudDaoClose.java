package dao;

import fr.lotus.entity.BankCard;
import fr.lotus.entity.Costumer;
import fr.lotus.entity.User;
import fr.lotus.model.implement.CrudDao;
import fr.lotus.model.interfaces.ICrudDao;
import fr.lotus.utils.DataTest;
import fr.lotus.utils.Utils;

public class TCrudDaoClose {
	public static void main(String[] args) {
		
		Utils.trace("*************************** Begin ************************************");
		
		int userId = 2;
		Costumer costumer = new Costumer() ;
		ICrudDao costumerDao = new CrudDao(costumer);
		try {
			costumer = (Costumer) costumerDao.read(userId);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			costumerDao.close();	
		}
		Utils.trace(costumer.toString());
		
		BankCard bankCard = new BankCard() ;
		bankCard = DataTest.genBankCard(costumer);
		Utils.trace(bankCard.toString());
		
		bankCard.setCostumer(costumer);
		
		ICrudDao bankCardDao = new CrudDao(bankCard);
		BankCard bankCardAdded = new BankCard() ;
		
		try {
			bankCardAdded = (BankCard) bankCardDao.create(bankCard);
		} catch (Exception e) {
			Utils.trace("catch create " + e.toString());
		}finally {
			bankCardDao.close();	
		}
		
		 
		Utils.trace("*************************** end ************************************");
		
	}
}
