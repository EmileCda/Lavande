package fr.emile.view;

import java.util.ResourceBundle;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import fr.emile.common.IConstant;
import fr.emile.ctrl.CostumerCtrl;
import fr.emile.ctrl.UserCtrl;
import fr.emile.entity.Address;
import fr.emile.entity.BankCard;
import fr.emile.entity.Costumer;
import fr.emile.entity.User;
import fr.emile.enums.Gender;
import fr.emile.enums.Profile;
import fr.emile.utils.DataTest;
import fr.emile.utils.Utils;

@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginBean extends MasterBean implements IConstant {

	private User user;
	private Costumer settingCostumer;
	private Costumer costumer;
	private Profile currentProfile;
	private boolean isConnected;
	private String welcome;
	private Gender male;
	private Gender female;
	private boolean isAdmin;
	private boolean isStoreKeeper;
	private boolean isCostumerZ;
	private String passwordConfirmation;
	private Profile profileCostumer;
	private Profile profileAdmin;
	private Profile profileStoreKeeper;
	private String labelCart;
	private BankCard currentBankCard;
	private Address currentAddress;

	public BankCard getCurrentBankCard() {
		return currentBankCard;
	}

	public void setCurrentBankCard(BankCard currentBankCard) {
		this.currentBankCard = currentBankCard;
	}

	public Address getCurrentAddress() {
		return currentAddress;
	}

	public void setCurrentAddress(Address currentAddress) {
		this.currentAddress = currentAddress;
	}

	private ResourceBundle msg;

	public LoginBean() {
		this.setMsg(ResourceBundle.getBundle("webPage"));
		this.setUser(new User());
		this.getUser().clean();
		this.setSettingCostumer(new Costumer());
		this.getSettingCostumer().clean();
		this.setCostumer(new Costumer());
		this.getCostumer().clean();
		this.setFemale(Gender.FEMALE);
		this.setMale(Gender.MALE);
		this.setProfileAdmin(Profile.MANAGER);
		this.setProfileCostumer(Profile.COSTUMER);
		this.setProfileStoreKeeper(Profile.STORE_KEEPER);
		this.setCurrentBankCard(new BankCard());
		this.setCurrentAddress(new Address());

	}

	// %%%%%%%%%%%%%%%%%%%%%%%%%% action %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	public String onTest() {
		this.resetPromptStatus();
		String pageReturn = CREATE_BANKCARD;

		return pageReturn;
	}

	// %%%%%%%%%%%%%%%%%%%%%%%%%% action %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	public String bankCardChange(ValueChangeEvent eventCategoryList) {
		this.resetPromptStatus();
		String pageReturn = SETTING;
		int cardId = (int) eventCategoryList.getNewValue();
		Utils.trace("cardId : %d\n", cardId);
		for (BankCard bankCard : this.getCostumer().getBankCardList()) {
			Utils.trace("cardId : %d\n", bankCard.getId());
			if (bankCard.getId() == cardId) {
				Utils.trace("bankCard : %s\n", bankCard);
				this.setCurrentBankCard(bankCard);
				Utils.trace("bankCard current: %s\n", this.getCurrentBankCard());
				break;
			}
		}

		Utils.trace("bankCard current: %s\n", this.getCurrentBankCard());
		return pageReturn;
	}

	// %%%%%%%%%%%%%%%%%%%%%%%%%% action %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	public String disconnect() {
		this.resetPromptStatus();
		String pageReturn = HOME;

		this.cleanSession();
		return pageReturn;
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

		if (this.getUser().getProfile() == Profile.COSTUMER) {
			this.setSettingCostumer(this.getCostumer());
			Utils.trace("%s\n", this.getCurrentBankCard());
			if ((this.getSettingCostumer().getBankCardList().size() > 0) && (this.getCurrentBankCard().getId() <=0))
				this.setCurrentBankCard(this.getSettingCostumer().getBankCardList().get(0));

			if ((this.getSettingCostumer().getAddressList().size() > 0) && (this.getCurrentAddress().getId() <=0))
				this.setCurrentAddress(this.getSettingCostumer().getAddressList().get(0));

			this.setPromptStatus("Setting %s %s %s", this.getSettingCostumer().getProfile().getName(),
					this.getSettingCostumer().getFirstname(), this.getSettingCostumer().getLastname());

			Utils.trace("current bankcard %s\n", this.getCurrentBankCard());
			Utils.trace("current Address %s\n", this.getCurrentAddress());

		} else {
			this.getSettingCostumer().setId(this.getUser().getId());
			this.getSettingCostumer().setPassword(this.getUser().getPassword());
			this.getSettingCostumer().setProfile(this.getUser().getProfile());
			this.setPromptStatus("Setting %s %s", this.getSettingCostumer().getProfile().getName(),
					this.getSettingCostumer().getEmail());

		}

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
			this.setCostumer(new Costumer(newCostumer));
			User newUser = new User(this.getSettingCostumer().getProfile(), this.getSettingCostumer().getEmail(),
					this.getSettingCostumer().getPassword(), true);
			this.setUser(newUser);
			this.setIsConnected(true);

		} catch (Exception e) {
			Utils.trace("catch addUser%s\n", e.toString());
		}

		this.getSettingCostumer().clean();
		initWelcomeMessage(this.getCostumer().getProfile().getName(), this.getCostumer().getFirstname());

		return pageReturn;

	}

