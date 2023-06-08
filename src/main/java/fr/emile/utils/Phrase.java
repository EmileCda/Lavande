package fr.lotus.utils;

import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import fr.lotus.common.IConstant;
//import fr.jasmin.entity.Address;
//import fr.jasmin.entity.BankCard;
//import fr.jasmin.entity.Comment;
//import fr.jasmin.entity.Item;
//import fr.jasmin.entity.Order;
//import fr.jasmin.entity.User;
//import fr.jasmin.entity.Category;

import fr.lotus.enums.Gender;
import fr.lotus.enums.Profile;
import fr.lotus.utils.Utils;

public final class Phrase implements IConstant {

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

	private static List<String> sentenseSubjectListOneMale;
	private static List<String> verbListOneMale;
	private static List<String> sentenseAdverbListOneMale;
	private static List<String> sentenseObjectListOneMale;
	private static List<String> sentenseAdjectiveListOneMale;

	private static List<String> sentenseSubjectListOneFemale;
	private static List<String> verbListOneFemale;
	private static List<String> sentenseAdverbListOneFemale;
	private static List<String> sentenseObjectListOneFemale;
	private static List<String> sentenseAdjectiveListOneFemale;

	private static List<String> sentenseSubjectListManyMale;
	private static List<String> verbListManyMale;
	private static List<String> sentenseAdverbListManyMale;
	private static List<String> sentenseObjectListManyMale;
	private static List<String> sentenseAdjectiveListManyMale;

	private static List<String> sentenseSubjectListManyFemale;
	private static List<String> verbListManyFemale;
	private static List<String> sentenseAdverbListManyFemale;
	private static List<String> sentenseObjectListManyFemale;
	private static List<String> sentenseAdjectiveListManyFemale;

	// ---------------------------------------------------------------------------------------------------
//	public static Category genCategory() {
//
//		return new Category(DataTest.categoryName(), Utils.randInt(5, 90), Utils.randInt(0, 1) > 0 ? true : false,
//				DataTest.picUrl());
//
//	}
//
//	// ---------------------------------------------------------------------------------------------------
//	public static String genOrderNumber(int id) {
//		
//		
//		return String.format("DT%s-ID%d-RN%06d-FR",Utils.date2String(DATE_NOW, "yyyyMMdd"),id,Utils.randInt(0, 999999) );
//		
//		
//	}
//
//	// ---------------------------------------------------------------------------------------------------
//	public static Comment genComment() {
//		return new Comment (DataTest.sentence(), Utils.randInt(0, 5));
//	}
//
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
//	public static BankCard genBankCard() {
//
//		BankCard myBankCard = new BankCard();
//
//		myBankCard = DataTest.genBankCardNoName();
//
//		Gender gender = DataTest.gender();
//		String firstname1 = DataTest.firstname(gender);
//		String lastname = DataTest.lastname();
//
//		myBankCard.setOwnerGender(gender);
//		myBankCard.setOwnerFirstname(firstname1);
//		myBankCard.setOwnerLastname(lastname);
//
//		return myBankCard;
//	}
//
//	// ---------------------------------------------------------------------------------------------------
//	public static User genUser() {
//
//		Date startDate = Utils.string2Date("01/01/1900", "dd/MM/yyyy");
//		Date endDate = Utils.string2Date("01/01/2023", "dd/MM/yyyy");
//		Gender gender = DataTest.gender();
//		String firstname = DataTest.firstname(gender);
//		String lastname = DataTest.lastname();
//
//		return new User(gender, firstname, lastname, DataTest.date(startDate, endDate), DataTest.profile(),
//				DataTest.email(firstname, lastname), DataTest.pass(firstname), DataTest.phone());
//
//	}
//
//	// ---------------------------------------------------------------------------------------------------
//	public static Item genItem() {
//		return new Item(DataTest.itemName(), DataTest.sentence(), Utils.randInt(1, 1000) / 10.0, Utils.randInt(0, 99),
//				Utils.randInt(0, 9999), DataTest.picUrl(), DataTest.videoUrl());
//
//	}
//
////---------------------------------------------------------------------------------------------------
//	public static Address genAddress() {
//
//		return new Address(DataTest.number(), DataTest.numberType(), DataTest.streetType(), DataTest.street(),
//				DataTest.city(), DataTest.zipcode());
//	}
//
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

	public static int subjectType() {
		return Utils.randInt(0, 3);

	}

	// ---------------------------------------------------------------------------------------------------
	private static List<String> subjectOneMaleList;
	private static List<String> subjectOneFemalebList;
	private static List<String> subjectManyMaleList;
	private static List<String> subjectManyFemaleList;

