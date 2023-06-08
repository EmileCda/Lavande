package fr.lotus.utils;

import java.io.UnsupportedEncodingException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import fr.lotus.common.IConstant;
import fr.lotus.entity.Address;
import fr.lotus.entity.BankCard;
import fr.lotus.entity.Category;
import fr.lotus.entity.Comment;
import fr.lotus.entity.Costumer;
import fr.lotus.entity.Item;
import fr.lotus.entity.Order;
import fr.lotus.entity.Param;
//import fr.jasmin.entity.Address;
//import fr.jasmin.entity.BankCard;
//import fr.jasmin.entity.Comment;
//import fr.jasmin.entity.Item;
//import fr.jasmin.entity.Order;
//import fr.jasmin.entity.User;
//import fr.jasmin.entity.Category;
import fr.lotus.entity.User;
import fr.lotus.enums.Gender;
import fr.lotus.enums.Profile;
import fr.lotus.utils.Utils;

public final class DataTest implements IConstant {

	private static List<String> firstnameList;
	private static List<String> lastnameList;
	private static List<String> numberTypeList;
	private static List<String> streetNameList;
	private static List<String> cityList;
	private static List<String> streetTypeList;
	private static List<String> domainList;
	private static List<String> domainExtensionList;
	private static List<String> articleList;
	private static List<String> commentList;
	private static List<String> categoryList;
	private static List<String> itemList;
	private static List<String> itemDescriptionList;

	// ---------------------------------------------------------------------------------------------------
	public static Category genCategory() {

		return new Category(
				DataTest.categoryName(), 
				Utils.randInt(5, 90), 
				Utils.randInt(0, 1) > 0 ? true : false,
				DataTest.picUrl());

	}

	// ---------------------------------------------------------------------------------------------------
	public static Order genOrder() {

//		public Order( String orderNumber,Date deliveryDate, float totalDiscount,
//				float shippingCosts, float grandTotal) {
		
		
		return new Order(DataTest.orderNumber(),
				Utils.addDate(DATE_NOW, Utils.randInt(7, 10)),
				Utils.randFloat(0, 1000),
				Utils.randFloat(1, 100),
				Utils.randFloat(1000, 2000));

	}

