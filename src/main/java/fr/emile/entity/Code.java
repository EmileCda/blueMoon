package fr.emile.entity;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import javax.crypto.spec.SecretKeySpec;
import fr.emile.common.IConstant;
import fr.emile.model.implement.SysTableDao;
import fr.emile.model.implement.UserDao;
import fr.emile.model.interfaces.ISysTableDao;
import fr.emile.model.interfaces.IUserDao;
import fr.emile.utils.DataTest;
import fr.emile.utils.Utils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;

public class Code implements IConstant {

	private static Key key = null;

//-------------------------------------------------------------------------------------------------	
	private static Key generateKey(int keyLength,String algorythm) throws NoSuchAlgorithmException {

		KeyGenerator keyGen = KeyGenerator.getInstance(algorythm);
//			SecureRandom secRandom = new SecureRandom();// Creating a SecureRandom object
//			keyGen.init(secRandom); // other solution : Initializing the KeyGenerator witht random length  

		keyGen.init(keyLength); // Initializing the KeyGenerator
		return keyGen.generateKey(); // Creating/Generating a key

	}
	// -------------------------------------------------------------------------------------------------

	private static Key getKeyFromDB(int functionCode) {

		Key keyReturn = null;

		ISysTableDao mySysTableDao = new SysTableDao();
		try {
			SysTable systable = mySysTableDao.readByFunctionCode(functionCode);
			keyReturn = new SecretKeySpec(systable.getBlobKey(), systable.getAlgorythm());
		} catch (Exception e) {
			Utils.trace("catch getKeyFromDB(functionCode) ");
			e.printStackTrace();
		}

		return keyReturn;
	}

	// -------------------------------------------------------------------------------------------------
	private static void saveKeyToDB(Key key) {
		Utils.trace("saveKeyToDB");

		ISysTableDao mySysTableDao = new SysTableDao();
		try {
			Utils.trace("saveKeyToDB1");

			if (key != null) {
				Utils.trace("saveKeyToDB 2");
				byte[] blobKey = key.getEncoded();
				SysTable systable = new SysTable(FUNCTION_KEY_DB, blobKey, KEY_LENGTH, ALGORITHM);
				mySysTableDao.create(systable);
			}
			Utils.trace("saveKeyToDB 3");

		} catch (Exception e) {
			Utils.trace("catch getKeyFromDB(functionCode) ");
			e.printStackTrace();
		}

	}
// -------------------------------------------------------------------------------------------------

	private static void initKey() {
		Utils.trace("initKey()");
		Code.setStaticKey(Code.getKeyFromDB(FUNCTION_KEY_DB));
		if (Code.getStaticKey() == null) {
			Utils.trace("Code.setStaticKey(Code.getKeyFromDB(FUNCTION_KEY_DB))== null");
			try {
				Code.setStaticKey(generateKey(KEY_LENGTH, ALGORITHM));
				Code.saveKeyToDB(Code.getStaticKey());
			} catch (NoSuchAlgorithmException e) {
				Utils.trace("catch initKey()");
				Utils.trace(e.toString());
				;
			}
		}

	}

	// -------------------------------------------------------------------------------------------------
	private static void setStaticKey(Key key) {
		Code.key = key;
	}
	// -------------------------------------------------------------------------------------------------
	private static Key getStaticKey() {
		return Code.key ;
	}

	// -------------------------------------------------------------------------------------------------
	public static Key getKey() {
		if (Code.getStaticKey()==null) {
			Utils.trace("Code.getStaticKey()==null");
			Code.initKey();
		}
		return Code.getStaticKey();
	}
	// -------------------------------------------------------------------------------------------------
	public static byte[] encrypt(String  toEncrypt) {

		if ((toEncrypt != null) && (toEncrypt.length() >0)) {
		
		byte[] messageByte = null ;
		try {
			messageByte = toEncrypt.getBytes(CHARSET);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return encrypt(messageByte);
		}
		return null;
		
		
	}

	// -------------------------------------------------------------------------------------------------
	public static byte[] encrypt(byte[]   toEncrypt) {

		if (toEncrypt.length >0) {
		
			try {
				Cipher cipher = Cipher.getInstance(ALGORITHM);
				cipher.init(Cipher.ENCRYPT_MODE, Code.getKey());
				return cipher.doFinal(toEncrypt);
			} catch (InvalidKeyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalBlockSizeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BadPaddingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchPaddingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
		
		
	}

	// -------------------------------------------------------------------------------------------------
	public static byte[] decrypt(byte[]   toDecrypte) {
		
		Cipher cipher;
		byte[] result = null ;
		try {
			cipher = Cipher.getInstance(ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, Code.getKey());
			byte[] resultTemp= cipher.doFinal(toDecrypte);
			result = resultTemp;
			
			
		} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result ;
	}

	// -------------------------------------------------------------------------------------------------
	public static String decrypt2String(byte[]   toDecrypte) {
				
		return  new String(Code.decrypt(toDecrypte));
		
	}

	// -------------------------------------------------------------------------------------------------
}

