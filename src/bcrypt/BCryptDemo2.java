package bcrypt;

import org.mindrot.BCrypt;

// Adapted from Damien Miller's <djm@mindrot.org> sample code
// with additions by Ian Gallagher <igallagher@securityinnovation.com>

public class BCryptDemo2 {
	private static boolean checkPassword(String password_plaintext, String stored_hash) {
		if (stored_hash == null || !stored_hash.startsWith("$2a$"))
			throw new java.lang.IllegalArgumentException("Invalid hash provided for comparison");

		return BCrypt.checkpw(password_plaintext, stored_hash);
	}

	public static void main(String[] args) {
		String password = "badPassw0rd";

		// First time store hash+salt
		String salt = BCrypt.gensalt(12);
		System.out.println("Salt: " + salt);
		String hashed = BCrypt.hashpw(password, salt);
		System.out.println("Hash: " + hashed);

		// Validation!
		String candidate = "goodPassword";
		if (checkPassword(candidate,hashed)) {
			System.out.println("It matches");
		}
		else {
			System.out.println("It does not match");
		}
	}
}
