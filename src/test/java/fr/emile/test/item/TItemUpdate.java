package item;

import fr.lotus.entity.Item;
import fr.lotus.entity.Item;
import fr.lotus.model.implement.CrudDao;
import fr.lotus.model.interfaces.ICrudDao;
import fr.lotus.utils.Utils;

public class TItemUpdate {
	public static void main(String[] args) {
		
		Utils.trace("*************************** Begin ************************************\n");
		int rowId = 3;
		Item item =  new Item();
		ICrudDao itemDao = new CrudDao(item) ;
		try {
			item = (Item) itemDao.read(rowId );
			if (item == null )
				Utils.trace("Item null\n");
			else {
				Utils.trace("Before: %s\n",item);

				// -------------------------- update ----------------------
				item.setName("Name modified");
				itemDao.update(item);
	
				item = (Item) itemDao.read(rowId);
				if (item != null )
					Utils.trace("After %s\n", item.toString());
				else
					Utils.trace("Item null\n");
			}

		} catch (

				Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}finally {
			itemDao.close();
		}
		
		
		Utils.trace("*************************** end ************************************");
		
	}
}
