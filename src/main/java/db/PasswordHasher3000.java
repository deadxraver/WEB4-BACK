package db;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class PasswordHasher3000 {

	public static String hash(String login, String password) {
		String salt = saltFromLogin(login);
		System.out.println("salt = " + salt);
		return new String(BCrypt.with(BCrypt.Version.VERSION_2Y).hash(BCrypt.MIN_COST, salt.getBytes(), password.getBytes()));
	}

	public static String saltFromLogin(String login) {
		StringBuilder sb = new StringBuilder();
		char[] chars = login.toCharArray();
		for (int i = 0; i < BCrypt.SALT_LENGTH; i++) {
			sb.append(chars[i % chars.length]);
		}
		return sb.toString();
	}

}
