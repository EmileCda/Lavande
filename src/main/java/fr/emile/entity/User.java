package fr.lotus.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import javax.persistence.Transient;

import fr.lotus.common.IConstant;
import fr.lotus.enums.Profile;
import fr.lotus.model.implement.ClassDao;
import fr.lotus.utils.Encryption;
import fr.lotus.utils.Utils;


@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="user_type")
@DiscriminatorValue("type-user")
@Table(name="user")
public class User extends ClassDao  implements IConstant,Serializable {

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


	public void preWrite(){
		try {
			this.setPasswordEncrpted(Encryption.encrypt(this.getPassword()));
		} catch (UnsupportedEncodingException e) {
			Utils.trace("catch test encrypt" + e.toString());
		}
	}

	public void postRead(){
		
		
		this.setPassword(Encryption.decrypt(this.getPasswordEncrpted()));
		
		
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
