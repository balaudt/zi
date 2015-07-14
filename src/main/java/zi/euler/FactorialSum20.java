package zi.euler;

import org.apfloat.Apint;

public class FactorialSum20 {

	public static void main(String[] args) {
		Apint sum = new Apint(1);
		for (int i = 2; i <= 100; i++) {
			sum = sum.multiply(new Apint(i));
		}
		char[] cs = sum.toString().toCharArray();
		int ans = 0;
		for (int i = 0; i < cs.length; i++) {
			ans += cs[i] - '0';
		}
		System.out.println(ans);
	}
}
