package fr.emile.view;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import fr.emile.common.IConstant;
import fr.emile.ctrl.CostumerCtrl;
import fr.emile.ctrl.CrudCtrl;
import fr.emile.ctrl.StandardCrudCtrl;
import fr.emile.entity.CartItem;
import fr.emile.entity.Category;
import fr.emile.entity.Costumer;
import fr.emile.entity.Item;
import fr.emile.entity.Order;
import fr.emile.entity.OrderLine;
import fr.emile.utils.DataTest;
import fr.emile.utils.Utils;

@ManagedBean
@SessionScoped
public class PurchaseBean extends MasterBean implements IConstant {

	List<String> pickUpItemList;
	Order order;

	@ManagedProperty(value = "#{itemManagementBean}")
	private ItemManagementBean itemManagementBean;
	@ManagedProperty(value = "#{loginBean}")
	private LoginBean loginBean;

	public PurchaseBean() {

		this.setPickUpItemList(new ArrayList<String>());
		this.setOrder(new Order());

	}

// %%%%%%%%%%%%%%%%%%%%%%%%%% action %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	public String deleteItem(CartItem cartItem) {
		String pageReturn = null;
		String message = "";
		message = String.format("%s remove ",  cartItem.getItem().getName());
		Utils.trace(String.format("%s remove", cartItem.getItem().getName()));
		boolean result = this.getLoginBean().getCostumer().removeCartItem(cartItem);
		Utils.trace("String.format( %b",result );
		message = String.format("%s", result ? "success" : "error");
		this.getLoginBean().setPromptStatus(message);
		return pageReturn;
	}

// %%%%%%%%%%%%%%%%%%%%%%%%%%_action_%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

	public String ValidateCart() {
		String pageReturn = POURCHASE_VALIDATED;
		float grossGrandTotal = 0;
		float totalDiscount = 0;
		float grossPrice = 0;
		int currentShippingCost = DEFAULT_SHIPPING_COST;
		order.setCostumer(this.getLoginBean().getCostumer());
		Utils.trace("%s\n", order);
		this.getLoginBean().getCostumer().addOrder(order);

		if (this.getOrder() == null)
			this.setOrder(new Order());

		this.getOrder().setShippingCosts(DEFAULT_SHIPPING_COST);
//		int nbCartItem = this.getLoginBean().getCostumer().getCartItemList().size();
		
		
		for (CartItem cartItem : this.getLoginBean().getCostumer().getCartItemList()) {
			OrderLine orderLine = new OrderLine(cartItem.getQuantity(), cartItem.getItem(), order);
			Utils.trace("%s\n",cartItem);
			this.getOrder().addOrderLine(orderLine);
			currentShippingCost += 1;
			grossPrice = cartItem.getItem().getPrice() * cartItem.getQuantity();
			grossGrandTotal += grossPrice;

			float itemDiscount = grossPrice * orderLine.getItem().getDiscount() / 100;

			if (orderLine.getItem().getCategory().isDiscountCumulative())
				itemDiscount = itemDiscount * orderLine.getItem().getCategory().getDiscount() / 100;

			totalDiscount += itemDiscount;

		}
		this.getLoginBean().getCostumer().getCartItemList().clear();
		this.updateCostumer();
		
		order.setOrderNumber(order.generateOrderNumber());
		order.setCreateDate(DATE_NOW);
		order.setDeliveryDate(Utils.addDate(this.getOrder().getCreateDate(), STANDARD_DELIVERY_TIME));
		order.setTotalDiscount(totalDiscount);
		order.setShippingCosts(currentShippingCost);
		order.setGrandTotal(grossGrandTotal - totalDiscount);
		int nbCard = this.getLoginBean().getCostumer().getAddressList().size();
		if (nbCard >0)	order.setDeliveryAddress(this.getLoginBean().getCostumer().getAddressList().get(0));
		else {
			this.getLoginBean().setPromptStatus("%s","pas de carte");
			return pageReturn;
		}
		
		int nbAddress = this.getLoginBean().getCostumer().getAddressList().size();
		if (nbAddress>0) {
			order.setBillingAddress(this.getLoginBean().getCostumer().getAddressList().get(0));
			order.setBankCardUsed(this.getLoginBean().getCostumer().getBankCardList().get(0));
		}
		else this.getLoginBean().setPromptStatus("%s","no address");

		Utils.trace("%s\n", order);
		return pageReturn;
	}

	// %%%%%%%%%%%%%%%%%%%%%%%%%%_action_%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

	public String addItemToCart() {
		String pageReturn = null;

		Item item = new Item();
		CartItem cartItem;

		for (String itemStringId : this.getPickUpItemList()) {

			int itemId = Integer.parseInt(itemStringId);
			if (itemId > 0) {
				item = this.getItem(itemId);
				cartItem = new CartItem(1, this.loginBean.getCostumer(), item);
				this.getLoginBean().getCostumer().addCartItem(cartItem);
			}
		}
		this.getPickUpItemList().clear(); // clear the list once transfered in user.cartItemList
		this.getLoginBean()
				.setLabelCart(String.format("%d", this.getLoginBean().getCostumer().getCartItemList().size()));
		return pageReturn;

	}

	// -+-+-+-+-+-+-+-+-+-+-+-+-+_processing_-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
	
	public void updateCostumer() {

		CostumerCtrl costumerCtrl =new CostumerCtrl();
		try {
			costumerCtrl.update(this.getLoginBean().getCostumer());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

// -+-+-+-+-+-+-+-+-+-+-+-+-+_processing_-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
	public Item getItem(int itemId) {

		for (Item item : this.getItemManagementBean().getCurrentCategory().getItemList()) {

			if (item.getId() == itemId)
				return item;
		}
		return null;
	}

//	=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-getters/setters-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=- 
	public List<String> getPickUpItemList() {
		return pickUpItemList;
	}

	public void setPickUpItemList(List<String> pickUpItemList) {
		this.pickUpItemList = pickUpItemList;
	}

	public LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}

	public ItemManagementBean getItemManagementBean() {
		return itemManagementBean;
	}

	public void setItemManagementBean(ItemManagementBean itemManagementBean) {
		this.itemManagementBean = itemManagementBean;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	// %%%%%%%%%%%%%%%%%%%%%%%%%%_action_%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

	// %%%%%%%%%%%%%%%%%%%%%%%%%%_action_%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

	// -+-+-+-+-+-+-+-+-+-+-+-+-+_processing_-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
	// -+-+-+-+-+-+-+-+-+-+-+-+-+_processing_-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+

	// -+-+-+-+-+-+-+-+-+-+-+-+-+_processing_-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
//-------------------------------------------------------------------------------------------------	
}
