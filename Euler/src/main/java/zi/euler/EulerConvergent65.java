package zi.euler;

import org.apfloat.Apint;

public class EulerConvergent65 {

	public static void longVersion(String[] args) {
		long a = 33;
		long n = 2 * a + 2;
		long d = 2 * a + 1;
		long t;
		for (long x = a - 1; x >= 1; x--) {
			System.out.println(n + "\t" + d);
			t = d;
			d = 2 * x * (n + d) + n;
			n = 2 * x * (n + t) + 2 * n + t;
		}
		t = d;
		d = n;
		n = 2 * n + t;
		System.out.println(n + "\t" + d);
	}

	public static void main(String[] args) {
		int a = 33;
		Apint n = new Apint(2 * a + 2);
		Apint d = new Apint(2 * a + 1);
		Apint two = new Apint(2);
		Apint t;
		for (int x = a - 1; x >= 1; x--) {
			t = new Apint(d.toString());
			d = two.multiply(new Apint(x)).multiply(n.add(d)).add(n);
			n = d.add(n).add(t);
		}
		t = new Apint(d.toString());
		d = n;
		n = two.multiply(n).add(t);
//		System.out.println(n + "\t" + d);
		char[] num = n.toString().toCharArray();
		int sum = 0;
		for (char c : num) {
			sum += c - '0';
		}
		System.out.println(sum);
	}

}
