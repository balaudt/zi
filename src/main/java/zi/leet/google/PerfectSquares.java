package zi.leet.google;

/**
 * @author balamurugan
 */
public class PerfectSquares {
	public int numSquares(int n) {
		int maxRoot = (int) Math.floor(Math.sqrt(n)), ans = 0;
		for (int i = maxRoot; i > 0; i--) {
			int curSq = i * i;
			ans += n / curSq;
			n -= (n / curSq) * curSq;
		}
		return ans;
	}

	public static void main(String[] args) {
		PerfectSquares perfectSquares = new PerfectSquares();
		System.out.println(perfectSquares.numSquares(12));
//		System.out.println(perfectSquares.numSquares(13));
	}
}