	// ---------------------------------------------------------------------------------------------------
	public static Item genItem() {

		return new Item(DataTest.itemName(), DataTest.itemDescription(), Utils.randFloat(1, 100), Utils.randInt(1, 90),
				Utils.randInt(1, 100), Utils.randInt(0, 1) > 0 ? true : false, DataTest.picUrl(), DataTest.videoUrl());
	}

//	// ---------------------------------------------------------------------------------------------------
	
	
	public static Comment genComment() {
		return new Comment (DataTest.comment(), Utils.randInt(0, 5));
	}
//	public Comment( String text, int grade, Item item, Costumer costumer) {
//	this(DEFAULT_ID,text, grade, item, costumer);


//	// ---------------------------------------------------------------------------------------------------
//	public static BankCard genBankCardNoName() {
//
//		BankCard myBankCard = new BankCard();
//		int bin = Utils.randInt(111111, 999999);
//		int digit3 = Utils.randInt(0, 999);
//		int digit8 = Utils.randInt(11111111, 99999999);
//
//		myBankCard.setCardClairNumber(String.format("%06d%08d", bin, digit8));
//		Date startDate = Utils.string2Date("01/01/2023", "dd/MM/yyyy");
//		Date endDate = Utils.string2Date("01/01/2025", "dd/MM/yyyy");
//		myBankCard.setExpiryDateJava(DataTest.date(startDate, endDate));
//		myBankCard.setClairCrypto(String.format("%03d", digit3));
//
//		return myBankCard;
//	}
//
//	// ---------------------------------------------------------------------------------------------------
	public static BankCard genBankCard(Costumer costumer) {

		int nbday = Utils.randInt(1, 30) * 30;
		String crypto = String.format("%03d", Utils.randInt(0, 999));

		return new BankCard(DataTest.bankCardNumber(), Utils.addDate(DATE_NOW, nbday), crypto, costumer);
	}

//
//	// ---------------------------------------------------------------------------------------------------
	public static User genUser() {

		Date startDate = Utils.string2Date("01/01/1900", "dd/MM/yyyy");
		Date endDate = Utils.string2Date("01/01/2023", "dd/MM/yyyy");
		Gender gender = DataTest.gender();
		String firstname = DataTest.firstname(gender);
		String lastname = DataTest.lastname();

		return new User(DataTest.profile(), DataTest.email(firstname, lastname), DataTest.pass(firstname), true);
	}

//	// ---------------------------------------------------------------------------------------------------
	public static Costumer genCostumer() {

		Date startDate = Utils.string2Date("01/01/1900", "dd/MM/yyyy");
		Date endDate = Utils.string2Date("01/01/2023", "dd/MM/yyyy");
		Gender gender = DataTest.gender();
		String firstname = DataTest.firstname(gender);
		String lastname = DataTest.lastname();

		return new Costumer(gender, firstname, lastname, DataTest.birthDate(), DataTest.phone(), Profile.COSTUMER,
				DataTest.email(firstname, lastname), DataTest.pass(firstname));

	}

//	// ---------------------------------------------------------------------------------------------------
//	public static Item genItem() {
//		return new Item(DataTest.itemName(), DataTest.sentence(), Utils.randInt(1, 1000) / 10.0, Utils.randInt(0, 99),
//				Utils.randInt(0, 9999), DataTest.picUrl(), DataTest.videoUrl());
//
//	}
//
////---------------------------------------------------------------------------------------------------
	public static Address genAddress() {

		return new Address(DataTest.number(), DataTest.numberType(), DataTest.streetType(), DataTest.street(),
				DataTest.city(), DataTest.zipcode());
	}

////---------------------------------------------------------------------------------------------------
	public static Param genParam() {

		try {
			return new Param(DataTest.number(), DataTest.number(),
					"small string :" + DataTest.street() + " " + DataTest.city() + " " + DataTest.zipcode(),
					"big string :" + DataTest.street() + " " + DataTest.city() + " " + DataTest.zipcode(),
					DataTest.firstname().getBytes(CHARSET), DataTest.birthDate());
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

//	// ---------------------------------------------------------------------------------------------------
//	public static String sentence() {
//
//		int sujectType = subjectType();
//
//		return (String.format(" %s %s %s %s ", DataTest.subject(sujectType),
//				DataTest.verb(sujectType), DataTest.subject(sujectType), DataTest.complement(sujectType)
//				));
//
//	}
//
	// ---------------------------------------------------------------------------------------------------

	// ---------------------------------------------------------------------------------------------------

	// ---------------------------------------------------------------------------------------------------
	public static String crypto() {

		return String.format("0.3%S", Utils.randInt(0, 999));

	}
	// ---------------------------------------------------------------------------------------------------
	public static String orderNumber() {
		
//		JSMyyyymmdd-<1.. 1000>-FR-<1.99>
		
		return String.format("JSM-%s-%05d-FR-%02d", 
				Utils.date2String(DATE_NOW, "yyyyMMdd"),
				Utils.randInt(1, 99999),
				Utils.randInt(1, 99));
				
	
	}
	// ---------------------------------------------------------------------------------------------------
	public static String bankCardNumber() {

		int bin = Utils.randInt(111111, 999999);
		int digit8 = Utils.randInt(0, 99999999);

		return (String.format("%06d%08d", bin, digit8));

	}

	// ---------------------------------------------------------------------------------------------------

	public static Date birthDate() {

		int nbDay = Utils.randInt(1, 100 * 365);

		return Utils.addDate(DATE_NOW, -nbDay);

	}

	// ---------------------------------------------------------------------------------------------------
	public static String email(String firstname, String lastname) {

		return Utils.stripAccent(String.format("%s.%s@%s.%s", firstname.toLowerCase(), lastname.toLowerCase(),
				DataTest.domain(), DataTest.domainExtension()));

	}

	// ---------------------------------------------------------------------------------------------------
	public static String picUrl() {

		return DataTest.url() + ".jpg";
	}

	// ---------------------------------------------------------------------------------------------------
	public static String videoUrl() {

		return DataTest.url() + ".mp4";
	}

	// ---------------------------------------------------------------------------------------------------
	public static String url() {

		return String.format("http://www.%s.%s/%s", DataTest.domain(), DataTest.domainExtension(),
				DataTest.article().replace(' ', '-'));

	}

//---------------------------------------------------------------------------------------------------
	public static String phone() {

		return String.format("%05d", Utils.randInt(0, 9999)) + String.format("%05d", Utils.randInt(0, 99999));

	}

	// ---------------------------------------------------------------------------------------------------
	public static String pass(String inString) {

		char[] charTab = inString.toCharArray();
		int length = inString.length();

		for (int index = 0; index < length; index++) {
			if ((index % 2) == 0) {
				charTab[length - index - 1] = Character.toUpperCase(inString.charAt(index));
			} else
				charTab[length - index - 1] = Character.toLowerCase(inString.charAt(index));
		}

		return new String(charTab);
//		return inString;
	}

//---------------------------------------------------------------------------------------------------
	public static String firstname() {

		return DataTest.firstname(Gender.OTHER);

	}

// ---------------------------------------------------------------------------------------------------
	public static String domainExtension() {
		if (domainExtensionList == null) {
			domainExtensionList = new ArrayList<String>();

			ResourceBundle myResource = ResourceBundle.getBundle("testData"); // retreive data from Dbase.properties
			String propertieValue = myResource.getString("domain.extension");
			String propertieArray[] = propertieValue.split(";");

			for (String value : propertieArray) {

				domainExtensionList.add(value.toLowerCase());
			}
		}
		int position = Utils.randInt(0, domainExtensionList.size() - 1);

		return Utils.stripAccent(domainExtensionList.get(position));

	}

// ---------------------------------------------------------------------------------------------------
			public static String domain() {
		if (domainList == null) {
			domainList = new ArrayList<String>();

			ResourceBundle myResource = ResourceBundle.getBundle("testData"); // retreive data from Dbase.properties
			String propertieValue = myResource.getString("domain");
			String propertieArray[] = propertieValue.split(";");

			for (String value : propertieArray) {

				domainList.add(value.toLowerCase());
			}
		}
		int position = Utils.randInt(0, domainList.size() - 1);

		return Utils.stripAccent(domainList.get(position));

	}

	// ---------------------------------------------------------------------------------------------------
	public static String firstname(Gender gender) {

//		Utils.trace(gender.getValue());
		if (firstnameList == null) {
			firstnameList = new ArrayList<String>();

			ResourceBundle myResource = ResourceBundle.getBundle("testData"); // retreive data from Dbase.properties
			String propertieValue = myResource.getString("FirstName." + gender.getValue());
			String propertieArray[] = propertieValue.split(";");

			for (String value : propertieArray) {

				firstnameList.add(Utils.firstToUpper(value));
			}
		}
		int position = Utils.randInt(0, firstnameList.size() - 1);

		return firstnameList.get(position);
	}

//---------------------------------------------------------------------------------------------------
	public static String article() {
		return DataTest.itemName();
	}

//---------------------------------------------------------------------------------------------------
	public static String categoryName() {
		if (categoryList == null) {
			categoryList = new ArrayList<String>();

			ResourceBundle myResource = ResourceBundle.getBundle("testData"); // retreive data from Dbase.properties
			String propertieValue = myResource.getString("category");
			String propertieArray[] = propertieValue.split(";");

			for (String value : propertieArray) {

				categoryList.add(value);
			}
		}
		int position = Utils.randInt(0, categoryList.size() - 1);

		return categoryList.get(position);
	}

//---------------------------------------------------------------------------------------------------
	public static String lastname() {
		if (lastnameList == null) {
			lastnameList = new ArrayList<String>();

			ResourceBundle myResource = ResourceBundle.getBundle("testData"); // retreive data from Dbase.properties
			String propertieValue = myResource.getString("LastName");
			String propertieArray[] = propertieValue.split(";");

			for (String value : propertieArray) {

				lastnameList.add(Utils.firstToUpper(value));
			}
		}
		int position = Utils.randInt(0, lastnameList.size() - 1);

		return lastnameList.get(position);
	}

//---------------------------------------------------------------------------------------------------
	public static String numberType() {

//		Utils.trace("numberType");
		if (numberTypeList == null) {
			numberTypeList = new ArrayList<String>();

			ResourceBundle myResource = ResourceBundle.getBundle("testData"); // retreive data from Dbase.properties
			String propertieValue = myResource.getString("NumberType");
//			Utils.trace(propertieValue);
			String propertieArray[] = propertieValue.split(";");
			for (String value : propertieArray) {

				numberTypeList.add(value);
			}
		}
		int position = Utils.randInt(0, numberTypeList.size() - 1);
//		Utils.trace(String.format("%d %d", position,numberTypeList.size()));
//		Utils.trace(numberTypeList.get(position));

		return numberTypeList.get(position);
	}

	// ---------------------------------------------------------------------------------------------------

	public static String street() {
		if (streetNameList == null) {
			streetNameList = new ArrayList<String>();

			ResourceBundle myResource = ResourceBundle.getBundle("testData"); // retreive data from Dbase.properties
			String propertieValue = myResource.getString("StreetName");
			String propertieArray[] = propertieValue.split(";");

			for (String value : propertieArray) {

				streetNameList.add(Utils.firstToUpper(value));
			}
		}
		int position = Utils.randInt(0, streetNameList.size() - 1);

		return streetNameList.get(position);
	}

	// ---------------------------------------------------------------------------------------------------

	public static String streetType() {
		if (streetTypeList == null) {
			streetTypeList = new ArrayList<String>();

			ResourceBundle myResource = ResourceBundle.getBundle("testData"); // retreive data from Dbase.properties
			String propertieValue = myResource.getString("StreetType");
			String propertieArray[] = propertieValue.split(";");

			for (String value : propertieArray) {

				streetTypeList.add(Utils.firstToUpper(value));
			}
		}
		int position = Utils.randInt(0, streetTypeList.size() - 1);

		return streetTypeList.get(position);
	}

	// ---------------------------------------------------------------------------------------------------
	public static String itemDescription() {
		if (itemDescriptionList == null) {
			itemDescriptionList = new ArrayList<String>();
			ResourceBundle myResource = ResourceBundle.getBundle("testData"); // retreive data from Dbase.properties
			String propertieValue = myResource.getString("item.description");
			String propertieArray[] = propertieValue.split(";");

			for (String value : propertieArray) {
				itemDescriptionList.add(Utils.firstToUpper(value));
			}
		}
		int position = Utils.randInt(0, itemDescriptionList.size() - 1);

		return itemDescriptionList.get(position);
	}

	// ---------------------------------------------------------------------------------------------------
	public static String itemName() {
		if (itemList == null) {
			itemList = new ArrayList<String>();
			ResourceBundle myResource = ResourceBundle.getBundle("testData"); // retreive data from Dbase.properties
			String propertieValue = myResource.getString("item.name");
			String propertieArray[] = propertieValue.split(";");

			for (String value : propertieArray) {
				itemList.add(Utils.firstToUpper(value));
			}
		}
		int position = Utils.randInt(0, itemList.size() - 1);

		return itemList.get(position);
	}

	
	// ---------------------------------------------------------------------------------------------------
	public static String comment() {
		if (DataTest.commentList == null) 
			DataTest.commentList= initList("comment");
		int position = Utils.randInt(0, commentList.size() - 1);
		return DataTest.commentList.get(position);
	}

	
	// ---------------------------------------------------------------------------------------------------
	public static String city() {
		
		if (DataTest.cityList == null) 
			DataTest.cityList = initList("CityName");
		int position = Utils.randInt(0, cityList.size() - 1);
		return DataTest.cityList.get(position);
	}

//---------------------------------------------------------------------------------------------------

	public static String zipcode() {

		return String.format("%05d", Utils.randInt(1000, 99999));
	}

//---------------------------------------------------------------------------------------------------
	public static Integer number() {
		return Utils.randInt(1, 999);
	}

//---------------------------------------------------------------------------------------------------
	public static String numberString() {

		String numberType = DataTest.numberType();

		if (numberType.equals("")) {
			return String.format("%d", Utils.randInt(0, 999));
		}

		return String.format("%d %s", Utils.randInt(0, 999), numberType);
	}

	// ---------------------------------------------------------------------------------------------------
	public static Date date(Date lowerDate, Date upperDate) {

		Date randomDate = null;
		LocalDate localDate = null;

		LocalDate loDate = Utils.date2localDate(lowerDate);
		LocalDate upDate = Utils.date2localDate(upperDate);
		long periode = loDate.until(upDate, ChronoUnit.DAYS);
		int nbDays = Utils.randInt(0, (int) periode);
		localDate = loDate.plusDays(nbDays);

		return Utils.localDate2Date(localDate);
	}

	// ---------------------------------------------------------------------------------------------------
	public static Profile profile() {

		Profile[] profileTab = Profile.values();

		int profileSize = Profile.values().length;

		int profileRandom = Utils.randInt(0, profileSize - 2); // do not user last value : Null

		return profileTab[profileRandom];
	}

	// ---------------------------------------------------------------------------------------------------
	public static Gender gender(int id) {

		Gender[] genderTab = Gender.values();

		if ((id >= 0) && (id <= genderTab.length - 2))
			return genderTab[id];
		else
			return genderTab[genderTab.length - 2];

	}

	// ---------------------------------------------------------------------------------------------------
	public static Gender gender() {

		Gender[] genderTab = Gender.values();
		int genderRandom = Utils.randInt(0, genderTab.length - 2); // do not user last value : Null

		return DataTest.gender(genderRandom);
	}

	// ---------------------------------------------------------------------------------------------------
	public static LocalDate localDate(LocalDate lowerDate, LocalDate upperDate) {
//			Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		long periode = lowerDate.until(upperDate, ChronoUnit.DAYS);

		int nbDays = Utils.randInt(0, (int) periode);

		Utils.trace(String.format("%s %s %d %d", upperDate.toString(), lowerDate.toString(), periode, nbDays));

		return lowerDate.plusDays(nbDays);
	}

//---------------------------------------------------------------------------------------------------
	public static List<String> initList(String key){
		
		List<String> returnList = new ArrayList<String>();

		ResourceBundle myResource = ResourceBundle.getBundle("testData"); // retreive data from Dbase.properties
		String propertieValue = myResource.getString(key);
		String propertieArray[] = propertieValue.split(";");

		for (String value : propertieArray) {

			returnList.add(value) ;
		}


		return returnList ;
	}
			
// ---------------------------------------------------------------------------------------------------

}
