package fr.lotus.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import fr.lotus.enums.Gender;
import fr.lotus.model.implement.ClassDao;
import fr.lotus.utils.Encryption;
import fr.lotus.utils.Utils;
import fr.lotus.common.IConstant;

@Entity
@Table(name = "bank_card")
public class BankCard extends ClassDao implements IConstant, Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "owner_gender")
	private Gender ownerGender;
	@Column(name = "owner_firstname")
	private String ownerFirstname;
	@Column(name = "owner_lastname")
	private String ownerLastname;
	@Column(name = "card_number_encrypted")
	private byte[] cardNumberEncrypted; //  encrypted card number
	@Transient
	private String cardNumber; // not encrypted card number
	@Column(name = "expiry_date_SQL")
	private java.sql.Date expiryDateSql; // java date
	@Transient
	private Date expiryDate; // java date

	@Column(name = "crypto_encrypted")
	private byte[] cryptoEncrypted; //  encrypted cryptoGrame
	@Transient
	private String crypto; // not encrypted
	@Column(name = "is_valid")
	private boolean isValid;
	@Column(name = "is_deleted")
	private boolean isDeleted;

	@ManyToOne
	@JoinColumn(name = "costumer_id", nullable = false)
	private Costumer costumer;

	public BankCard() {
		this(DEFAULT_ID, DEFAULT_BANK_CARD_NUMBER, DATE_NOW, DEFAULT_BANK_CARD_CRYPTO, true, false, null);
	}

	public BankCard(String cardNumber, Date expiryDate, String crypto, Costumer costumer) {

		this(DEFAULT_ID, cardNumber, expiryDate, crypto, true, false, costumer);

	}

	public BankCard(int id, String cardNumber, Date expiryDate, String crypto, boolean isValid, boolean isDeleted,
			Costumer costumer) {
		this.setId(id);
		this.setCardNumber(cardNumber);
		this.setExpiryDate(expiryDate);
		this.setCrypto(crypto);
		this.setIsValid(isValid);
		this.setIsDeleted(isDeleted);
		this.setCostumer(costumer);

		if (this.getCostumer() != null) {
			this.setOwnerGender(this.getCostumer().getGender());
			this.setOwnerFirstname(this.getCostumer().getFirstname());
			this.setOwnerLastname(this.getCostumer().getLastname());

		}
	}

	public void preWrite(){
		try {
			this.setCardNumberEncrypted(Encryption.encrypt(this.getCardNumber()));
			this.setCryptoEncrypted(Encryption.encrypt(this.getCrypto()));
		} catch (UnsupportedEncodingException e) {
			Utils.trace("catch test encrypt" + e.toString());
		}
		
		
		this.setExpiryDateSql( Utils.toSqlDate(this.getExpiryDate()));
	}

	public void postRead(){
		
		
		this.setCardNumber(Encryption.decrypt(this.getCardNumberEncrypted()));
		this.setCrypto(Encryption.decrypt(this.getCryptoEncrypted()));
		this.setExpiryDate( Utils.toSqlDate(this.getExpiryDateSql()));
		
		
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Gender getOwnerGender() {
		return ownerGender;
	}

	public void setOwnerGender(Gender ownerGender) {
		this.ownerGender = ownerGender;
	}

	public String getOwnerFirstname() {
		return ownerFirstname;
	}

	public void setOwnerFirstname(String ownerFirstname) {
		this.ownerFirstname = ownerFirstname;
	}

	public String getOwnerLastname() {
		return ownerLastname;
	}

	public void setOwnerLastname(String ownerLastname) {
		this.ownerLastname = ownerLastname;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getCrypto() {
		return crypto;
	}

	public void setCrypto(String crypto) {
		this.crypto = crypto;
	}

	public boolean getIsValid() {
		return isValid();
	}

	public boolean isValid() {
		return isValid;
	}

	public void setIsValid(boolean isValid) {
		this.setValid(isValid);
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	public boolean getIsDeleted() {
		return this.isDeleted();
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(boolean isDeleted) {
		this.setDeleted(isDeleted);
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Costumer getCostumer() {
		return costumer;
	}

	public void setCostumer(Costumer costumer) {
		this.costumer = costumer;
	}

	public byte[] getCardNumberEncrypted() {
		return cardNumberEncrypted;
	}

	public void setCardNumberEncrypted(byte[] cardNumberEncrypted) {
		this.cardNumberEncrypted = cardNumberEncrypted;
	}

	public java.sql.Date getExpiryDateSql() {
		return expiryDateSql;
	}

	public void setExpiryDateSql(java.sql.Date expiryDateSql) {
		this.expiryDateSql = expiryDateSql;
	}

	public byte[] getCryptoEncrypted() {
		return cryptoEncrypted;
	}

	public void setCryptoEncrypted(byte[] cryptoEncrypted) {
		this.cryptoEncrypted = cryptoEncrypted;
	}

	@Override
	public String toString() {
		return String.format(
				"Id[%d] %s%s %s %s exp:%s, {%s}  %s %s",
				getId(), getOwnerGender().getId()>2?"":getOwnerGender().getTitle()+" ",
				getOwnerFirstname(), getOwnerLastname(), 
				getCardNumber(), 
				Utils.date2String(getExpiryDate(),"MM/yy"),
				getCrypto(), (isValid()?"":"non-")+"valide", (isDeleted()?"":"non-")+"effacée");
	}

}
