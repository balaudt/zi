package zi.chef.y15.octLong;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class F {
	static final int P = (int) (1e9 + 7);
	static final int NLIM = 100000;
	static final long[] factorials = new long[NLIM];

	public static void main(String[] args) throws Exception {
		factorials[0] = 1;
		for (int i = 1; i < NLIM; i++) {
			factorials[i] = factorials[i - 1] * (i + 1) % P;
		}

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(reader.readLine());
		String[] inStr, inStr1;
		for (int i = 0; i < t; i++) {
			inStr = reader.readLine().split(" ");
			int n = Integer.parseInt(inStr[0]);
			int k = Integer.parseInt(inStr[1]);
			inStr = reader.readLine().split(" ");
			inStr1 = reader.readLine().split(" ");
			int[] p = new int[n];
			int[] q = new int[n];
			for (int j = 0; j < n; j++) {
				p[j] = Integer.parseInt(inStr[j]);
				q[j] = Integer.parseInt(inStr1[j]);
			}
			inStr = inStr1 = null;
			if (k == 1) {
				if (Arrays.equals(p, q)) {
					System.out.println(1);
				} else {
					System.out.println(-1);
				}
				continue;
			}
			if (k == n) {
				int j = 0, l;
				while (q[j++] != p[0])
					;
				j--;
				boolean flag = true;
				for (l = 0; j < n; l++, j++) {
					if (p[l] != q[j]) {
						flag = false;
						break;
					}
				}
				if (!flag) {
					System.out.println(-1);
					continue;
				}
				for (j = 0; l < n; l++, j++) {
					if (p[l] != q[j]) {
						flag = false;
						break;
					}
				}
				if (!flag) {
					System.out.println(-1);
				} else {
					System.out.println(q[0]);
				}
				continue;
			}
			if (k % 2 == 0) {
				long ans = 1, mult;
				for (int j = 0; j < n - 1; j++) {
					mult = (q[j] - 1 * factorials[n - j - 2]) % P;
					ans = (ans + mult) % P;
				}
				System.out.println(ans);
				continue;
			}
			long pPos, qPos, qPosInGrp, t1, t2;
			pPos = qPos = qPosInGrp = 0;
			byte pGrpInd, qGrpInd;
			pGrpInd = qGrpInd = 0;
			for (int j = 0; j < n - 1; j++) {
				t1 = p[j] * factorials[n - j - 2];
				pGrpInd = (byte) ((t1 + pGrpInd) % 4);
				t2 = t1 / 2;
				t1 %= P;
				t2 %= P;
				pPos = (pPos + t1) % P;

				t1 = q[j] * factorials[n - j - 2];
				qGrpInd = (byte) ((t1 + qGrpInd) % 4);
				t2 = t1 / 2;
				t1 %= P;
				t2 %= P;
				qPos = (qPos + t1) % P;
				qPosInGrp = (qPosInGrp + t2) % P;
			}
			boolean pGrp = pGrpInd == 0 || pGrpInd == 3;
			boolean qGrp = qGrpInd == 0 || qGrpInd == 3;
			if (pGrp != qGrp) {
				System.out.println(-1);
				continue;
			}

		}
	}
}
