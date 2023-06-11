package fr.emile.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Transient;

import fr.emile.common.IConstant;
import fr.emile.enums.Profile;



@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="user_type")
@DiscriminatorValue("type-user")
@Table(name="user")
public class User implements IConstant,Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private Profile profile;
	@Column(unique = true, length = 100, nullable = false)
	private String email;
	@Column(name = "password_encrpted",nullable = false)
	private byte[] passwordEncrpted;
	@Transient
	private String password;
	@Column(name = "is_actif",nullable = false)
	private Boolean isActif;

	
	public User() {
		this(DEFAULT_ID,
				DEFAULT_PROFILE,
				DEFAULT_EMAIL,
				DEFAULT_PASSWORD,true
				);
		
	}
	public User( User copy) {
		
		this(copy.getId(), copy.getProfile(), copy.getEmail(),copy.getPassword(), copy.getIsActif());
		
		
	}
	public User( Profile profile, String email, String password, Boolean isActif) {
		this(DEFAULT_ID, profile, email,password, isActif);
	}

	public User(int id, Profile profile, String email, String password, Boolean isActif) {
		this.setId ( id);
		this.setProfile ( profile);
		this.setEmail ( email);
		this.setPassword ( password);
		this.setIsActif ( isActif);
	}

	
	public void clean(){
		this.setId ( DEFAULT_ID);
		this.setProfile ( DEFAULT_PROFILE);
		this.setEmail ("");
		this.setPassword ("");
		this.setIsActif (false);
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public byte[] getPasswordEncrpted() {
		return passwordEncrpted;
	}
	public void setPasswordEncrpted(byte[] passwordEncrpted) {
		this.passwordEncrpted = passwordEncrpted;
	}
	public Boolean getIsActif() {
		return isActif;
	}

	public void setIsActif(Boolean isActif) {
		this.isActif = isActif;
	}

	@Override
	public String toString() {
		return String.format("Id[%d], %s, %s, %s, {%s}",
				getId(), getProfile(), getEmail(), getPassword(), (getIsActif()?"" :"non-") + "actif");
	}
	
	
}
