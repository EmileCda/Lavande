package category;

import fr.lotus.entity.Category;
import fr.lotus.entity.Category;
import fr.lotus.model.implement.CrudDao;
import fr.lotus.model.interfaces.ICrudDao;
import fr.lotus.utils.Utils;

public class TCategoryUpdate {
	public static void main(String[] args) {
		
		Utils.trace("*************************** Begin ************************************\n");
		int rowId = 2;
		Category category =  new Category();
		ICrudDao categoryDao = new CrudDao(category) ;
		try {
			category = (Category) categoryDao.read(rowId );
			if (category == null )
				Utils.trace("Category null ");
			else {
				Utils.trace("Before %s\n",category);
				// -------------------------- update ----------------------
				category.setName("modified name");
				categoryDao.update(category);
				
				category = (Category) categoryDao.read(rowId);
				if (category != null )
					Utils.trace("After%s\n",category);
				else
					Utils.trace("Category null \n");
			}
		} catch (
				Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}finally {
			categoryDao.close();
		}
		Utils.trace("*************************** end ************************************\n");
		
	}
}
