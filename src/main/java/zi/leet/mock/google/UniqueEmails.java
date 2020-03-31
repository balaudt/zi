package zi.leet.mock.google;

import java.util.HashSet;
import java.util.Set;

/**
 * @author balamurugan
 */
//https://leetcode.com/problems/unique-email-addresses/
public class UniqueEmails {
	public int numUniqueEmails(String[] emails) {
		Set<String> uniqueEmails = new HashSet<>();
		for (String email : emails) {
			int atInd = email.indexOf('@');
			char[] c = email.substring(0, atInd).toCharArray();
			StringBuilder actualEmail = new StringBuilder();
			for (int i = 0; i < c.length; i++) {
				if (c[i] == '+') break;
				if (c[i] == '.') continue;
				actualEmail.append(c[i]);
			}
			actualEmail.append(email.substring(atInd));
			uniqueEmails.add(actualEmail.toString());
		}
		return uniqueEmails.size();
	}
}
