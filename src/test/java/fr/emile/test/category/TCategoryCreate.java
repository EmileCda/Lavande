package category;

import fr.lotus.entity.Category;
import fr.lotus.entity.Item;
import fr.lotus.model.implement.CrudDao;
import fr.lotus.model.interfaces.ICrudDao;
import fr.lotus.utils.DataTest;
import fr.lotus.utils.Utils;

public class TCategoryCreate {
	public static void main(String[] args) {

		Utils.trace("*************************** Begin ************************************\n");
		int maxIndex = 3;

		Category category = null;
		new Category();
		Item item;
		ICrudDao categoryDao = new CrudDao(category);
		try {
			for (int index = 0; index < maxIndex; index++) {
				category = DataTest.genCategory(); // generate random category
				int maxItem = Utils.randInt(1, 10);

				for (int nbItem = 0; nbItem < maxItem; nbItem++) {

					item = DataTest.genItem();
					item.setCategory(category);
					category.addItem(item);
				}
				category = (Category) categoryDao.create(category); // create category in database
				if (category != null)
					Utils.trace("%s\n", category);
				else
					Utils.trace("categoryAdded is nul\n");
			}
		} catch (Exception e) {
			Utils.trace("catch create %s\n", e.toString());
		} finally {
			categoryDao.close();
		}
		Utils.trace("*************************** end ************************************\n");
	}
}
