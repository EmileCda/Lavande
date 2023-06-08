package category;

import java.util.ArrayList;
import java.util.List;

import fr.lotus.entity.Category;
import fr.lotus.model.implement.ClassDao;
import fr.lotus.model.implement.CrudDao;
import fr.lotus.model.interfaces.ICrudDao;
import fr.lotus.utils.Utils;

public class TCategoryRead {
	public static void main(String[] args) {
		Utils.trace("*************************** Begin ************************************\n");
		readMany();
//		readOne();
		Utils.trace("*************************** end ************************************\n");
		
	}
	//-------------------------------------------------------------------------------------------------	
	public static void readMany() {
		Utils.trace("=========================== read many  ===========================\n");

		List<ClassDao> categoryList = new ArrayList<ClassDao>() ;
		Category category = new Category() ;
		ICrudDao categoryDao = new CrudDao(category);
		try {
			categoryList = categoryDao.read();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			categoryDao.close();
		}
		if ((categoryList.size() >0  ) && (categoryList != null)) {
			for (ClassDao classDao : categoryList) {
				category = (Category)classDao ; 
				Utils.trace("%s\n",category); 
			}
		}
		else
			Utils.trace(" categoryList vide");
	}
	//-------------------------------------------------------------------------------------------------	
	public static void readOne() {
		Utils.trace("=========================== read One  ===========================\n");
		int categoryId = 1;
		Category category = new Category() ;
		ICrudDao categoryDao = new CrudDao(category);
		try {
			category = (Category) categoryDao.read(categoryId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (category != null )
			Utils.trace(category.toString());
		else 
			Utils.trace("category null");
		
	}
}
