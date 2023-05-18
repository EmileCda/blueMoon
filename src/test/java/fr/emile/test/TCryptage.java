package fr.emile.test;

import fr.emile.common.IConstant;
import fr.emile.entity.Code;
import fr.emile.utils.Utils;


//*************** test result *********************************************************************
// check if encrypt and decrypt ok
//check if write to db ok
//check if read to db ok
//check if read to db once by session :ok
//*************** test result *********************************************************************



public class TCryptage implements IConstant{

	public static void main(String[] args) {
		
		
		
		byte[] byteEncode = Code.encrypt(DEFAULT_TEXT);
		
		
		String stringResult = Code.decrypt2String(byteEncode);
		String stringResult2 = Code.decrypt2String(byteEncode);
		String stringResult3 = Code.decrypt2String(byteEncode);
		
		
		Utils.trace(DEFAULT_TEXT);
		Utils.trace(stringResult);
		Utils.trace(stringResult2);
		Utils.trace(stringResult3);
		
		
		
		
	}
	
}
