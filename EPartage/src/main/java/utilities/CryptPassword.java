package utilities;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

public class CryptPassword {

	public static String getCryptString(String str) {

		Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		return encoder.encodePassword(str, null);
	}
}
