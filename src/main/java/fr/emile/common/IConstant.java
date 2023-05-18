package fr.emile.common;

import java.util.Date;

import fr.emile.enums.Gender;

public interface IConstant {
	
	
	public final String  DEFAULT_TEXT = "Et je m’en vais au vent mauvais Qui m’emporte Deçà, delà,Pareil à la Feuille morte." ;

	public final String  DEFAULT_BANK_CARD_NUMBER = "1000200030004000" ;
	public final String  DEFAULT_BANK_CARD_CRYPTO = "XYZ" ;
	public final String  DEFAULT_FIRSTNAME = "No-Firstname" ;
	public final String  DEFAULT_LASTNAME = "No-Lastname" ;
	public final String  DEFAULT_USER = DEFAULT_FIRSTNAME + " " + DEFAULT_LASTNAME ;
	public final Gender DEFAULT_GENDER = Gender.OTHERS ;
	public final String  DEFAULT_ADDRESS_NUMBER = "No-Number" ;
	public final String  DEFAULT_ADDRESS_STREET = "No-street" ;
	public final String  DEFAULT_ADDRESS_CITY = "No-City" ;
	public final String  DEFAULT_ADDRESS_ZIP_CODE = "AXBXC" ;

	public final boolean  DEFAULT_IS_VALIDE= true;
	public final boolean DEFAULT_IS_DELETE= false;
	public final int DEFAULT_ID = -1 ;
	public final Date NULL_DATE = null ;
	public final String CHARSET = "UTF8";
	public static final String ALGORITHM = "AES";
	public static final int FUNCTION_KEY_DB = 1705;
	public static final int KEY_LENGTH = 256;
}
