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
import fr.emile.utils.DataTest;
import fr.emile.utils.Utils;

@ManagedBean
@SessionScoped
public class ItemManagementBean extends MasterBean implements IConstant {

	List<Category> categoryList;
	Category currentCategory;
	Category categoryToUpdate;
	Item itemToUpdate;
	
	boolean falseValue = false;
	boolean trueValue = true;

	@ManagedProperty(value = "#{loginBean}")
	private LoginBean loginBean;

	public ItemManagementBean() {

		if (this.getCategoryList() == null) {
			this.setCategoryList(new ArrayList<Category>());
		}
		this.retreiveCategorieList();
		Category firstCategory = this.getCategoryList().get(0); // init category to the first in the list
		this.setCurrentCategory(new Category(firstCategory));
		this.setCategoryToUpdate(new Category());
		this.setItemToUpdate(new Item());

	}

// %%%%%%%%%%%%%%%%%%%%%%%%%%_action_%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

	public void updateCurrentCategory(ValueChangeEvent eventCategoryList) {
		this.cleanPromptStatus();
		int categoryId = (int) eventCategoryList.getNewValue();
		this.setCurrentCategory(categoryId);
		this.setCategoryToUpdate(this.getCurrentCategory());
	}

// %%%%%%%%%%%%%%%%%%%%%%%%%% action %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	public String updateCategory() {

		Category category = this.getCategoryToUpdate();
		String pageReturn = null;
		CrudCtrl categoryCtrl = new StandardCrudCtrl(new Category());
		try {
			if (category.getId() > 0)
				categoryCtrl.update(category);
			else
				categoryCtrl.create(category);

			this.getCategoryToUpdate().clean();
		} catch (Exception e) {
			Utils.trace("catch create %s\n", e.toString());
		}
		this.retreiveCategorieList();
		return pageReturn;

	}

// %%%%%%%%%%%%%%%%%%%%%%%%%% action %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	public String deleteItem(CartItem cartItem) {
		String pageReturn = null;
		String message = "";
		boolean result = this.getLoginBean().getCostumer().removeCartItem(cartItem);
		message = String.format("%s remove %", result ? "success" : "error", cartItem.getItem().getName());
		this.getLoginBean().setPromptStatus(message);
		return pageReturn;
	}

// %%%%%%%%%%%%%%%%%%%%%%%%%% action %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%


	public String deleteItem(Item item) {

		String pageReturn = null;
		return pageReturn;

	}
	// %%%%%%%%%%%%%%%%%%%%%%%%%%_action_%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

	public String modifyItem(Item item) {

		String pageReturn = null;
		this.setItemToUpdate(item);
		return pageReturn;

	}

	// %%%%%%%%%%%%%%%%%%%%%%%%%%_action_%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

	public String updateItem() {

		Utils.trace("String updateItem()");
		Item item = this.getItemToUpdate();

		String pageReturn = null;
		CrudCtrl itemCtrl = new StandardCrudCtrl(new Item());
		try {
			if (item.getId() > 0) {
				itemCtrl.update(item);
				// replace de modified item in the list
				for (Item currentItem : this.getCurrentCategory().getItemList()) {
					if (currentItem.getId() == item.getId()) {
						int index = this.getCurrentCategory().getItemList().indexOf(currentItem);
						this.getCurrentCategory().getItemList().set(index, item);
					}
				}
			} else {
				Utils.trace("itemCtrl.create%s\n", item);
				item.setCategory(this.getCurrentCategory());
				
				itemCtrl.create(item);
				this.getCurrentCategory().getItemList().add(item);
			}
			this.getCategoryToUpdate().clean();
		} catch (Exception e) {
			Utils.trace("catch create %s\n", e.toString());
		}

		return pageReturn;
	}

// %%%%%%%%%%%%%%%%%%%%%%%%%%_action_%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

	public String cheatCodeItem() {
		this.setItemToUpdate(DataTest.genItem());
		return null;
	}

// %%%%%%%%%%%%%%%%%%%%%%%%%%_action_%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

	public String cheatCodeCategory() {

		this.setCategoryToUpdate(DataTest.genCategory());
		return null;
	}

	// -+-+-+-+-+-+-+-+-+-+-+-+-+_processing_-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
	public Item ZgetItem(int itemId) {

		for (Item item : this.getCurrentCategory().getItemList()) {

			if (item.getId() == itemId)
				return item;
		}
		return null;
	}

	// -+-+-+-+-+-+-+-+-+-+-+-+-+_processing_-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+

	// -+-+-+-+-+-+-+-+-+-+-+-+-+_processing_-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
	public void retreiveCategorieList() {

		List<Object> objectList = new ArrayList<Object>();
		CrudCtrl categoryCtrl = new StandardCrudCtrl(new Category());

		try {
			objectList = categoryCtrl.list();
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
		this.currentCategory = currentCategory;
	}

	public void setCurrentCategory(int categoryId) {
		// this method is needed to change categoryId into indexof
		// categorieList in order to retreive the right category

		for (Category category : this.getCategoryList()) {
			if (category.getId() == categoryId) {
				this.setCurrentCategory(new Category(category));
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



	public boolean getFalseValue() {
		return falseValue;
	}

	public void setFalseValue(boolean falseValue) {
		this.falseValue = falseValue;
	}

	public boolean getTrueValue() {
		return trueValue;
	}

	public void setTrueValue(boolean trueValue) {
		this.trueValue = trueValue;
	}

	public Category getCategoryToUpdate() {
		return categoryToUpdate;
	}

	public void setCategoryToUpdate(Category categoryToUpdate) {
		this.categoryToUpdate = categoryToUpdate;
	}

	public Item getItemToUpdate() {
		return itemToUpdate;
	}

	public void setItemToUpdate(Item itemToUpdate) {
		this.itemToUpdate = itemToUpdate;
	}

}
