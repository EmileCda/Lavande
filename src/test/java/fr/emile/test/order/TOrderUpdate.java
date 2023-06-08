package order;

import fr.lotus.entity.Order;
import fr.lotus.entity.Order;
import fr.lotus.model.implement.CrudDao;
import fr.lotus.model.interfaces.ICrudDao;
import fr.lotus.utils.Utils;

public class TOrderUpdate {
	public static void main(String[] args) {
		
		Utils.trace("*************************** Begin ************************************\n");
		int rowId = 1;
		Order order =  new Order();
		ICrudDao orderDao = new CrudDao(order) ;
		try {
			order = (Order) orderDao.read(rowId );
			if (order == null )
				Utils.trace("Order null\n");
			else {
				Utils.trace("Before: %s\n",order);

				// -------------------------- update ----------------------
				order.setOrderNumber("Temporary-order-number");
				orderDao.update(order);
	
				order = (Order) orderDao.read(rowId);
				if (order != null )
					Utils.trace("After %s\n",order);
				else
					Utils.trace("Order null\n");
			}

		} catch (

				Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}finally {
			orderDao.close();
		}
		
		
		Utils.trace("*************************** end ************************************\n");
		
	}
}
