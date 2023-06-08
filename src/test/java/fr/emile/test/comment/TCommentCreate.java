package comment;

import fr.lotus.entity.Costumer;
import fr.lotus.entity.Item;
import fr.lotus.entity.Comment;
import fr.lotus.model.implement.CrudDao;
import fr.lotus.model.interfaces.ICrudDao;
import fr.lotus.utils.DataTest;
import fr.lotus.utils.Utils;

public class TCommentCreate {
	public static void main(String[] args) {
		
		Utils.trace("*************************** Begin ************************************\n");
//		createOne(2,2);
		
		for (int userId = 1; userId < 5 ; userId++) {
			for (int itemId = 1; itemId < 5 ; itemId++) {
				createOne(userId,itemId);
			}
		}
		
		Utils.trace("*************************** end ************************************\n");
	
	}
	//-------------------------------------------------------------------------------------------------	
	public static void createOne(int costumerId, int itemId) {
		Utils.trace("************************** create one **********************************\n");
		Comment commentAdded = null  ;		// generate random comment
		Comment comment = DataTest.genComment() ;		// generate random comment
		Costumer costumer = retrieveCostumer(costumerId);
		Item	item = retrieveItem(itemId);
		
		comment.setCostumer(costumer);
		comment.setItem(item);

		costumer.addComment(comment);
		item.addComment(comment);
		
		ICrudDao commentDao = new CrudDao(comment);

		try {
			commentAdded = (Comment) commentDao.create(comment); // create comment in database
			if (commentAdded != null)
				Utils.trace("%s\n",commentAdded);
			else
				Utils.trace("%s\n","commentAdded is null");
		} catch (Exception e) {
			Utils.trace("catch create : %s\n", e.toString());
		}finally {
			commentDao.close();
		}
	}
	//-------------------------------------------------------------------------------------------------	
	public static void displayOneComment() {
		Utils.trace("************************** Display one comment**********************************\n");

		Comment comment = new Comment() ;
		comment = DataTest.genComment() ;	
		Utils.trace("%s\n",comment);
	}
	
	//-------------------------------------------------------------------------------------------------	
	public static Costumer retrieveCostumer(int id) {
		
		Utils.trace("=========================== read One Costumer [%d]  ===========================\n",id);
		Costumer costumer = new Costumer() ;
		ICrudDao costumerDao = new CrudDao(costumer);

		try {
			costumer = (Costumer) costumerDao.read(id);
			if (costumer != null )
				Utils.trace("%s\n",costumer);
			else
				Utils.trace("costumer is null" );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			costumerDao.close();
		}
		
		return  costumer;
		
	}
	//-------------------------------------------------------------------------------------------------	
	public static Item retrieveItem(int id) {
		
		Utils.trace("=========================== read One Item [%d]  ===========================\n",id);
		Item item = new Item() ;
		ICrudDao itemDao = new CrudDao(item);
		try {
			item = (Item) itemDao.read(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (item != null )
			Utils.trace("%s\n",item);
		else 
			Utils.trace("item null\n");
		
		return  item;
	}
}
