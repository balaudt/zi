package zi.leet.medium;

/**
 * @author balamurugan
 */
public class DecodeString {
	public String decodeString(String s) {
		char[] in = s.toCharArray();
		StringBuilder out = new StringBuilder();
		for (int i = 0; i < in.length; i++) {
			if (Character.isLetter(in[i])) {
				out.append(in[i]);
				continue;
			}
			int count = 0;
			while (Character.isDigit(in[i])) count = count * 10 + in[i++] - '0';
			i++;
			int st = i;
			while (in[i] != ']') i++;
			for (int j = 0; j < count; j++)
				for (int k = st; k < i; k++)
					out.append(in[k]);
		}
		return out.toString();
	}

}
