package encryption;


import java.io.UnsupportedEncodingException;

import fr.lotus.utils.Encryption;
import fr.lotus.utils.Utils;

public class TEncryptionEncryt {
	public static void main(String[] args) {
		
		Utils.trace("*************************** Begin ************************************");
		String string = "string to ecrpty";
				
		byte[] value = null;
		try {
			value = Encryption.encrypt(string);
		} catch (UnsupportedEncodingException e) {
			Utils.trace("catch test encrypt" + e.toString());
			
		} 
		
		Utils.trace(new String (value));
		
		
		String result = Encryption.decrypt(value);
		Utils.trace(result);
		
		
		
		Utils.trace("*************************** end ************************************");
		
	}
}
