package fr.emile.view;

import java.util.ResourceBundle;

import javax.faces.bean.ManagedBean;
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
import fr.emile.enums.Gender;
import fr.emile.enums.Profile;
import fr.emile.utils.DataTest;
import fr.emile.utils.Utils;

@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginBean extends MasterBean implements IConstant {

	private User user;
	private Costumer costumer;
	private Profile currentProfile;
	private boolean isConnected;
	private String welcome;
	private Gender male;
	private Gender female;

	private Profile profileCostumer;
	private Profile profileAdmin;
	private Profile profileStoreKeeper;
	private String labelCart;


	private ResourceBundle msg;

	public LoginBean() {
		this.setMsg(ResourceBundle.getBundle("webPage"));
		this.setUser(new User());
		this.getUser().clean();
		this.setCostumer(new Costumer());
		this.getCostumer().clean();
		this.setFemale(Gender.FEMALE);
		this.setMale(Gender.MALE);
		this.setProfileAdmin(Profile.MANAGER);
		this.setProfileCostumer(Profile.COSTUMER);
		this.setProfileStoreKeeper(Profile.STORE_KEEPER);

	}

	// %%%%%%%%%%%%%%%%%%%%%%%%%% action %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	public String onTest() {
		this.resetPromptStatus();
		String pageReturn = CREATE_BANKCARD;

		return pageReturn;
	}


	// %%%%%%%%%%%%%%%%%%%%%%%%%% action %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	public String disconnect() {
		this.resetPromptStatus();
		String pageReturn = HOME;

		this.cleanSession();
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

	// -+-+-+-+-+-+-+-+-+-+-+-+-+_processing_-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
	public BankCard readBankCard(int id) {
		
		CrudCtrl bankCardCtrl = new BankCardCtrl();
		
		BankCard bankCard = null ; 

		try {
			bankCard = (BankCard) bankCardCtrl.read(id);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return bankCard;
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
		return this.profileStoreKeeper;
	}

	public void setProfileStoreKeeper(Profile profileStoreKeeper) {
		this.profileStoreKeeper = profileStoreKeeper;
	}

	public String getLabelCart() {
		return this.labelCart;
	}

	public void setLabelCart(String labelCart) {
		this.labelCart = labelCart;
	}



	
	
}
