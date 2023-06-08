package costumer;

import fr.lotus.entity.Costumer;
import fr.lotus.entity.User;
import fr.lotus.model.implement.CrudDao;
import fr.lotus.model.interfaces.ICrudDao;
import fr.lotus.utils.Utils;

public class TCostumerUpdate {
	public static void main(String[] args) {
		
		Utils.trace("*************************** Begin ************************************");
		

		int rowId = 2;
		Costumer costumer =  new Costumer();
		ICrudDao costumerDao = new CrudDao(costumer) ;
		try {
			costumer = (Costumer) costumerDao.read(rowId );
			if (costumer == null )
				Utils.trace("Costumer null ");
			else {
				Utils.trace("Before" + costumer.toString());

				// -------------------------- update ----------------------
				costumer.setLastname("changement de nom");
				costumerDao.update(costumer);
	
				costumer = (Costumer) costumerDao.read(rowId);
				if (costumer != null )
					Utils.trace("After" + costumer.toString());
				else
					Utils.trace("Costumer null ");
			}

		} catch (

				Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}finally {
			costumerDao.close();
		}
		
		Utils.trace("*************************** end ************************************");
		
	}
}
