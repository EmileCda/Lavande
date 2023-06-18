package fr.emile.view;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import fr.emile.common.IConstant;
import fr.emile.ctrl.BankCardCtrl;
import fr.emile.ctrl.CostumerCtrl;
import fr.emile.ctrl.CrudCtrl;
import fr.emile.ctrl.UserCtrl;
import fr.emile.entity.Address;
import fr.emile.entity.BankCard;
import fr.emile.entity.Costumer;
import fr.emile.entity.User;
import fr.emile.enums.Profile;
import fr.emile.utils.DataTest;
import fr.emile.utils.Utils;

@ManagedBean
@SessionScoped
public class SettingBean extends MasterBean implements IConstant {

	@ManagedProperty(value = "#{loginBean}")
	private LoginBean loginBean;

	private BankCard settingBankCard;
	private Address settingAddress;

	private Costumer settingCostumer;
	private String passwordConfirmation;

	
	
	public SettingBean() {
		
		this.setSettingBankCard(new BankCard());
		this.setSettingAddress(new Address());
		this.setSettingCostumer(new Costumer());
		

	}

	// %%%%%%%%%%%%%%%%%%%%%%%%%%_action_%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	public void bankCardChange(ValueChangeEvent eventBankCardList) {
		this.resetPromptStatus();
		int cardId = (int) eventBankCardList.getNewValue();
		Utils.trace("cardId : %d\n", cardId);
		this.setSettingBankCard(readBankCard(cardId));

		this.setPromptStatus("%s", this.getSettingBankCard().getCardNumber());
		Utils.trace("bankCard current: %s\n", this.getSettingBankCard());

	}

	// %%%%%%%%%%%%%%%%%%%%%%%%%% action %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	public String addUser() {
		this.resetPromptStatus();
		String pageReturn = ADMIN_HOME;
		User userToAdd = new User(this.getSettingCostumer().getProfile(), this.getSettingCostumer().getEmail(),
				this.getSettingCostumer().getPassword(), true);
		UserCtrl userCtrl = new UserCtrl();

		try {
			User newUser = (User) userCtrl.create(userToAdd);

			this.setPromptStatus(
					String.format("User [%s] %s added", newUser.getProfile().getName(), newUser.getEmail()));
		} catch (Exception e) {
			Utils.trace("catch addUser%s\n", e.toString());
		}
		Utils.trace("pageReturn %s\n", pageReturn);
		this.getSettingCostumer().clean();
		Utils.trace("pageReturn %s\n", pageReturn);
		return pageReturn;

	}

	// %%%%%%%%%%%%%%%%%%%%%%%%%%_action_%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	public String setting() {
		this.resetPromptStatus();
		String pageReturn = SETTING;
		Utils.trace("%s\n", "String setting");

		if (this.getLoginBean().getUser().getProfile() == Profile.COSTUMER) {
			this.setSettingCostumer(this.getLoginBean().getCostumer());

			if ((this.getSettingCostumer().getBankCardList().size() > 0) && (this.getSettingBankCard().getId() <= 0))
				this.setSettingBankCard(this.getSettingCostumer().getBankCardList().get(0));

			if ((this.getSettingCostumer().getAddressList().size() > 0) && (this.getSettingAddress().getId() <= 0))
				this.setSettingAddress(this.getSettingCostumer().getAddressList().get(0));

			this.setPromptStatus("Setting %s %s %s", this.getSettingCostumer().getProfile().getName(),
					this.getSettingCostumer().getFirstname(), this.getSettingCostumer().getLastname());

			Utils.trace("current bankcard %s\n", this.getSettingBankCard());
			Utils.trace("current Address %s\n", this.getSettingAddress());

		} else {
			this.getSettingCostumer().setId(this.getLoginBean().getUser().getId());
			this.getSettingCostumer().setPassword(this.getLoginBean().getUser().getPassword());
			this.getSettingCostumer().setProfile(this.getLoginBean().getUser().getProfile());
			this.setPromptStatus("Setting %s %s", this.getSettingCostumer().getProfile().getName(),
					this.getSettingCostumer().getEmail());

		}

		return pageReturn;

	}

	// %%%%%%%%%%%%%%%%%%%%%%%%%%_action_%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	public String updateBankCard() {
		this.resetPromptStatus();
		String pageReturn = null;
		CrudCtrl bankCardCtrl = new BankCardCtrl();
		if (this.getSettingBankCard().getId() <= 0) { // new bankcard
			Utils.trace("addBankCard %s\n", this.getSettingBankCard());
			Utils.trace("addBankCard %s\n", this.getSettingBankCard());
			this.getSettingBankCard().setCostumer(this.getSettingCostumer());
			Utils.trace("addBankCard %s\n", this.getSettingBankCard());
			try {
				Utils.trace("addBankCard %s\n", this.getSettingBankCard());
				BankCard bankcard = (BankCard) bankCardCtrl.create(this.getSettingBankCard());
				this.getLoginBean().setPromptStatus("Add  %s", this.getSettingBankCard().getCardNumber());
				this.getSettingCostumer().addBankCard(bankcard);
			} catch (Exception e) {
				Utils.trace("catch delete %s\n", e.toString());
			}

		} else {

			try {
				Utils.trace("updateBankCard %s \n", this.getSettingBankCard());
				int nbUpdate = bankCardCtrl.update(this.getSettingBankCard());
				this.setPromptStatus("%s updated", this.getSettingBankCard().getId());
				Utils.trace("updateBankCard id %d  %d\n", this.getSettingBankCard().getId(), nbUpdate);
				Utils.trace("updateBankCard %s \n", this.getSettingBankCard());

			} catch (Exception e) {
				Utils.trace("catch delete %s\n", e.toString());
			}
		}
		Utils.trace("addBankCard %s\n", this.getSettingBankCard());
		return pageReturn;
	}

	// %%%%%%%%%%%%%%%%%%%%%%%%%%_action_%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	public String newBankCard() {
		this.resetPromptStatus();
		String pageReturn = null;
		this.setSettingBankCard(new BankCard());

		Utils.trace("updateBankCard %s \n", this.getSettingBankCard());
		return pageReturn;
	}

	// %%%%%%%%%%%%%%%%%%%%%%%%%%_action_%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	public String updateUser() {
		this.resetPromptStatus();
		String pageReturn = null;

		return pageReturn;
	}

