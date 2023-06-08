package costumer;

import fr.lotus.entity.Costumer;
import fr.lotus.entity.User;
import fr.lotus.model.implement.CrudDao;
import fr.lotus.model.interfaces.ICrudDao;
import fr.lotus.utils.Utils;

public class TCostumerDelete {
	public static void main(String[] args) {
		
		Utils.trace("*************************** Begin ************************************");
		
		int rowId = 1;
		Costumer costumer = new Costumer();
		ICrudDao costumerDao = new CrudDao(costumer);
		try {
			costumer = (Costumer) costumerDao.read(rowId);
			if (costumer == null) 
				Utils.trace("Error : l'adresse n'existe pas");
			else {
				costumerDao.delete(costumer );
				costumer = (Costumer) costumerDao.read(rowId);
				if (costumer != null)
					Utils.trace("Error not remove");
				else
					Utils.trace("remove ok");
			}
		} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}finally {
			costumerDao.close();
		}
		
		Utils.trace("*************************** end ************************************");
	}
}
