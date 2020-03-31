package zi.leet.mock.microsoft;

import java.util.ArrayList;
import java.util.List;

/**
 * @author balamurugan
 */
public class Multiply {
	public String multiply(String num1, String num2) {
		List<String> additions = new ArrayList<>();
		char[] n1 = num1.toCharArray();
		char[] n2 = num2.toCharArray();
		int maxLen = Integer.MIN_VALUE;
		for (int i = n1.length - 1; i >= 0; i--) {
			int m1 = n1[i] - '0', carry = 0;
			StringBuilder b = new StringBuilder();
			for (int j = 0; j < n1.length - 1 - i; j++) b.append('0');
			for (int j = n2.length - 1; j >= 0; j--) {
				int prod = m1 * (n2[j] - '0') + carry;
				b.append((char) ((prod % 10) + '0'));
				carry = prod / 10;
			}
			if (carry > 0) {
				char[] c = (carry + "").toCharArray();
				for (int j = c.length - 1; j >= 0; j--) b.append(c[j]);
			}
			String product = b.toString();
			additions.add(product);
			maxLen = Math.max(maxLen, product.length());
		}
		StringBuilder b = new StringBuilder();
		int carry = 0;
		for (int i = 0; i < maxLen; i++) {
			int sum = carry;
			for (int j = 0; j < additions.size(); j++) {
				if (additions.get(j).length() > i)
					sum += additions.get(j).charAt(i) - '0';
			}
			b.append(sum % 10);
			carry = sum / 10;
		}
		b.reverse();
		if (carry > 0) b.insert(0, carry);
		while (b.length() > 0 && b.charAt(0) == '0') b.deleteCharAt(0);
		return b.length() > 0 ? b.toString() : "0";
	}
}