// %%%%%%%%%%%%%%%%%%%%%%%%%%_action_%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	public String addCostumer() {
		this.resetPromptStatus();
		String pageReturn = COSTUMER_HOME;
		CostumerCtrl costumerCtrl = new CostumerCtrl();
		try {
			this.getSettingCostumer().setProfile(Profile.COSTUMER);
			Costumer newCostumer = new Costumer();
			newCostumer = (Costumer) costumerCtrl.create(this.getSettingCostumer());
			this.setSettingCostumer(new Costumer(newCostumer));
			User newUser = new User(this.getSettingCostumer().getProfile(), this.getSettingCostumer().getEmail(),
					this.getSettingCostumer().getPassword(), true);
			this.getLoginBean().setUser(newUser);
			this.getLoginBean().setIsConnected(true);

		} catch (Exception e) {
			Utils.trace("catch addUser%s\n", e.toString());
		}

		this.getSettingCostumer().clean();
		this.getLoginBean().initWelcomeMessage(this.getLoginBean().getCostumer().getProfile().getName(),
				this.getLoginBean().getCostumer().getFirstname());

		return pageReturn;

	}

	// -+-+-+-+-+-+-+-+-+-+-+-+-+_processing_-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
	public BankCard readBankCard(int id) {

		CrudCtrl bankCardCtrl = new BankCardCtrl();

		BankCard bankCard = null;

		try {
			bankCard = (BankCard) bankCardCtrl.read(id);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return bankCard;
	}

	// -------------------------------------------------------------------------------------------------
	public void cheatBankCard() {

		this.setSettingBankCard(DataTest.genBankCard(settingCostumer));

	}

	// -------------------------------------------------------------------------------------------------
	public void cheatGenUser() {

		this.setSettingCostumer(DataTest.genCostumer());

	}

	public LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}

	public BankCard getSettingBankCard() {
		return settingBankCard;
	}

	public void setSettingBankCard(BankCard settingBankCard) {
		this.settingBankCard = settingBankCard;
	}

	public Address getSettingAddress() {
		return settingAddress;
	}

	public void setSettingAddress(Address settingAddress) {
		this.settingAddress = settingAddress;
	}

	public Costumer getSettingCostumer() {
		return settingCostumer;
	}

	public void setSettingCostumer(Costumer settingCostumer) {
		this.settingCostumer = settingCostumer;
	}

	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}

	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
	}
}
