package fr.emile.test;

import java.util.ArrayList;
import java.util.List;

import fr.emile.ctrl.CrudCtrl;
import fr.emile.ctrl.StandardCrudCtrl;
import fr.emile.ctrl.UserCtrl;
import fr.emile.entity.Category;
import fr.emile.entity.Costumer;
import fr.emile.entity.Item;
import fr.emile.entity.Comment;
import fr.emile.utils.Utils;

public class TComment {

	public static void main(String[] args) {
		Utils.trace("*************************** Begin ************************************\n");
		TCommentUnitTest unitTest = new TCommentUnitTest();
		unitTest.createOne();
//		unitTest.createMany();
//		unitTest.readOne(1);
//		unitTest.readMany();
//		unitTest.update();
//		unitTest.delete();
		Utils.trace("*************************** end ************************************\n");

	}

}

//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%Unit Test Object %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
class TCommentUnitTest {

	private CrudCtrl ctrl;
	private int maxRetry = 10;

	public TCommentUnitTest() {
		this.setCtrl(new StandardCrudCtrl(new Comment()));
	}

//-------------------------------------------------------------------------------------------------
	public void create() {
		Utils.trace("=========================== Create ===========================\n");
		createOne();
		createMany();

	}

//-------------------------------------------------------------------------------------------------
	public void read() {
		Utils.trace("=========================== Read ===========================\n");
		readMany();
		readOne(1);

	}

//-------------------------------------------------------------------------------------------------
	public void update() {
		int commentId = 3;
		Utils.trace("=========================== Update [%d]===========================\n",commentId);
		Comment comment = null;

		try {
			comment = (Comment) this.getCtrl().read(commentId);
			if (comment == null)
				Utils.trace("Address null\n");
			else {
				Utils.trace("Before:\t%s\n", comment);

				// -------------------------- update ----------------------
				comment.setGrade(comment.getGrade()+1);
				this.getCtrl().update(comment);

				comment = (Comment) this.getCtrl().read(commentId);
				if (comment != null)
					Utils.trace("After:\t%s\n", comment);
				else
					Utils.trace("Comment null\n");
			}

		} catch (Exception e) {
			Utils.trace("catch delete %s\n", e.toString());
		}
	}

//-------------------------------------------------------------------------------------------------
	public void delete() {
		Utils.trace("=========================== Delete ===========================\n");
		int addressId = 2;
		Comment comment = new Comment();

		try {
			comment = (Comment) this.getCtrl().read(addressId);
			if (comment == null)
				Utils.trace("Error : l'comment n'existe pas\n");
			else {
				Utils.trace("last time seen %s\n", comment);
				this.getCtrl().delete(comment);
				comment = (Comment) this.getCtrl().read(addressId);

				if (comment != null)
					Utils.trace("Error not remove\n");
				else
					Utils.trace("remove ok\n");
			}
		} catch (Exception e) {
			Utils.trace("catch delete %s\n", e.toString());
		}

	}
	// -------------------------------------------------------------------------------------------------

	public void createOne() {
		Utils.trace("=========================== create One  ===========================\n");
		Comment comment = new Comment();
		Item item = getItem(1);
		Costumer costumer = getCostumer(1);

		comment = DataTest.genComment();
		comment.setCostumer(costumer);
		comment.setItem(item);
		try {
			this.getCtrl().create(comment);
			Utils.trace("%s\n", comment);
		} catch (Exception e) {
			Utils.trace("catch create %s\n", e.toString());
		}

	}
	// -------------------------------------------------------------------------------------------------

	public void createMany() {
		Utils.trace("=========================== create many  ===========================\n");
		int maxComment = 10;
		int maxItem = 59;
		int maxIndexCostumer= 10;
		Comment comment = new Comment();
		Item item = new Item();
		Costumer costumer = new Costumer() ;

		try {
			for (int indexCostumer = 1; indexCostumer  <= maxIndexCostumer ; indexCostumer ++) {
				
				int maxCurrentComment = Utils.randInt(0, maxComment);
				costumer = getCostumer(indexCostumer);
				
				for (int indexComment = 1; indexComment <= maxCurrentComment; indexComment++) {

					comment = DataTest.genComment();
					item = getItem(Utils.randInt(1, maxItem));

					comment.setCostumer(costumer);
					comment.setItem(item);
					costumer.addComment(comment);
					item.addComment(comment);
					this.getCtrl().create(comment);
					Utils.trace("%s\n", comment);

				}
			}
		} catch (Exception e) {
			Utils.trace("catch createMany %s\n", e.toString());
		}
	}

	// -------------------------------------------------------------------------------------------------
	public void readMany() {
		Utils.trace("=========================== read many  ===========================\n");

		List<Object> commentList = new ArrayList<Object>();

		try {
			commentList = this.getCtrl().list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if ((commentList.size() > 0) && (commentList != null)) {
			for (Object object : commentList) {
				Utils.trace("%s\n", (Comment) object);
			}
		} else
			Utils.trace("address null");
	}

//-------------------------------------------------------------------------------------------------	
	public void readOne(int id) {
		Utils.trace("=========================== read One [%d] ===========================\n",id);

		Comment comment = new Comment();

		try {
			for (int index = id; index < this.getMaxRetry() + id; index++) {
				comment = (Comment) this.getCtrl().read(id);
				if (comment!= null)	break;
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (comment != null)
			Utils.trace("%s\n", comment);
		else
			Utils.trace("comment null\n");

	}
//-------------------------------------------------------------------------------------------------	

	public Category getCategory(int categoryId) {
		Category category = new Category();
		CrudCtrl categoryCtrl = new StandardCrudCtrl(new Category());

		try {
			category = (Category) categoryCtrl.read(categoryId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return category;
	}

//-------------------------------------------------------------------------------------------------	
	public Item getItem(int id) {
		Item item = new Item();
		CrudCtrl itemCtrl = new StandardCrudCtrl(new Item());

		try {
			for (int index = id; index < this.getMaxRetry() + id; index++) {
				item = (Item) itemCtrl.read(id);
				if (item != null)
					break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return item;
	}

//-------------------------------------------------------------------------------------------------	

	public Costumer getCostumer(int id) {

		Costumer costumer = new Costumer();
		UserCtrl costumerCtrl = new UserCtrl();
		try {
			for (int index = id; index < this.getMaxRetry() + id; index++) {
				costumer = (Costumer) costumerCtrl.read(id);
				if (costumer != null)
					break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return costumer;

	}

//-------------------------------------------------------------------------------------------------	
	public CrudCtrl getCtrl() {
		return this.ctrl;
	}

	public void setCtrl(CrudCtrl ctrl) {
		this.ctrl = ctrl;
	}

	public int getMaxRetry() {
		return maxRetry;
	}

	public void setMaxRetry(int maxRetry) {
		this.maxRetry = maxRetry;
	}
}
