package bankcard;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import fr.lotus.entity.BankCard;
import fr.lotus.model.implement.ClassDao;
import fr.lotus.model.implement.CrudDao;
import fr.lotus.model.interfaces.ICrudDao;
import fr.lotus.utils.Utils;

public class TBankCardRead {
	public static void main(String[] args) {
		
		Utils.trace("*************************** Begin ************************************");
		readOne();
//		readMany();
		Utils.trace("*************************** end ************************************");
		
	}
	
	//-------------------------------------------------------------------------------------------------	
	public static void readMany() {
		Utils.trace("=========================== read many  ===========================");
		List<ClassDao> bankCardList = new ArrayList<ClassDao>() ;
		BankCard bankCard =  new BankCard();
		ICrudDao bankCardDao = new CrudDao(bankCard) ;
		Utils.trace(" readMany ");
		try {
			Utils.trace(" readMany ");
			bankCardList = bankCardDao.read();
			Utils.trace(" readMany ");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			bankCardDao.close();
		}
		
		if ((bankCardList.size() >0  ) && (bankCardList != null)) {
			bankCard = new BankCard(); 
			for (ClassDao classDao : bankCardList) {
				bankCard = (BankCard)classDao ; 
				
				Utils.trace(bankCard.toString()); 
			}
				
		}
			
		else
			Utils.trace(" bankCardList vide");
		
	}
//-------------------------------------------------------------------------------------------------	
	public static void readOne() {
		Utils.trace("=========================== read One  ===========================");
		int bankCardId = 1 ;

		BankCard bankCard = new BankCard() ;
		ICrudDao bankCardDao = new CrudDao(bankCard) ;
		try {
			bankCard = (BankCard) bankCardDao.read(bankCardId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			bankCardDao.close();
		}
		if (bankCard != null )
			Utils.trace(bankCard.toString());
		else
			Utils.trace(" bankCard == null ");
		
	}
}
