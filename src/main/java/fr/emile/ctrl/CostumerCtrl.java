package fr.emile.ctrl;

import fr.emile.entity.BankCard;
import fr.emile.entity.Costumer;

import fr.emile.model.CostumerDao;
import fr.emile.utils.Code;
import fr.emile.utils.Utils;

public class CostumerCtrl extends CrudCtrl implements IClassCtrl {

	public CostumerCtrl() {
		super(new Costumer());
		// TODO Auto-generated constructor stub
	}

	@Override
	public Costumer preWrite(Object object) {
		Costumer costumer = (Costumer) object;
		CrudCtrl bankCardCtrl = new BankCardCtrl(); 
		if (costumer != null) {
			costumer.setPasswordEncrpted(Code.encrypt(costumer.getPassword()));
			for (BankCard bancard : costumer.getBankCardList()) {
				
				try {
					bankCardCtrl.update(bancard);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return costumer;
	}

	@Override
	public Object postWrite(Object object) {
		// TODO Auto-generated method stub
		return object;
	}

	@Override
	public Object preRead(Object object) {
		// TODO Auto-generated method stub
		return object;
	}

	@Override
	public Costumer postRead(Object object) {
		Costumer costumer = (Costumer) object;
		CrudCtrl bankCardCtrl = new BankCardCtrl(); 
		if (costumer != null) {
			costumer.setPassword(Code.decrypt2String(costumer.getPasswordEncrpted()));
			for (BankCard bancard : costumer.getBankCardList()) {
				try {
					BankCard bankcard = (BankCard) bankCardCtrl.read(bancard.getId());
					int index = costumer.getBankCardList().indexOf(bancard);
					costumer.getBankCardList().set(index, bankcard);
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return costumer;
	}
	public Costumer  read(String email) throws Exception {
		Costumer costumer  ; 
		CostumerDao CostumerDao= new CostumerDao();
		costumer= CostumerDao.read(email);
		costumer = (Costumer) postRead(costumer);
		return costumer;
	}
	

}
