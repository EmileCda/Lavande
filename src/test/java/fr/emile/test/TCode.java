package fr.emile.test;

import java.io.UnsupportedEncodingException;

import fr.emile.utils.Utils;

public class TCode {

	public static void main(String[] args) {
		Utils.trace("*************************** Begin ************************************\n");
		encode();
		decode();

		Utils.trace("*************************** end ************************************\n");

	}

	// -------------------------------------------------------------------------------------------------
		public static void encode() {
			Utils.trace("=========================== read many  ===========================\n");
			String string = "string to ecrpty";
			
			byte[] value = null;
			try {
				value = Code.encrypt(string);
			} catch (UnsupportedEncodingException e) {
				Utils.trace("catch test encrypt" + e.toString());
				
			} 
			
			Utils.trace(new String (value));
			
			
			String result = Encryption.decrypt(value);
			Utils.trace(result);

		}
		// -------------------------------------------------------------------------------------------------
		public static void decode() {
			Utils.trace("=========================== read many  ===========================\n");

		}

}
