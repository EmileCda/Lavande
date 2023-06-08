package item;

import java.util.ArrayList;
import java.util.List;

import fr.lotus.entity.Item;
import fr.lotus.model.implement.ClassDao;
import fr.lotus.model.implement.CrudDao;
import fr.lotus.model.interfaces.ICrudDao;
import fr.lotus.utils.Utils;

public class TItemRead {
	public static void main(String[] args) {
		Utils.trace("*************************** Begin ************************************\n");
		readMany();
		readOne();
		Utils.trace("*************************** end ************************************\n");
		
	}
	//-------------------------------------------------------------------------------------------------	
	public static void readMany() {
		Utils.trace("=========================== read many  ===========================\n");

		List<ClassDao> itemList = new ArrayList<ClassDao>() ;
		Item item = new Item() ;
		ICrudDao itemDao = new CrudDao(item);
		try {
			itemList = itemDao.read();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			itemDao.close();
		}
		if ((itemList.size() >0  ) && (itemList != null)) {
			for (ClassDao classDao : itemList) {
				item = (Item)classDao ; 
				Utils.trace("%s\n",item); 
			}
		}
		else
			Utils.trace(" itemList vide");
	}
	//-------------------------------------------------------------------------------------------------	
	public static void readOne() {
		Utils.trace("=========================== read One  ===========================\n");
		int itemId = 3;
		Item item = new Item() ;
		ICrudDao itemDao = new CrudDao(item);
		try {
			item = (Item) itemDao.read(itemId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (item != null )
			Utils.trace("%s\n",item);
		else 
			Utils.trace("item null\n");
		
	}
}
