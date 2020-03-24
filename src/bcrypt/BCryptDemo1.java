package bcrypt;

import org.mindrot.BCrypt;

// Adapted from Damien Miller's <djm@mindrot.org> sample code

public class BCryptDemo1 {
	public static void main(String[] args) {
		String password = "badPassw0rd";

		// gensalt's log_rounds parameter determines the complexity
		// the work factor is 2**log_rounds, and the default is 10
		String salt = BCrypt.gensalt(12);
		System.out.println("Salt: " + salt);
		String hashed = BCrypt.hashpw(password, salt);
		System.out.println("Hash: " + hashed);

		// Check that an unencrypted password matches one that has
		// previously been hashed

		String candidate = "badPassw0rd";
		if (BCrypt.checkpw(candidate, hashed)) {
			System.out.println("It matches");
		} else {
			System.out.println("It does not match");
		}
	}
}
