package db;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class PasswordHasher3000 {

	public static String hash(String password) {
		return BCrypt.withDefaults().hashToString(12, password.toCharArray());
	}

	public static boolean verify(String password, String hashedPassword) {
		return BCrypt.verifyer().verify(password.toCharArray(), hashedPassword).verified;
	}
}
