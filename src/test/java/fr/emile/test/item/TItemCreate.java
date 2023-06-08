package item;

import fr.lotus.entity.Category;
import fr.lotus.entity.Costumer;
import fr.lotus.entity.Item;
import fr.lotus.model.implement.CrudDao;
import fr.lotus.model.interfaces.ICrudDao;
import fr.lotus.utils.DataTest;
import fr.lotus.utils.Utils;

public class TItemCreate {
	public static void main(String[] args) {
		
		Utils.trace("*************************** Begin ************************************\n");

		int maxIndex = 10;
		Item item = new Item() ;
		Item itemAdded = new Item() ;
		item = DataTest.genItem() ;		// generate random item
		Utils.trace("%s\n",item);
		
		int categoryId = 2;
		Category category = new Category() ;
		ICrudDao categoryDao = new CrudDao(category);
		ICrudDao itemDao = new CrudDao(item);
		try {
			category = (Category) categoryDao.read(categoryId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (category == null ) {
			Utils.trace("category null\n");
			
		}else {
			item.setCategory(category);
			category.addItem(item);
			try {
				itemAdded = (Item) itemDao.create(item);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			if (itemAdded != null )
				Utils.trace("%s\n",itemAdded);
			else
				Utils.trace("itemAdded is null \n");
			
		}
				
		
		
		Utils.trace("*************************** end ************************************\n");
		
	}
}
