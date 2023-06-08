package address;

import fr.lotus.entity.Address;
import fr.lotus.entity.User;
import fr.lotus.model.implement.CrudDao;
import fr.lotus.model.interfaces.ICrudDao;
import fr.lotus.utils.Utils;

public class TAddressDelete {
	public static void main(String[] args) {
		
		Utils.trace("*************************** Begin ************************************");
		int rowId = 2;
		Address address = new Address();
		ICrudDao addressDao = new CrudDao(address);
		try {
			address = (Address) addressDao.read(rowId);
			if (address == null) 
				Utils.trace("Error : l'adresse n'existe pas");
			else {
				addressDao.delete(address );

				address = (Address) addressDao.read(rowId);

				if (address != null)
					Utils.trace("Error not remove");
				else
					Utils.trace("remove ok");
			}
		} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}finally {
			addressDao.close();
		}
		Utils.trace("*************************** end ************************************");
		
	}
}
