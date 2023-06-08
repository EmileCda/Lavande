package fr.lotus.backingbean;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import fr.lotus.common.IConstant;
import fr.lotus.entity.User;
import fr.lotus.model.implement.UserDao;
import fr.lotus.utils.DataTest;

@ManagedBean (name ="loginBean")
@SessionScoped
public class LoginBean  implements IConstant{
	
	private User user ; 
	
	public LoginBean() {
		user = new User();
		initTestUser();
	
		
	}

	
	public String checkUser() throws Exception {
				
		UserDao userDao = new UserDao();
		String emailInput = this.getUser().getEmail();

		User userRetreive = userDao.read(emailInput);
		this.setUser(userRetreive);
		
		switch (this.getUser().getProfile()) {
		case COSTUMER : 
		{this.setUser(userRetreive);
			return  CLIENT_HOME;
		}
		case MANAGER : return ADMIN_HOME; 
		case STORE_KEEPER : return  STOREKEEPER_HOME;
		default: return HOME;
		} 
	
		
	}

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}


	public void initTestUser() {
		String firstname = DataTest.firstname();
		this.getUser().setEmail(DataTest.email(firstname,DataTest.lastname()));
		this.getUser().setPassword(DataTest.pass(firstname));
	}
	

}
