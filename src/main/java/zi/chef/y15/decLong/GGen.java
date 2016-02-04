package zi.chef.y15.decLong;
import java.io.PrintStream;
import java.util.Random;

import org.apfloat.Apint;

public class GGen {
	public static void main(String[] args) throws Exception {
		System.setOut(new PrintStream("/Users/balaudt/Temp/dec/G-sin-1.in"));
		System.out.println("10");
		Random random = new Random();
		int cdigits=(int) (1e6-1e4);
		for (int k = 0; k < 10; k++) {
			char bc[] = new char[10000];
			for (int i = 0; i < 10000; i++) {
				bc[i] = (char) (random.nextInt(7) + '0');
			}
			String bstr = new String(bc);
			Apint b = new Apint(bstr, 7);
			char cc[] = new char[cdigits];
			for (int i = 0; i < cdigits; i++) {
				cc[i] = (char) (random.nextInt(7) + '0');
			}
			String cstr = new String(cc);
			Apint c = new Apint(cstr, 7);
			Apint a = b.multiply(c);
			System.out.println(a.toString());
			System.out.println(cstr);
			//			System.out.println(bstr);
			System.out.println("10000");
		}
	}

	public static void rand(String[] args) throws Exception {
		System.setOut(new PrintStream("/Users/balaudt/Temp/dec/G-gen.in"));
		System.out.println("10");
		Random random = new Random();
		int n = 20;
		int randMax = (int) Math.pow(7, n);
		for (int i = 0; i < 10000; i++) {
			int bnum = random.nextInt(randMax / 2) + (randMax / 2);
			long anum = (long) bnum * random.nextInt(randMax);
			String astr = Long.toString(anum, 7);
			System.out.println(astr);
			String bstr = Integer.toString(bnum, 7);
			System.out.println(bstr);
			System.out.println(1 + random.nextInt(astr.length() - bstr.length()));
		}
	}
}
