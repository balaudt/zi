package zi.chef.y15.sdown.r1B;

import java.io.File;
import java.util.Scanner;

public class A {
	public static final String FOLDER_ROOT = "C:/ft/28/";

	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(new File(FOLDER_ROOT + "A-gen.in"));
		long time = System.currentTimeMillis();
//		 Scanner scanner = new Scanner(System.in);
		int t = scanner.nextInt();
		// BitSet one = new BitSet(1);
		// one.set(1);
		// BitSet zero = new BitSet(1);
		byte in;
		for (int i = 0; i < t; i++) {
			int n = scanner.nextInt(), count1 = 0;
			// byte res = 0;
			// BitSet res = new BitSet(1);
//			boolean res = false;
//			byte res=0;
			for (int j = 0; j < n; j++) {
				in = scanner.nextByte();
				if (in == 1) {
					count1++;
					// res.xor(one);
//					res = !res;
//					res^=1;
				}
			}
//			 if (res.get(1))
			if (count1%2==0)
				System.out.println(n-count1);
			else
				System.out.println(count1);
		}
		System.out.println(System.currentTimeMillis() - time);
		scanner.close();
	}

}
