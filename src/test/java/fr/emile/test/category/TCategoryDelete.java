package category;

import fr.lotus.entity.Category;

import fr.lotus.model.implement.CrudDao;
import fr.lotus.model.interfaces.ICrudDao;
import fr.lotus.utils.Utils;

public class TCategoryDelete {
	public static void main(String[] args) {
		
		Utils.trace("*************************** Begin ************************************\n");
		
		int rowId =  1;
		Category category = new Category();
		ICrudDao categoryDao = new CrudDao(category);
		try {
			category = (Category) categoryDao.read(rowId);
			if (category == null) 
				Utils.trace("Error : category n'existe pas\n");
			else {
				Utils.trace("%s\n",category);
				categoryDao.delete(category );
				category = (Category) categoryDao.read(rowId);
				if (category != null)
					Utils.trace("Error not remove\n");
				else
					Utils.trace("remove ok\n");
			}
		} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}finally {
			categoryDao.close();
		}
		
		Utils.trace("*************************** end ************************************\n");
		
	}
}
