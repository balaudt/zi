package zi.chef.y15.decLong;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Arrays;

public class G {
	static final int BASE = 7;

	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader("/Users/balaudt/Temp/dec/G-sin-1.in"));
		System.setOut(new PrintStream("/Users/balaudt/Temp/dec/G-sin-1.out"));
//				BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
				long time = System.currentTimeMillis();
		int t = Integer.parseInt(reader.readLine());
		for (int i = 0; i < t; i++) {
			char[] a = reader.readLine().toCharArray();
			char[] b = reader.readLine().toCharArray();
			int l = Integer.parseInt(reader.readLine());
			char[][] multTable = mult(b);
			char[][] trans = new char[2][b.length + 1];
			System.arraycopy(a, 0, trans[0], 1, b.length);
			trans[0][0] = '0';
			int ind = b.length;
			StringBuilder builder = new StringBuilder(a.length - b.length);
			while (true) {
				int j = 0;
				for (; j < BASE; j++) {
					if (subtract(trans[0], multTable[j], trans[1]))
						break;
				}
				builder.append(j - 1);
				if (ind == a.length)
					break;
				else {
					System.arraycopy(trans[1], 1, trans[0], 0, b.length);
					trans[0][b.length] = a[ind++];
				}
			}
			String result = new String(trimZeros(builder.toString()));
			if (result.length() <= l)
				System.out.println(result);
			else {
				char[] trimmed = trimZeros(result.substring(result.length() - l));
				System.out.println(trimmed.length == 0 ? '0' : new String(trimmed));
			}
		}
				System.out.println(System.currentTimeMillis() - time);
		reader.close();
	}

	static char[] trimZeros(String sub) {
		int firNonZero = 0;
		while (firNonZero < sub.length() && sub.charAt(firNonZero) == '0')
			firNonZero++;
		//		System.out.println("o:" + sub);
		return sub.substring(firNonZero).toCharArray();
	}

	static char[][] mult(char b[]) {
		char[][] res = new char[BASE][];
		for (int i = 0; i < BASE; i++) {
			StringBuilder builder = new StringBuilder();
			int carry = 0, stepMult;
			for (int j = b.length - 1; j >= 0; j--) {
				stepMult = i * (b[j] - '0') + carry;
				builder.append(stepMult % BASE);
				carry = stepMult / BASE;
			}
			builder.append(carry);
			res[i] = builder.reverse().toString().toCharArray();
		}
		return res;
	}

	static boolean subtract(char a[], char b[], char[] res) {
		//		System.out.println("a:" + new String(a));
		//		System.out.println("b:" + new String(b));
		boolean borrow = false;
		int ind = 0;
		while (ind < b.length && a[ind] == b[ind])
			ind++;
		if (ind < b.length && a[ind] < b[ind])
			return true;

		for (int i = b.length - 1; i >= 0; i--) {
			int l = a[i];
			if (borrow)
				l--;
			if (b[i] > l) {
				l += BASE;
				borrow = true;
			} else {
				borrow = false;
			}
			res[i] = (char) (l - b[i] + '0');
		}
		return false;
	}
}
