package fr.lotus.common;

import java.util.Date;

import fr.lotus.enums.Gender;
import fr.lotus.enums.Profile;
import fr.lotus.utils.Utils;

public interface IConstant {

// common to all class 
	
	public final String DEFAULT_TEXT = "Et je m’en vais au vent mauvais Qui m’emporte Deçà, delà,Pareil à la Feuille morte.";
	public final int DEFAULT_INT= 0 ; 
	public final String DATE_FORMAT= "dd/MM/yyyy";
	
	public final Date DEFAULT_DATE = Utils.string2Date("01/01/1970", DATE_FORMAT);
	public final Date DATE_NOW = new Date();
	public final String DEFAULT_FIRSTNAME = "No-Firstname";
	public final String DEFAULT_LASTNAME = "No-Lastname";
	public final String DEFAULT_USER = DEFAULT_FIRSTNAME + " " + DEFAULT_LASTNAME;
	public final Gender DEFAULT_GENDER = Gender.OTHER;
	public final Profile DEFAULT_PROFILE = Profile.OTHER;

	public final float DEFAULT_FLOAT_VALUE=  0;
	public final boolean DEFAULT_IS_VALIDE = true;
	public final boolean DEFAULT_IS_DELETE = false;
	public final int DEFAULT_ID = -1;
	public final Date NULL_DATE = null;
	public final String CHARSET = "UTF8";

	
//---------------------------------------------------------------
	
	public final String DEFAULT_BANK_CARD_NUMBER = "1000200030004000";
	public final String DEFAULT_BANK_CARD_CRYPTO = "XYZ";
	
	
	public final Date DEFAULT_DELIVERY_DATE = Utils.addDate(DATE_NOW,5);
	
	
	//-------------- Address --------------------------------	
	
	public final String DEFAULT_ADDRESS_STREET = "No-street";
	public final String DEFAULT_ADDRESS_CITY = "No-City";
	public final String DEFAULT_ADDRESS_ZIP_CODE = "AXBXC";
	public final String DEFAULT_ADDRESS_STREET_TYPE = "voie";

//-------------- User --------------------------------	
	public final String DEFAULT_EMAIL= "no-mail@no-mail.no";
	public final String DEFAULT_PASSWORD= "xxxxxxxxxx";
	public final String DEFAULT_PHONE= "0011223344";

// ----------------------Item -----------------------------
	public final String DEFAULT_PIC= "http://default-picture.jpg";
	
//----------------- encryption-----------------------------------------------	
	public final String ALGORITHM = "AES";
	public final int FUNCTION_KEY_DB = 1705;
	public final int DEFAULT_FUNCTION_KEY = 1000;
	public final int KEY_LENGTH = 256;
	public final String DEFAULT_ALGORYTHM = "DES";
	public final int DEFAULT_FUNCTION_CODE = 0;

	public final int BC_KEY = 0;
	public final int PASS_KEY = 1;
	
	
	public final String DEFAULT_NAME = "no-name";
	public final String DEFAULT_PICTURE = "no-url.jpg";
	public final String DEFAULT_VIDEO = "no-url.mp4";
	public final String DEFAULT_DESCRIPTION = "no-description";
	public final String DEFAULT_ORDER_NUMBER = "temp-order-number-1298";
	
	public final float DEFAULT_PRICE = 999;
	public final int DEFAULT_DISCOUNT = 0;
	public final int DEFAULT_INVENTORY = 0;
	
	public final int DEFAULT_GRADE = 5;
	public final int DEFAULT_QUANTITY= 1;
	

//-------------- web page ------------------------------------------------	
	
	public final String CLIENT_HOME 	="procurement-management.xhtml";
	public final String STOREKEEPER_HOME= "item-management.xhtml";
	public final String ADMIN_HOME  	= "admin-management.xhtml";
	public final String ADD_USER  		= "add-user.xhtml";
	public final String ADMIN_MANAGEMENT= "admin-management.xhtml";
	public final String CART  			= "cart.xhtml";
	public final String CONFIRM  		= "confirm.xhtml";
	public final String CREATE_USER  	= "create-user.xhtml";
	public final String HOME  			= "home.xhtml";
	public final String ITEM  			= "item.xhtml";
	public final String ORDER_HISTORY  	= "order-history.xhtml";
	public final String POURCHASE_VALIDATED  = "purchase-validated.xhtml";

}