// %%%%%%%%%%%%%%%%%%%%%%%%%%_action_%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	public String checkUser() throws Exception {
		String pageReturn = HOME;
		this.resetPromptStatus();

		if (!isAccountCorrect())
			return pageReturn;

		this.setConnected(true);

		switch (this.getUser().getProfile()) {

		case MANAGER:
			pageReturn = ADMIN_HOME;
			initWelcomeMessage(this.getUser().getProfile().getName(), this.user.getEmail());

			break;
		case STORE_KEEPER:
			pageReturn = STOREKEEPER_HOME;
			initWelcomeMessage(this.getUser().getProfile().getName(), this.user.getEmail());
			break;
		default: // default value is costumer (no rights on the app)
			initCostumer();
			this.initWelcomeMessage(this.getCostumer().getProfile().getName(), this.getCostumer().getFirstname());
			pageReturn = COSTUMER_HOME;
			break;
		}
		this.getUser().setPassword("");// cleaning input

		return pageReturn;

	}

	// -------------------------------------------------------------------------------------------------
	public void cheatGenUser() {

		this.setSettingCostumer(DataTest.genCostumer());

	}

	// -------------------------------------------------------------------------------------------------
	private boolean isAccountCorrect() {

		User userRetreive = new User();

		if (getUser() == null) {
			this.setPromptStatus(this.getMsg().getString("login.erreur") + "1");
			return false;
		}

// unrecognise email 

		UserCtrl ctrl = new UserCtrl();

		try {
			userRetreive = (User) ctrl.read(getUser().getEmail());

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (userRetreive == null) {
			Utils.trace("%s\n", userRetreive);
			this.setPromptStatus(this.getMsg().getString("login.erreur") + "2");
			return false;
		}
// not same password
		String passwordInput = this.getUser().getPassword();
		if (!userRetreive.getPassword().equals(passwordInput)) {
			this.setPromptStatus(this.getMsg().getString("login.erreur") + "3");
			return false;
		}
		this.setUser(userRetreive);

		return true;
	}

//-------------------------------------------------------------------------------------------------		
	private void initCostumer() {
		CostumerCtrl costumerCtrl = new CostumerCtrl();
		Costumer costumer = null;
		try {
			costumer = (Costumer) costumerCtrl.read(this.getUser().getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			this.setCostumer(costumer);
		}
	}

	// -------------------------------------------------------------------------------------------------

	private int cleanSession() {

		this.getUser().clean();
		if (this.getUser().getProfile() == Profile.COSTUMER)
			this.getCostumer().clean();

		this.setCurrentProfile(DEFAULT_PROFILE);
		this.setIsConnected(false);
		this.setWelcome("");
		this.setIsAdmin(false);
		this.setIsCostumer(false);
		this.setIsStoreKeeper(false);
		return 0;

	}

	// -------------------------------------------------------------------------------------------------
	public Costumer readOneCostumer(int id) {
		int maxRetry = 10;
		Costumer costumer = null;
		CostumerCtrl costumerCtrl = new CostumerCtrl();

		try {
			for (int index = id; index < maxRetry + id; index++) {
				costumer = (Costumer) costumerCtrl.read(id);
				if (costumer != null)
					break;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return costumer;
	}

	// -------------------------------------------------------------------------------------------------

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Costumer getCostumer() {
		return this.costumer;
	}

	public void setCostumer(Costumer costumer) {
		this.costumer = costumer;
	}

	public boolean isConnected() {
		return isConnected;
	}

	public boolean getIsConnected() {
		return isConnected();
	}

	public void setConnected(boolean isConnected) {
		this.isConnected = isConnected;
	}

	public void setIsConnected(boolean isConnected) {
		this.setConnected(isConnected);
	}

	public Profile getCurrentProfile() {
		return currentProfile;
	}

	public void setCurrentProfile(Profile currentProfile) {
		this.currentProfile = currentProfile;
	}

	public String getWelcome() {
		return welcome;
	}

	public void setWelcome(String welcome) {
		this.welcome = welcome;
	}

	public ResourceBundle getMsg() {
		return msg;
	}

	public void setMsg(ResourceBundle msg) {
		this.msg = msg;
	}

	public boolean getIsAdmin() {
		if (this.getUser() == null)
			return false;
		return (this.getUser().getProfile() == Profile.MANAGER);
	}

	public void setIsAdmin(boolean isAdmin) {
		if (this.getUser() == null)
			this.isAdmin = false;
		else
			this.isAdmin = this.getUser().getProfile() == Profile.MANAGER;
	}

	public void setAdmin(boolean isAdmin) {
		this.setAdmin(isAdmin);

	}

	public boolean isAdmin() {
		return this.getIsAdmin();

	}

	public boolean getIsStoreKeeper() {
		if (this.getUser() == null)
			return false;
		return (this.getUser().getProfile() == Profile.STORE_KEEPER);
	}

	public void setIsStoreKeeper(boolean isStoreKeeper) {
		if (this.getUser() == null)
			this.isStoreKeeper = false;
		else
			this.isStoreKeeper = this.getUser().getProfile() == Profile.STORE_KEEPER;
	}

	public void setStoreKeeper(boolean isStoreKeeper) {
		this.setStoreKeeper(isStoreKeeper);
	}

	public boolean isStoreKeeper() {
		return this.getIsStoreKeeper();
	}

	public boolean getIsCostumer() {
		if (this.getUser() == null)
			return false;
		return (this.getUser().getProfile() == Profile.COSTUMER);
	}

	public void setIsCostumer(boolean isCostumer) {
		if (this.getUser() == null)
			this.isCostumerZ = false;
		else
			this.isCostumerZ = this.getUser().getProfile() == Profile.COSTUMER;
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

	public void initWelcomeMessage(String profile, String name) {

		this.setWelcome(String.format("%s [%s] %s", this.getMsg().getString("home.welcome"), profile, name));
	}

	public Gender getMale() {
		return male;
	}

	public void setMale(Gender male) {
		this.male = male;
	}

	public Gender getFemale() {
		return female;
	}

	public void setFemale(Gender female) {
		this.female = female;
	}

	public Profile getProfileCostumer() {
		return profileCostumer;
	}

	public void setProfileCostumer(Profile profileCostumer) {
		this.profileCostumer = profileCostumer;
	}

	public Profile getProfileAdmin() {
		return profileAdmin;
	}

	public void setProfileAdmin(Profile profileAdmin) {
		this.profileAdmin = profileAdmin;
	}

	public Profile getProfileStoreKeeper() {
		return profileStoreKeeper;
	}

	public void setProfileStoreKeeper(Profile profileStoreKeeper) {
		this.profileStoreKeeper = profileStoreKeeper;
	}

	public String getLabelCart() {
		return labelCart;
	}

	public void setLabelCart(String labelCart) {
		this.labelCart = labelCart;
	}

//	public void setCostumer(boolean isCostumer) {
//		this.setIsCostumer(isCostumer);
//	}

}
