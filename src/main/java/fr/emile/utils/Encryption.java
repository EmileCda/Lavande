package fr.lotus.utils;

import java.io.UnsupportedEncodingException;

import fr.lotus.common.IConstant;

public class Encryption implements IEncryption, IConstant {

// ------------------------------------------------------------------------------------------------

	public static byte[] encrypt(String StringtoEncrypte) throws UnsupportedEncodingException {

		String result = "!"+ StringtoEncrypte+ "!" ;
		return result.getBytes(CHARSET);
	}

// ------------------------------------------------------------------------------------------------
	public static String decrypt(byte[] byteArrayToDecrypt) {
		
		int arraySize = byteArrayToDecrypt.length-2;
		byte byteResult[] = new byte[arraySize ]; 
		
		for (int index = 1 ; index <byteArrayToDecrypt.length-1 ; index ++) {
			
			byteResult[index-1] =byteArrayToDecrypt[index]; 
		}
		
		return new String(byteResult);
	};
// ------------------------------------------------------------------------------------------------
}
