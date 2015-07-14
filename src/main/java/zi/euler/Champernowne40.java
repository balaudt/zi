package zi.euler;

import org.apache.commons.lang3.text.StrBuilder;

public class Champernowne40 {

	public static void main(String[] args) {
		StrBuilder builder = new StrBuilder("1");
		int cur = 1;
		while (builder.length() < 1000001) {
			builder.append(++cur);
		}
		cur = 0;
		long ans = 1;
		while (cur < 7) {
			ans *= builder.charAt(new Double(Math.pow(10, cur)).intValue() - 1) - '0';
			cur++;
		}
		System.out.println(ans);
	}
}
