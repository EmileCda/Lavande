package item;

import fr.lotus.entity.Item;
import fr.lotus.model.implement.CrudDao;
import fr.lotus.model.interfaces.ICrudDao;
import fr.lotus.utils.Utils;

public class TItemDelete {
	public static void main(String[] args) {
		
		Utils.trace("*************************** Begin ************************************\n");
		
		int rowId =  4;
		Item item = new Item();
		ICrudDao itemDao = new CrudDao(item);
		try {
			item = (Item) itemDao.read(rowId);
			if (item == null) 
				Utils.trace("Error :item rowid[%d] n'existe pas\n",rowId);
			else {
				Utils.trace("%s\n",item);
				itemDao.delete(item );
				item = (Item) itemDao.read(rowId);
				if (item != null)
					Utils.trace("Error :item rowid[%d] existe toujours\n",rowId);
				else
					Utils.trace("remove rowId [%d] ok\n",rowId);
			}
		} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}finally {
			itemDao.close();
		}
		
		Utils.trace("*************************** end ************************************\n");
		
	}
}
