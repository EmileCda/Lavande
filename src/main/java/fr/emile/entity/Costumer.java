package fr.lotus.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import fr.lotus.common.IConstant;
import fr.lotus.enums.Gender;
import fr.lotus.enums.Profile;
import fr.lotus.utils.Encryption;
import fr.lotus.utils.Utils;

@Entity
@DiscriminatorValue("type-costumer")
@Table(name = "costumer")
//https://en.wikibooks.org/wiki/Java_Persistence/Inheritance#Example_single_table_inheritance_table_in_database
public class Costumer extends User implements IConstant, Serializable {

	private static final long serialVersionUID = 1L;

	private Gender gender;
	private String firstname;
	private String lastname;
	private Date birthdate;
	@Column(name = "phone_number")
	private String phoneNumber;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "costumer", fetch = FetchType.LAZY)
	private List<Address> addressList;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "costumer", fetch = FetchType.LAZY)
	private List<BankCard> bankCardList;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "costumer", fetch = FetchType.LAZY)
//	@Transient
	private List<CartItem> cartItemList; // meaning cart : item + quan
	
	@OneToMany(cascade = CascadeType.DETACH, mappedBy = "costumer", fetch = FetchType.LAZY)
	@Transient
	private List<Order> orderList;
	
	
	@OneToMany(cascade = CascadeType.DETACH, mappedBy = "costumer", fetch = FetchType.LAZY)
//	@Transient
	private List<Comment> commentList;

	public Costumer() {
		this(DEFAULT_ID, DEFAULT_GENDER, DEFAULT_FIRSTNAME, DEFAULT_LASTNAME, DATE_NOW, DEFAULT_PHONE, DEFAULT_PROFILE,
				DEFAULT_EMAIL, DEFAULT_PASSWORD, true);

	}

	public Costumer(Gender gender, String firstname, String lastname, Date birthdate, String phoneNumber,
			Profile profile, String email, String password) {

		this(DEFAULT_ID, gender, firstname, lastname, birthdate, phoneNumber, profile, email, password, true);

	}

	public Costumer(int id, Gender gender, String firstname, String lastname, Date birthdate, String phoneNumber,
			Profile profile, String email, String password, Boolean isActif) {

		this.setId(id);
		this.setProfile(profile);
		this.setEmail(email);
		this.setPassword(password);
		this.setIsActif(isActif);
		this.setGender(gender);
		this.setFirstname(firstname);
		this.setLastname(lastname);
		this.setBirthdate(birthdate);
		this.setPhoneNumber(phoneNumber);

		initAddressList();
		initOrderList();
		initBankCardList();
		initCommentList();
		initCartItemList();

	}

	public void addAddress(Address address) {
		initAddressList();
		this.getAddressList().add(address);
	}

	public void addBankCard(BankCard bankCard) {
		initBankCardList();
		this.getBankCardList().add(bankCard);

	}

	public void addOrder(Order order) {
		initOrderList();
		this.getOrderList().add(order);

	}

	public void addComment(Comment comment) {
		initCommentList();
		this.getCommentList().add(comment);

	}

	public void addCartItem(CartItem cartItem) {
		initCartItemList();
		this.getCartItemList().add(cartItem);

	}

	public void initBankCardList() {
		if (this.getBankCardList() == null) 
			this.setBankCardList(new ArrayList<BankCard>());

	}

	public void initAddressList() {
		if (this.getAddressList() == null) 
			this.setAddressList(new ArrayList<Address>());

	}

	public void initOrderList() {
		if (this.getOrderList() == null) 
			this.setOrderList(new ArrayList<Order>());
		

	}

	public void initCommentList() {

		if (this.getCommentList() == null)
			this.setCommentList(new ArrayList<Comment>());
	}

	public void initCartItemList() {
		if (this.getCartItemList() == null) 
			this.setCartItemList(new ArrayList<CartItem>());
		

	}

	public void preWrite() {
		super.preWrite();
		for (Address address : this.getAddressList()) {
			address.preWrite();
		}

		for (BankCard bankCard : this.getBankCardList()) {
			bankCard.preWrite();
		}

	}

	public void postRead() {
		super.postRead();

		for (BankCard bankCard : this.getBankCardList()) {
			bankCard.postRead();
		}

	}

	public Gender getGender() {
		return this.gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public List<Address> getAddressList() {
		return addressList;
	}

	public void setAddressList(List<Address> addressList) {
		this.addressList = addressList;
	}

	public List<BankCard> getBankCardList() {
		return bankCardList;
	}

	public void setBankCardList(List<BankCard> bankCardList) {
		this.bankCardList = bankCardList;
	}

	public List<CartItem> getCartItemList() {
		return cartItemList;
	}

	public void setCartItemList(List<CartItem> cartItemList) {
		this.cartItemList = cartItemList;
	}

	public List<Order> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<Order> orderList) {
		this.orderList = orderList;
	}

	public List<Comment> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}

	@Override
	public String toString() {

		String stringReturn = "";
		stringReturn += super.toString() + "\n";
		stringReturn += String.format("%s%s %s,né%s le:%s  tel:%s\n",
				getGender().getId() == 0 ? "" : getGender().getTitle() + " ", getFirstname(), getLastname(),
				getGender().getId() == 2 ? "e" : "", Utils.date2String(getBirthdate()), getPhoneNumber());

		stringReturn += "Address" + "\n";

		for (Address address : this.getAddressList()) {

			stringReturn += "\t" + address.toString() + "\n";

		}
		stringReturn += "BankCard" + "\n";
		for (BankCard bankcard : this.getBankCardList()) {
			stringReturn += "\t" + bankcard.toString() + "\n";

		}

		return stringReturn;
	}

}
