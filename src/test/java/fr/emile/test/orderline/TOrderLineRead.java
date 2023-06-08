package orderline;

import java.util.ArrayList;
import java.util.List;

import fr.lotus.entity.OrderLine;
import fr.lotus.entity.OrderLine;
import fr.lotus.model.implement.ClassDao;
import fr.lotus.model.implement.CrudDao;
import fr.lotus.model.interfaces.ICrudDao;
import fr.lotus.utils.Utils;

public class TOrderLineRead {
	public static void main(String[] args) {
		Utils.trace("*************************** Begin ************************************\n");
		readMany();
//		readOne();
		Utils.trace("*************************** end ************************************\n");
		
	}
	//-------------------------------------------------------------------------------------------------	
	public static void readMany() {
		Utils.trace("=========================== read many  ===========================\n");

		List<ClassDao> orderLineList = new ArrayList<ClassDao>() ;
		OrderLine orderLine = new OrderLine() ;
		ICrudDao orderLineDao = new CrudDao(orderLine);
		try {
			orderLineList = orderLineDao.read();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			orderLineDao.close();
		}
		if ((orderLineList.size() >0  ) && (orderLineList != null)) {
			for (ClassDao classDao : orderLineList) {
				orderLine = (OrderLine)classDao ; 
				Utils.trace("%s\n",orderLine);
			}
		}
		else
			Utils.trace(" orderLineList vide");
	}
	//-------------------------------------------------------------------------------------------------	
	public static void readOne() {
		Utils.trace("=========================== read One  ===========================\n");
		int orderLineId =21;
		OrderLine orderLine = new OrderLine() ;
		ICrudDao orderLineDao = new CrudDao(orderLine);
		try {
			orderLine = (OrderLine) orderLineDao.read(orderLineId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (orderLine != null )
			Utils.trace("%s\n",orderLine);
		else 
			Utils.trace("orderLine null\n");
		
	}
}
