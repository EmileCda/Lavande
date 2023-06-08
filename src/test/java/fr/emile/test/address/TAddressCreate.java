package address;

import fr.lotus.entity.Address;
import fr.lotus.entity.Costumer;
import fr.lotus.model.implement.CrudDao;
import fr.lotus.model.interfaces.ICrudDao;
import fr.lotus.utils.DataTest;
import fr.lotus.utils.Utils;

public class TAddressCreate {
	public static void main(String[] args) {
		
		Utils.trace("*************************** Begin ************************************");
		int userId = 1;
		Costumer costumer = new Costumer() ;
//		IObjectDao costumerDao = new CostumerDao();
		ICrudDao costumerDao = new CrudDao(costumer);
		try {
			costumer = (Costumer) costumerDao.read(userId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			costumerDao.close();
		}
		if (costumer!=null)
			Utils.trace(costumer.toString());
		else 
			Utils.trace("costumer null");
		

		Address address = new Address() ;
		address = DataTest.genAddress();
		Utils.trace(address.toString());
		
		address.setCostumer(costumer);
//		costumer.addAddress(address);
//		Utils.trace(costumer.getAddressList().get(0).toString());
		
		ICrudDao addressDao = new CrudDao(address);
		Address addressAdded = new Address() ;
		
		try {
			addressAdded = (Address) addressDao.create(address);
		} catch (Exception e) {
			Utils.trace("catch create " + e.toString());
		}finally {
			addressDao.close();
		}
		
		Utils.trace(addressAdded.toString());

		Utils.trace("*************************** end ************************************");
		
	}
}
