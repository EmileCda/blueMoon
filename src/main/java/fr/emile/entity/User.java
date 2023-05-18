package fr.emile.entity;

import javax.persistence.Table;
import javax.persistence.Transient;

import fr.emile.common.IConstant;
import fr.emile.utils.Utils;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import java.io.Serializable;

@Entity
@Table(name = "user")
public class User implements Serializable, IConstant {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int id;
	private String firstname;
	private String lastname;
	private Date birthdate;

	private String email;
	@Transient
	private String pass;
	@Lob
	private byte[] Encryptpass;
	private boolean isDeleted;

	public User() {
		this(-1, "", "", null, "", "",false);
	}

	public User(int id, String firstname, String lastname, 
			Date birthdate, String email,
			String pass, boolean isDeleted) {
		this.setId(id);
		this.setFirstname(firstname);
		this.setLastname(lastname);
		this.setBirthdate(birthdate);
		this.setEmail(email);
		this.setDeleted(isDeleted);
		this.setPass(pass);
	}


	
	public void encrypt() {

		this.setEncryptpass(Code.encrypt(this.getPass()));
	}
	
	public void decrypt() {

		this.setPass(Code.decrypt2String(this.getEncryptpass()));
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public byte[] getEncryptpass() {
		return Encryptpass;
	}

	public void setEncryptpass(byte[] encryptpass) {
		Encryptpass = encryptpass;
	}

	@Override
	public String toString() {
		return String.format("id=%d: %s %s %s %s [%s] delete : %b  ", getId(), getFirstname(), getLastname(),
				Utils.date2String(getBirthdate(), "dd/MM/yyyy"), getEmail(), 
				getPass(), isDeleted());
	}

}
