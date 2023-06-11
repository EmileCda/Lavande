package fr.emile.test;

import java.io.UnsupportedEncodingException;

import fr.emile.utils.Code;
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
			value = Code.encrypt(string); 
			
			Utils.trace(new String (value));
			
			
			String result = Code.decrypt(value);
			Utils.trace(result);

		}
		// -------------------------------------------------------------------------------------------------
		public static void decode() {
			Utils.trace("=========================== read many  ===========================\n");

		}

}
