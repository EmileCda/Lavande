package orderline;

import fr.lotus.entity.OrderLine;
import fr.lotus.entity.OrderLine;
import fr.lotus.model.implement.CrudDao;
import fr.lotus.model.interfaces.ICrudDao;
import fr.lotus.utils.Utils;

public class TOrderLineUpdate {
	public static void main(String[] args) {
		
		Utils.trace("*************************** Begin ************************************\n");
		int rowId = 23;
		OrderLine orderLine =  new OrderLine();
		ICrudDao orderLineDao = new CrudDao(orderLine) ;
		try {
			orderLine = (OrderLine) orderLineDao.read(rowId );
			if (orderLine == null )
				Utils.trace("OrderLine null\n");
			else {
				Utils.trace("Before: %s\n",orderLine);

				// -------------------------- update ----------------------
				orderLine.setQuantity(1);
				orderLineDao.update(orderLine);
	
				orderLine = (OrderLine) orderLineDao.read(rowId);
				if (orderLine != null )
					Utils.trace("After %s\n",orderLine);
				else
					Utils.trace("OrderLine null\n");
			}

		} catch (

				Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}finally {
			orderLineDao.close();
		}
		
		
		Utils.trace("*************************** end ************************************\n");
		
	}
}
