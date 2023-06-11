package fr.emile.view;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import fr.emile.common.IConstant;
import fr.emile.ctrl.CrudCtrl;
import fr.emile.ctrl.StandardCrudCtrl;
import fr.emile.entity.CartItem;
import fr.emile.entity.Category;
import fr.emile.entity.Item;
import fr.emile.utils.Utils;

@ManagedBean
@SessionScoped
public class ItemManagementBean extends MasterBean implements IConstant {

	List<Category> categoryList;
	Category currentCategory;
	List<String> pickUpItemList;
	@ManagedProperty(value = "#{loginBean}")
	private LoginBean loginBean;

	public ItemManagementBean() {

		if (this.getCategoryList() == null) {
			this.setCategoryList(new ArrayList<Category>());
		}
		this.retreiveCategorieList();
		Category  firstCategory = this.getCategoryList().get(0);		// init category to the first in the list
		this.setCurrentCategory(new Category(firstCategory ));

	}

// %%%%%%%%%%%%%%%%%%%%%%%%%% action %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

	public void updateCurrentCategory(ValueChangeEvent eventCategoryList) {
		this.cleanPromptStatus();
		int categoryId = (int) eventCategoryList.getNewValue();
		this.setCurrentCategory(categoryId);
//		this.getLoginBean()
//				.setPromptStatus(String.format("catégorie en cours : %s [%d]", 
//						this.getCurrentCategory().getName(),
//				this.getCurrentCategory().getItemList().size()
//				));
	}

// %%%%%%%%%%%%%%%%%%%%%%%%%% action %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

	public String addItemToCart() {
		String pageReturn = null;

		Item item = new Item();
		CartItem cartItem ;

		for (String itemStringId : this.getPickUpItemList()) {

			int itemId = Integer.parseInt(itemStringId);
			if (itemId > 0) {
				item = getItem(itemId);
				cartItem = new CartItem(1,this.loginBean.getCostumer(), item);
				this.getLoginBean().getCostumer().addCartItem(cartItem);
			}
		}
		this.getPickUpItemList().clear(); // clear the list once transfered in user.cartItemList
		this.getLoginBean().setLabelCart(String.format("%d", 
				this.getLoginBean().getCostumer().getCartItemList().size()));
		return pageReturn;

	}

	//-+-+-+-+-+-+-+-+-+-+-+-+-+ processing -+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
		public Item getItem(int itemId) {
			
			for (Item item : this.getCurrentCategory().getItemList()) {
				
				if (item.getId() == itemId) return item ; 
			}
			return null; 
		}

		//-+-+-+-+-+-+-+-+-+-+-+-+-+ processing -+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
			public void retreiveCategorieList() {

		List<Object> objectList = new ArrayList<Object>();
		CrudCtrl CategoryCtrl = new StandardCrudCtrl(new Category());

		try {
			objectList = CategoryCtrl.list();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if ((objectList.size() > 0) && (objectList != null)) {
			if (this.getCategoryList() != null)
				this.getCategoryList().clear();
			else
				this.setCategoryList(new ArrayList<Category>());

			for (Object object : objectList)
				this.getCategoryList().add((Category) object);
		}
	}

//-------------------------------------------------------------------------------------------------	
	public List<Category> getCategoryList() {
		return this.categoryList;
	}

	public void setCategoryList(List<Category> categorieList) {
		this.categoryList = categorieList;
	}

	public Category getCurrentCategory() {
		return this.currentCategory;
	}

	public void setCurrentCategory(Category currentCategory) {
		Utils.trace("%s\n", currentCategory);
		this.currentCategory = currentCategory;
		Utils.trace("%s\n", this.currentCategory );
	}

	public void setCurrentCategory(int categoryId) {
		// this method is needed to change categoryId into indexof
		// categorieList in order to retreive the right category

		for (Category category : this.getCategoryList()) {
			if (category.getId() == categoryId) {
				this.setCurrentCategory(new Category (category));
//				Utils.trace("%s\n", category);
				Utils.trace("%s\n", this.getCurrentCategory());
				break; // once found, no need to loop
			}
		}

	}

	public LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}

	public List<String> getPickUpItemList() {
		return pickUpItemList;
	}

	public void setPickUpItemList(List<String> pickUpItemList) {
		this.pickUpItemList = pickUpItemList;
	}

}
