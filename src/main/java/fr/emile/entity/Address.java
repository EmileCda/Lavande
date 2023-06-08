package fr.lotus.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import fr.lotus.common.IConstant;
import fr.lotus.model.implement.ClassDao;


@Entity
@Table(name = "address")
public final class Address extends ClassDao implements IConstant, Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "street_number")
	private int streetNumber;
	@Column(name = "number_type")
	private String numberType;
	private String street;
	@Column(name = "street_type")
	private String streetType;
	private String city;
	private String zipCode;
	@Column(name = "is_valide")
	private Boolean isValide;

	@ManyToOne
	@JoinColumn(name = "costumer_id", nullable = false)
	private Costumer costumer;
	
	
	public Address() {
		this(	DEFAULT_ID,
				DEFAULT_INT,
				"",
				DEFAULT_ADDRESS_STREET_TYPE,
				DEFAULT_ADDRESS_STREET,
				DEFAULT_ADDRESS_ZIP_CODE,
				DEFAULT_ADDRESS_CITY,
				true );
	}

	public Address( int streetNumber, String numberType,  String streetType,String street, String city,
			String zipCode) {

		this(	DEFAULT_ID,
				streetNumber,
				numberType,
				streetType,
				street,
				zipCode,
				city,
				true );

		
	}

	
	public Address(int id, int streetNumber, String numberType,  
			String streetType,String street, String zipCode,String city,
			 Boolean isValide) {
		this.setId(id);
		this.setStreetNumber(streetNumber);
		this.setNumberType(numberType);
		this.setStreetType(streetType);
		this.setStreet(street);
		this.setZipCode(zipCode);
		this.setCity(city);
		this.setIsValide(isValide);
	}
// ================= constructor ================================================================== 	

	public void preWrite(){
		
	}

	public void postRead(){
		
		
		
	}
// ---------------setter / getter------------------------------------------------------------------ 	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(int streetNumber) {
		this.streetNumber = streetNumber;
	}

	public String getNumberType() {
		return numberType;
	}

	public void setNumberType(String numberType) {
		this.numberType = numberType;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getStreetType() {
		return streetType;
	}

	public void setStreetType(String streetType) {
		this.streetType = streetType;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public Boolean getIsValide() {
		return isValide;
	}

	public void setIsValide(Boolean isValide) {
		this.isValide = isValide;
	}

	@Override
	public String toString() {
		return String.format(
				"Id[%d] %d%s, %s %s %s %s {%s}",
				getId(), getStreetNumber(), getNumberType().length()<=0?"":(" "+getNumberType()), 
						getStreetType(),getStreet(),  getZipCode(), getCity(),
				(getIsValide()?"":"non-")+"valide");
		        
	}

	public Costumer getCostumer() {
		return costumer;
	}

	public void setCostumer(Costumer costumer) {
		this.costumer = costumer;
	}

}
