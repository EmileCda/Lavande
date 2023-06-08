package address;

import fr.lotus.entity.Address;
import fr.lotus.entity.User;
import fr.lotus.model.implement.CrudDao;
import fr.lotus.model.interfaces.ICrudDao;
import fr.lotus.utils.Utils;

public class TAddressUpdate {
	public static void main(String[] args) {

		Utils.trace("*************************** Begin ************************************");

		int rowId = 5;
		Address address = new Address();
		ICrudDao addressDao = new CrudDao(address);
		try {
			address = (Address) addressDao.read(rowId );
			if (address == null )
				Utils.trace("Address null ");
			else {
				Utils.trace("Before" + address.toString());

				// -------------------------- update ----------------------
				address.setCity("Palo-alto");
				addressDao.update(address);
	
				address = (Address) addressDao.read(rowId);
				if (address != null )
					Utils.trace("After" + address.toString());
				else
					Utils.trace("Address null ");
			}

		} catch (

				Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}finally {
			addressDao.close();
		}

		Utils.trace("*************************** end ************************************");

	}
}
