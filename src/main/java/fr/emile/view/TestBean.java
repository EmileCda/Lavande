package fr.emile.view;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import fr.emile.common.IConstant;
import fr.emile.ctrl.CostumerCtrl;
import fr.emile.ctrl.UserCtrl;
import fr.emile.entity.Costumer;
import fr.emile.entity.User;
import fr.emile.utils.Utils;

@ManagedBean
@SessionScoped
public class TestBean implements IConstant {

	@ManagedProperty(value = "#{loginBean}")
	private LoginBean loginBean;

	@ManagedProperty(value = "#{purchaseBean}")
	private PurchaseBean purchaseBean;

	@ManagedProperty(value = "#{itemManagementBean}")
	private ItemManagementBean itemManagementBean;

	@ManagedProperty(value = "#{settingBean}")
	private SettingBean settingBean;

	
// %%%%%%%%%%%%%%%%%%%%%%%%%%_action_%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

	public String testAddBankCard() {
		String pageReturn = SETTING;
		this.getLoginBean().cleanPromptStatus();
		int userId = 31;
		this.getLoginBean().setCostumer(getCostumer(userId));
		this.getLoginBean().setUser(getUser(userId));
		this.getSettingBean().setSettingCostumer(this.getLoginBean().getCostumer());
		this.getSettingBean().setSettingBankCard(this.getSettingBean().getSettingCostumer().getBankCardList().get(0));
		
		this.getLoginBean().setPromptStatus("testAddBankCard()");
		return pageReturn;
		
	}

	// -+-+-+-+-+-+-+-+-+-+-+-+-+_processing_-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+

	public Costumer getCostumer(int id) {
		Costumer costumer = null; 
		CostumerCtrl costumerCtrl = new CostumerCtrl();
		try {
			costumer  = (Costumer) costumerCtrl.read(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return costumer;
		
	}

	// -+-+-+-+-+-+-+-+-+-+-+-+-+_processing_-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+

	public User getUser(int id) {
		User user = null;
		UserCtrl userCtrl = new UserCtrl();

		try {
			user = (User) userCtrl.read(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;

	}

// -+-+-+-+-+-+-+-+-+-+-+-+-+_processing_-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
	public LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}

	public PurchaseBean getPurchaseBean() {
		return purchaseBean;
	}

	public void setPurchaseBean(PurchaseBean purchaseBean) {
		this.purchaseBean = purchaseBean;
	}

	public ItemManagementBean getItemManagementBean() {
		return itemManagementBean;
	}

	public void setItemManagementBean(ItemManagementBean itemManagementBean) {
		this.itemManagementBean = itemManagementBean;
	}

	public SettingBean getSettingBean() {
		return settingBean;
	}

	public void setSettingBean(SettingBean settingBean) {
		this.settingBean = settingBean;
	}

}
