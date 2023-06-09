package fr.emile.ctrl;

import fr.emile.common.IConstant;
import fr.emile.entity.BankCard;
import fr.emile.utils.Utils;

public class BankCardCtrl extends CrudCtrl implements IConstant {

	public BankCardCtrl(Object currentObject) {
		super(currentObject);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Object preWrite(Object myObject) {
		BankCard myBankCard = (BankCard)myObject;  
		if (myBankCard != null) {
			myBankCard.setCardNumberEncrypted(Code.encrypt(myBankCard.getCardNumber()));
			myBankCard.setCryptoEncrypted(Code.encrypt(myBankCard.getCrypto()));
			myBankCard.setExpiryDateSql(Utils.toSqlDate(myBankCard.getExpiryDate()));
		}
		
		return myBankCard;
	}

	@Override
	protected Object postWrite(Object myObject) {
		// nothing to do 
		return myObject;
	}

	@Override
	protected Object postRead(Object myObject) {
		BankCard myBankCard = (BankCard)myObject;  
		if (myBankCard != null) {
			myBankCard.setCardNumber(Code.decrypt(myBankCard.getCardNumberEncrypted()));
			myBankCard.setCrypto(Code.decrypt(myBankCard.getCryptoEncrypted()));
			myBankCard.setExpiryDate(Utils.toJavaDate(myBankCard.getExpiryDateSql()));
		}
		return myBankCard;
	}

}
