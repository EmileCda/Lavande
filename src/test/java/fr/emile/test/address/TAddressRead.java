package address;

import java.util.ArrayList;
import java.util.List;

import fr.lotus.entity.Address;
import fr.lotus.model.implement.ClassDao;
import fr.lotus.model.implement.CrudDao;
import fr.lotus.model.interfaces.ICrudDao;
import fr.lotus.utils.Utils;

public class TAddressRead {
	public static void main(String[] args) {
		Utils.trace("*************************** Begin ************************************");
		readMany();
		readOne();
		Utils.trace("*************************** end ************************************");
		
	}
//-------------------------------------------------------------------------------------------------	
	public static void readMany() {
		Utils.trace("=========================== read many  ===========================");

		List<ClassDao> addressList = new ArrayList<ClassDao>() ;
		Address address = new Address() ;
		ICrudDao addressDao = new CrudDao(address);
		try {
			addressList = addressDao.read();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			addressDao.close();
		}
		if ((addressList.size() >0  ) && (addressList != null)) {
			for (ClassDao classDao : addressList) {
				address = (Address)classDao ; 
				Utils.trace(address.toString()); 
			}
		}
		else
			Utils.trace(" addressList vide");
	}
//-------------------------------------------------------------------------------------------------	
	public static void readOne() {
		Utils.trace("=========================== read One  ===========================");
		int addressId = 3;
		Address address = new Address() ;
		ICrudDao addressDao = new CrudDao(address);
		try {
			address = (Address) addressDao.read(addressId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (address != null )
			Utils.trace(address.toString());
		else 
			Utils.trace("address null");
		
	}
//-------------------------------------------------------------------------------------------------	
}