	public static String subject(int sentenseType) {

		switch (sentenseType) {

		case 1: return Phrase.subjectOneFemale();
		case 2: return Phrase.subjectManyMale();
		case 3: return Phrase.subjectManyFemale();
		default: return Phrase.subjectOneMale();

		}

	}

	// ---------------------------------------------------------------------------------------------------
	public static String subjectOneMale() {
		Phrase.subjectOneMaleList = InitList(Phrase.subjectOneMaleList, "sentense.subject.one.male");
		int position = Utils.randInt(0, Phrase.subjectOneMaleList.size() - 1);
		return Phrase.subjectOneMaleList.get(position);

	}
	// ---------------------------------------------------------------------------------------------------
	public static String subjectOneFemale() {
		Phrase.subjectOneFemalebList = InitList(Phrase.subjectOneFemalebList, "sentense.subject.one.female");
		int position = Utils.randInt(0, Phrase.subjectOneFemalebList.size() - 1);
		return Phrase.subjectOneFemalebList.get(position);

	}
	// ---------------------------------------------------------------------------------------------------
	public static String subjectManyMale() {
		Phrase.subjectManyMaleList = InitList(Phrase.subjectManyMaleList, "sentense.subject.many.male");
		int position = Utils.randInt(0, Phrase.subjectManyMaleList.size() - 1);
		return Phrase.subjectManyMaleList.get(position);

	}
	// ---------------------------------------------------------------------------------------------------
	public static String subjectManyFemale() {
		Phrase.subjectManyFemaleList = InitList(Phrase.subjectManyFemaleList, "sentense.subject.many.female");
		int position = Utils.randInt(0, Phrase.subjectManyFemaleList.size() - 1);
		return Phrase.subjectManyFemaleList.get(position);

	}
	
	
	private static List<String> verbOneMaleList;
	private static List<String> verbOneFemalebList;
	private static List<String> verbManyMaleList;
	private static List<String> verbManyFemaleList;

	public static String verb(int sentenseType) {

		switch (sentenseType) {

		case 1: return Phrase.verbOneFemale();
		case 2: return Phrase.verbManyMale();
		case 3: return Phrase.verbManyFemale();
		default: return Phrase.verbOneMale();

		}

	}

	// ---------------------------------------------------------------------------------------------------
	public static String verbOneMale() {
		Phrase.verbOneMaleList = InitList(Phrase.verbOneMaleList, "sentense.verb.one.male");
		int position = Utils.randInt(0, Phrase.verbOneMaleList.size() - 1);
		return Phrase.verbOneMaleList.get(position);

	}
	// ---------------------------------------------------------------------------------------------------
	public static String verbOneFemale() {
		Phrase.verbOneFemalebList = InitList(Phrase.verbOneFemalebList, "sentense.verb.one.female");
		int position = Utils.randInt(0, Phrase.verbOneFemalebList.size() - 1);
		return Phrase.verbOneFemalebList.get(position);

	}
	// ---------------------------------------------------------------------------------------------------
	public static String verbManyMale() {
		Phrase.verbManyMaleList = InitList(Phrase.verbManyMaleList, "sentense.verb.many.male");
		int position = Utils.randInt(0, Phrase.verbManyMaleList.size() - 1);
		return Phrase.verbManyMaleList.get(position);

	}
	// ---------------------------------------------------------------------------------------------------
	public static String verbManyFemale() {
		Phrase.verbManyFemaleList = InitList(Phrase.verbManyFemaleList, "sentense.verb.many.female");
		int position = Utils.randInt(0, Phrase.verbManyFemaleList.size() - 1);
		return Phrase.verbManyFemaleList.get(position);

	}

	
	// ---------------------------------------------------------------------------------------------------
	

	private static List<String> complementOneMaleList;
	private static List<String> complementOneFemalebList;
	private static List<String> complementManyMaleList;
	private static List<String> complementManyFemaleList;

	public static String complement(int sentenseType) {

		switch (sentenseType) {

		case 1: return Phrase.complementOneFemale();
		case 2: return Phrase.complementManyMale();
		case 3: return Phrase.complementManyFemale();
		default: return Phrase.complementOneMale();

		}

	}

