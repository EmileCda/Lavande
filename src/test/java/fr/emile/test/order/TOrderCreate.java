package order;

import fr.lotus.entity.Costumer;
import fr.lotus.entity.Order;
import fr.lotus.model.implement.CrudDao;
import fr.lotus.model.interfaces.ICrudDao;
import fr.lotus.utils.DataTest;
import fr.lotus.utils.Utils;

public class TOrderCreate {
	public static void main(String[] args) {
		
		Utils.trace("*************************** Begin ************************************\n");
		int costumerId= 1;

		
		int maxIndex = 10;
		Order order = new Order() ;
		Order orderAdded = null ; 
		ICrudDao orderDao = new CrudDao(order);
		
		Costumer costumer = new Costumer() ;
		ICrudDao costumerDao = new CrudDao(costumer);

		try {
			costumer = (Costumer) costumerDao.read(costumerId);
			if (costumer != null ) {
				Utils.trace(costumer.toString());
			}else {
				Utils.trace("costumer is null" );
				System.exit(costumerId);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			costumerDao.close();
		}
		
		
		
		try {
			for (int index = 0 ; index < maxIndex; index++) {

				order = DataTest.genOrder() ;		// generate random order
				order.setCostumer(costumer);
				costumer.addOrder(order);
				
				orderAdded = (Order) orderDao.create(order); // create order in database
				if (orderAdded != null)
					Utils.trace("%s\n",orderAdded);
				else
					Utils.trace("%s\n","orderAdded is null");
			}
		} catch (Exception e) {
			Utils.trace("catch create : %s\n", e.toString());
		}finally {
//			orderDao.close();
		}
		
		
		
		Utils.trace("*************************** end ************************************\n");
		
	}
}
