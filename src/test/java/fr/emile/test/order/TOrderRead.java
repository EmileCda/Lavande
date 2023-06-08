package order;

import java.util.ArrayList;
import java.util.List;

import fr.lotus.entity.Order;
import fr.lotus.entity.Order;
import fr.lotus.model.implement.ClassDao;
import fr.lotus.model.implement.CrudDao;
import fr.lotus.model.interfaces.ICrudDao;
import fr.lotus.utils.Utils;

public class TOrderRead {
	public static void main(String[] args) {
		Utils.trace("*************************** Begin ************************************\n");
		readMany();
//		readOne();
		Utils.trace("*************************** end ************************************\n");
		
	}
	//-------------------------------------------------------------------------------------------------	
	public static void readMany() {
		Utils.trace("=========================== read many  ===========================\n");

		List<ClassDao> orderList = new ArrayList<ClassDao>() ;
		Order order = new Order() ;
		ICrudDao orderDao = new CrudDao(order);
		try {
			orderList = orderDao.read();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			orderDao.close();
		}
		if ((orderList.size() >0  ) && (orderList != null)) {
			for (ClassDao classDao : orderList) {
				order = (Order)classDao ; 
				Utils.trace("%s\n",order);
			}
		}
		else
			Utils.trace(" orderList vide");
	}
	//-------------------------------------------------------------------------------------------------	
	public static void readOne() {
		Utils.trace("=========================== read One  ===========================\n");
		int orderId = 1;
		Order order = new Order() ;
		ICrudDao orderDao = new CrudDao(order);
		try {
			order = (Order) orderDao.read(orderId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (order != null )
			Utils.trace("%s\n",order);
		else 
			Utils.trace("order null\n");
		
	}
}