	// ---------------------------------------------------------------------------------------------------
	public static String complementOneMale() {
		Phrase.complementOneMaleList = InitList(Phrase.complementOneMaleList, "sentense.complement.one.male");
		int position = Utils.randInt(0, Phrase.complementOneMaleList.size() - 1);
		return Phrase.complementOneMaleList.get(position);

	}
	// ---------------------------------------------------------------------------------------------------
	public static String complementOneFemale() {
		Phrase.complementOneFemalebList = InitList(Phrase.complementOneFemalebList, "sentense.complement.one.female");
		int position = Utils.randInt(0, Phrase.complementOneFemalebList.size() - 1);
		return Phrase.complementOneFemalebList.get(position);

	}
	// ---------------------------------------------------------------------------------------------------
	public static String complementManyMale() {
		Phrase.complementManyMaleList = InitList(Phrase.complementManyMaleList, "sentense.complement.many.male");
		int position = Utils.randInt(0, Phrase.complementManyMaleList.size() - 1);
		return Phrase.complementManyMaleList.get(position);

	}
	// ---------------------------------------------------------------------------------------------------
	public static String complementManyFemale() {
		Phrase.complementManyFemaleList = InitList(Phrase.complementManyFemaleList, "sentense.complement.many.female");
		int position = Utils.randInt(0, Phrase.complementManyFemaleList.size() - 1);
		return Phrase.complementManyFemaleList.get(position);

	}

	
	// ---------------------------------------------------------------------------------------------------

	// ---------------------------------------------------------------------------------------------------
	public static String crypto() {

	
		return String.format("0.3%S",Utils.randInt(0, 999));

	}

	
	// ---------------------------------------------------------------------------------------------------

	// ---------------------------------------------------------------------------------------------------
	public static String bankCardNumber() {

		int bin = Utils.randInt(111111, 999999);
		int digit8 = Utils.randInt(0, 99999999);

		return (String.format("%06d%08d", bin, digit8));

	}

	
	// ---------------------------------------------------------------------------------------------------

	public static Date birthDate() {
		
		int nbDay = Utils.randInt(1, 100*365);
		
		return Utils.addDate(DATE_NOW, - nbDay);

	}

	// ---------------------------------------------------------------------------------------------------
	public static String email(String firstname, String lastname) {

		return Utils.stripAccent(String.format("%s.%s@%s.%s", firstname.toLowerCase(), lastname.toLowerCase(),
				Phrase.domain(), Phrase.domainExtension()));

	}

	// ---------------------------------------------------------------------------------------------------
	public static String picUrl() {

		return Phrase.url() + ".jpg";
	}

	// ---------------------------------------------------------------------------------------------------
	public static String videoUrl() {

		return Phrase.url() + ".mp4";
	}

	// ---------------------------------------------------------------------------------------------------
	public static String url() {

		return String.format("http://www.%s.%s/%s", Phrase.domain(), Phrase.domainExtension(),
				Phrase.article().replace(' ', '-'));

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

		return Phrase.firstname(Gender.OTHER);

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
		if (articleList == null) {
			articleList = new ArrayList<String>();

			ResourceBundle myResource = ResourceBundle.getBundle("testData"); // retreive data from Dbase.properties
			String propertieValue = myResource.getString("item.name");
			String propertieArray[] = propertieValue.split(";");

			for (String value : propertieArray) {

				articleList.add(value);
			}
		}
		int position = Utils.randInt(0, articleList.size() - 1);

		return articleList.get(position);
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
	public static List<String> InitList(List<String> listName, String Key) {
		if (listName == null) {
			listName = new ArrayList<String>();
			ResourceBundle myResource = ResourceBundle.getBundle("testData"); // retreive data from Dbase.properties
			String propertieValue = myResource.getString(Key);
			String propertieArray[] = propertieValue.split(";");
			for (String value : propertieArray) {

				listName.add(value);
			}
		}
		return listName;
	}


	// ---------------------------------------------------------------------------------------------------
	public static String city() {
		if (cityList == null) {
			cityList = new ArrayList<String>();

			ResourceBundle myResource = ResourceBundle.getBundle("testData"); // retreive data from Dbase.properties
			String propertieValue = myResource.getString("CityName");
			String propertieArray[] = propertieValue.split(";");

			for (String value : propertieArray) {

				cityList.add(Utils.firstToUpper(value));
			}
		}
		int position = Utils.randInt(0, cityList.size() - 1);

		return cityList.get(position);
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

		String numberType = Phrase.numberType();

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
	public static Gender gender() {

		Gender[] genderTab = Gender.values();

		int genderSize = Gender.values().length;

		int genderRandom = Utils.randInt(0, genderSize - 2); // do not user last value : Null

		return genderTab[genderRandom];
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

}
