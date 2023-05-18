package fr.emile.entity;

import javax.persistence.Table;
import javax.persistence.Transient;

import fr.emile.common.IConstant;
import fr.emile.enums.Gender;
import fr.emile.utils.Utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;

import java.io.Serializable;

@Entity
@Table(name = "user")
public class User implements Serializable, IConstant {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int id;
	@Transient
	private Gender gender;
	private String genderTitle;
	private String firstname;
	private String lastname;
	@Transient
	private Date birthdate;
	private java.sql.Date birthdateSql;

	private String email;
	@Transient
	private String pass;
	@Lob
	private byte[] Encryptpass;
	private boolean isDeleted;

//	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.EAGER)
	@Transient
	List<BankCard> bankCardList;

	public User() {
		this(-1, DEFAULT_GENDER, "", "", null, "", "", false);
	}

	public User(int id, Gender gender, String firstname, String lastname, Date birthdate, String email, String pass,
			boolean isDeleted) {
		this.setId(id);
		this.setGender(gender);
		this.setFirstname(firstname);
		this.setLastname(lastname);
		this.setBirthdate(birthdate);
		this.setEmail(email);
		this.setDeleted(isDeleted);
		this.setPass(pass);
		bankCardList = new ArrayList<BankCard>();
	}

//-------------------------------------------------------------------------------------------------
	public void encrypt() {

		this.setEncryptpass(Code.encrypt(this.getPass()));
		this.setBirthdateSql(Utils.toSqlDate(this.getBirthdate()));
	}

//-------------------------------------------------------------------------------------------------
	public void decrypt() {
		
		this.setBirthdate(Utils.toJavaDate(this.getBirthdateSql()));
		this.setPass(Code.decrypt2String(this.getEncryptpass()));
	}

//-------------------------------------------------------------------------------------------------
	public void delBankCard(int id) {

		if (this.getBankCardList().size() > id) {

			this.getBankCardList().remove(id);

		}

	}

//-------------------------------------------------------------------------------------------------
	public void delBankCard(BankCard bankcard) {

		this.getBankCardList().remove(bankcard);

	}

	//-------------------------------------------------------------------------------------------------
		public void addBankCard(List<BankCard> bankcardList) {
			for (BankCard bankCard : bankcardList) {
				this.addBankCard(bankCard);
			}
		}

		//-------------------------------------------------------------------------------------------------
		public void addBankCard(BankCard bankcard) {

			bankcard.setBelongTo(String.format("%s %s %s", this.getGenderTitle(), this.getFirstname(), this.getLastname()));
			bankcard.setUserId(this.getId());
			this.getBankCardList().add(bankcard);

		}

//========================= getter / setter =======================================================	
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

	public List<BankCard> getBankCardList() {
		return bankCardList;
	}

	public void setBankCardList(List<BankCard> bankCardList) {
		this.bankCardList = bankCardList;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.setGenderTitle(gender.getTitle());
		this.gender = gender;
	}

	public String getGenderTitle() {
		return genderTitle;
	}

	public void setGenderTitle(String genderTitle) {
		this.genderTitle = genderTitle;
	}

	public java.sql.Date getBirthdateSql() {
		return birthdateSql;
	}

	public void setBirthdateSql(java.sql.Date birthdateSql) {
		this.birthdateSql = birthdateSql;
	}

	@Override
	public String toString() {
		String stringReturn = "";

		stringReturn += String.format("id=%d: %s %s %s %s %s [%s] delete : %b  ", getId(), this.getGenderTitle(),
				getFirstname(), getLastname(), Utils.date2String(getBirthdate(), "dd/MM/yyyy"), getEmail(), getPass(),
				isDeleted()) +"\n" ;

		for (BankCard bankcard : this.getBankCardList()) {
			stringReturn +="\t"+ bankcard.toString() +"\n";
		}

		return stringReturn;

	}

}
