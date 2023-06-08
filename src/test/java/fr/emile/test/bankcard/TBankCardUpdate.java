package bankcard;

import fr.lotus.entity.BankCard;
import fr.lotus.entity.BankCard;
import fr.lotus.entity.User;
import fr.lotus.model.implement.CrudDao;
import fr.lotus.model.interfaces.ICrudDao;
import fr.lotus.utils.Utils;

public class TBankCardUpdate {
	public static void main(String[] args) {
		
		Utils.trace("*************************** Begin ************************************");
		

		int rowId = 5;
		BankCard bankCard =  new BankCard();
		ICrudDao bankCardDao = new CrudDao(bankCard) ;
		try {
			bankCard = (BankCard) bankCardDao.read(rowId );
			if (bankCard == null )
				Utils.trace("BankCard null ");
			else {
				Utils.trace("Before" + bankCard.toString());

				// -------------------------- update ----------------------
				bankCard.setOwnerFirstname("Dieu le père");
				bankCardDao.update(bankCard);
	
				bankCard = (BankCard) bankCardDao.read(rowId);
				if (bankCard != null )
					Utils.trace("After" + bankCard.toString());
				else
					Utils.trace("BankCard null ");
			}

		} catch (

				Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}finally {
			bankCardDao.close();
		}
		Utils.trace("*************************** end ************************************");
		
	}
}
