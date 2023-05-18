package fr.emile.entity;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;

import fr.emile.common.IConstant;
import fr.emile.utils.Utils;

@Entity
@Table(name = "bank_card")
public class BankCard implements IConstant, Serializable {

	private static final long serialVersionUID = -8986459009062446264L;
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int id;
	@Transient
	private String cardNumber;
	@Lob
	@Column(nullable = false)
	private byte[] encryptCardNumber;
	@Column(nullable = false)
	private Date expiryDate;
	@Transient
	private String cryptogram;
	@Lob
	@Column(nullable = false)
	private byte[] encrypCryptogram;
	@Column(nullable = false)
	private String belongTo;
	private boolean isValid;
	private boolean isDeleted;
	private int userId;

	public BankCard() {

		this(DEFAULT_ID, DEFAULT_BANK_CARD_NUMBER, null, null, DEFAULT_BANK_CARD_CRYPTO, null, DEFAULT_USER, true,
				false, DEFAULT_ID);

	}

	public BankCard(int id, String cardNumber, byte[] encryptCardNumber, Date expiryDate, String cryptogram,
			byte[] encrypCryptogram, String belongTo, boolean isValid, boolean isDeleted, int userId) {
		this.id = id;
		this.cardNumber = cardNumber;
		this.encryptCardNumber = encryptCardNumber;
		this.expiryDate = expiryDate;
		this.cryptogram = cryptogram;
		this.encrypCryptogram = encrypCryptogram;
		this.belongTo = belongTo;
		this.isValid = isValid;
		this.isDeleted = isDeleted;
		this.userId = userId;
	}

	public void encrypt() {

		this.setEncryptCardNumber(Code.encrypt(this.getCardNumber()));
		this.setEncrypCryptogram(Code.encrypt(this.getCryptogram()));
//		this.setCardNumber("<"+this.getCardNumber()+">");
//		this.setCryptogram("<"+this.getCryptogram()+">");
	}

	public void decrypt() {

		this.setCardNumber(Code.decrypt2String(this.getEncryptCardNumber()));
		this.setCryptogram(Code.decrypt2String(this.getEncrypCryptogram()));

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public byte[] getEncryptCardNumber() {
		return encryptCardNumber;
	}

	public void setEncryptCardNumber(byte[] encryptCardNumber) {
		this.encryptCardNumber = encryptCardNumber;
	}

	public String  getExpiryDate() {
		return Utils.date2String(expiryDate,"MM/yy");
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getCryptogram() {
		return cryptogram;
	}

	public void setCryptogram(String cryptogram) {
		this.cryptogram = cryptogram;
	}

	public byte[] getEncrypCryptogram() {
		return encrypCryptogram;
	}

	public void setEncrypCryptogram(byte[] encrypCryptogram) {
		this.encrypCryptogram = encrypCryptogram;
	}

	public String getBelongTo() {
		return belongTo;
	}

	public void setBelongTo(String belongTo) {
		this.belongTo = belongTo;
	}

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return String.format("Id:%d, %s, [%s] exp:%s  <%s>, [%s], M. %s isValid:%b, isDeleted:%b, UserId[%d}",
				getId(), getCardNumber(), Arrays.toString(getEncryptCardNumber()), getExpiryDate(), getCryptogram(),
				Arrays.toString(getEncrypCryptogram()), getBelongTo(), isValid(), isDeleted(), getUserId());
	}

}
