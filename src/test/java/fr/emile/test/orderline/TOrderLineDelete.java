package orderline;

import fr.lotus.entity.OrderLine;
import fr.lotus.entity.OrderLine;
import fr.lotus.model.implement.CrudDao;
import fr.lotus.model.interfaces.ICrudDao;
import fr.lotus.utils.Utils;

public class TOrderLineDelete {
	public static void main(String[] args) {
		
		Utils.trace("*************************** Begin ************************************\n");
		
		int rowId =  21;
		OrderLine orderLine = new OrderLine();
		ICrudDao orderLineDao = new CrudDao(orderLine);
		try {
			orderLine = (OrderLine) orderLineDao.read(rowId);
			if (orderLine == null) 
				Utils.trace("Error : OrderLine n'existe pas\n");
			else {
				Utils.trace("last time seen: %s\n",orderLine);
				orderLineDao.delete(orderLine );
				orderLine = (OrderLine) orderLineDao.read(rowId);
				if (orderLine != null)	
					Utils.trace("Error : not remove\n");
				else
					Utils.trace("remove ok\n");
			}
		} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}finally {
			orderLineDao.close();
		}
		
		Utils.trace("*************************** end ************************************\n");
		
	}
}
