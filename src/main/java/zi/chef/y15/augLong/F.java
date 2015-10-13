package zi.chef.y15.augLong;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.BitSet;

public class F {

	public static void main(String[] args) throws Exception {
		//				BufferedReader reader = new BufferedReader(new FileReader("/home/bala/temp/21/F-gen-1.in"));
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String[] inStr = reader.readLine().split(" ");
		int n = Integer.parseInt(inStr[0]);
		int k = Integer.parseInt(inStr[1]);
		/*if (n > 1000) {
			reader.close();
			throw new UnsupportedOperationException();
		}*/

		inStr = reader.readLine().split(" ");
		int a[] = new int[n];
		long nchoose2 = n * (n - 1) / 2;

		//		BitSet left = new BitSet(nchoose2);
		long count = 0;
		int maxLeft = -1;
		for (int r = 0; r < n; r++) {
			a[r] = Integer.parseInt(inStr[r]);
			int loopMax = maxLeft < 0 ? -1 : maxLeft;
			for (int l = r - 1; l > loopMax; l--) {
				if (a[l] % a[r] == k) {
					maxLeft = l;
					break;
				}
			}
			if (maxLeft > -1) {
				//				int[] st = leftManipulate(maxLeft, r);
				count += maxLeft + 1;
				//				left.set(st[0], st[1] + 1);
			}
		}

		/*BitSet right = new BitSet(nchoose2);
		int minRight = -1;
		for (int l = n - 1; l >= 0; l--) {
			int loopMin = minRight < 0 ? n : minRight;
			for (int r = l + 1; r < loopMin; r++) {
				if (a[l] % a[r] == k) {
					minRight = r;
					break;
				}
			}
			if (minRight > -1) {
				int[] st = rightManipulate(n, l, minRight);
				right.set(st[0], st[1] + 1);
			}
		}
		
		System.out.println(left.cardinality());
		System.out.println(right.cardinality());
		
		int result = nchoose2 + n;
		for (int l = 0; l < n - 1; l++) {
			for (int r = n - 1; r > l; r--) {
				if (left.get(leftManipulate(l, r)[1]) || right.get(rightManipulate(n, l, r)[1]))
					result--;
			}
		}
		System.out.println(result);*/
		System.out.println(nchoose2 + n - count);

		reader.close();
	}

	static int[] leftManipulate(int l, int r) {
		int st = r * (r - 1) / 2;
		return new int[] { st, st + l };
	}

	static int[] rightManipulate(int n, int l, int r) {
		l++;
		int st = n * (n - 1) - (n - l) * (n - l + 1);
		st /= 2;
		return new int[] { st, st + n - r - 1 };
	}
}
