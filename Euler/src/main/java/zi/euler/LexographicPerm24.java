package zi.euler;

import cern.colt.Arrays;

public class LexographicPerm24 {

	public static void main(String[] args) {
		Permutation permutation = new Permutation(10, 10);
		long count = 0;
		while (permutation.hasNext()) {
			int[] next = permutation.next();
			count++;
			if (count == 1000000l) {
				System.out.println(Arrays.toString(next).replaceAll(", ", "").substring(1,11));
				break;
			}
		}
	}
}
