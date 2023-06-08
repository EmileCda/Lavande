package bankcard;

import fr.lotus.entity.BankCard;
import fr.lotus.entity.Costumer;
import fr.lotus.model.implement.CrudDao;
import fr.lotus.model.interfaces.ICrudDao;
import fr.lotus.utils.DataTest;
import fr.lotus.utils.Utils;

public class TBankCardCreate {
	public static void main(String[] args) {
		
		Utils.trace("*************************** Begin ************************************");

		//--- retreive an existing costumer 
		int userId = 2;
		Costumer costumer = new Costumer() ;
		ICrudDao costumerDao = new CrudDao(costumer);
		try {
			costumer = (Costumer) costumerDao.read(userId);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			costumerDao.close();
		}		if (costumer != null)
			Utils.trace(costumer.toString());
		else
			Utils.trace("costumer is null ") ;
			
		
// creating a bankcard and assign it to the user 
		
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
		
		if (bankCardAdded != null ) 
			Utils.trace(bankCardAdded.toString());
		else 
			Utils.trace("bankCardAdded est null ");

		Utils.trace("*************************** end ************************************");
		
	}
}
