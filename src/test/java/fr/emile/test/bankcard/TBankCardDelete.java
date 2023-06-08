package bankcard;

import fr.lotus.entity.BankCard;
import fr.lotus.model.implement.CrudDao;
import fr.lotus.model.interfaces.ICrudDao;
import fr.lotus.utils.Utils;

public class TBankCardDelete {
	public static void main(String[] args) {
		
		Utils.trace("*************************** Begin ************************************");
		int rowId = 2;
		BankCard bankCard = new BankCard();
		ICrudDao bankCardDao = new CrudDao(bankCard);
		try {
			bankCard = (BankCard) bankCardDao.read(rowId);
			if (bankCard == null) 
				Utils.trace("Error : l'adresse n'existe pas");
			else {
				bankCardDao.delete(bankCard );
				bankCard = (BankCard) bankCardDao.read(rowId);
				if (bankCard != null)
					Utils.trace("Error not remove");
				else
					Utils.trace("remove ok");
			}
		} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}finally {
			bankCardDao.close();
		}	
		Utils.trace("*************************** end ************************************");
		
	}
}
