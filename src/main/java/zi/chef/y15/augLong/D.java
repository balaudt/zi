package zi.chef.y15.augLong;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class D {

	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		long[] lkup = new long[81];
		lkup[0] = 2;
		lkup[1] = 3;
		for (int i = 2; i < lkup.length; i++) {
			lkup[i] = lkup[i - 1] + lkup[i - 2];
			lkup[i - 2]--;
		}
		lkup[79]--;
		lkup[80]--;
//				System.out.println(Arrays.toString(lkup));
		int t = Integer.parseInt(reader.readLine());
		for (int i = 0; i < t; i++) {
			long n = Long.parseLong(reader.readLine());
			int ip = Arrays.binarySearch(lkup, n);
			if (ip < 0)
				ip = -ip - 1;
			System.out.println(ip + 1);
		}
		reader.close();
	}
	/*
		public static void main(String[] args) {
			for (int k = 1; k < 14; k++) {
				Integer iv[] = new Integer[k];
				for (int i = 0; i < iv.length; i++) {
					iv[i] = i;
				}
				Generator<Integer> generator = Factory.createSubSetGenerator(Factory.createVector(iv));
				int count = 0;
				for (ICombinatoricsVector<Integer> comb : generator) {
					List<Integer> vector = comb.getVector();
					if (vector.size() == 0)
						continue;
					if (vector.size() == 1) {
						count++;
						continue;
					}
					boolean isValid = true;
					int lastNum = -2;
					for (Integer v : vector) {
						if (v == lastNum + 1) {
							isValid = false;
							break;
						}
						lastNum = v;
					}
					if (isValid) {
						//					System.out.println(vector);
						count++;
					}
				}
				System.out.println(k + "\t" + count);
			}
		}*/
}
