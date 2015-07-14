package zi.hrank;
public class DAnalysis {

	/*
	 * The order of brute force for the solution is: sum from k=1 to n/2
	 * (n-k+1)*(n-k)*k = left ( binom{n/2}{5} right ) For n=3000,
	 * nchoosek(n,4)>2e15
	 */
	public static void main(String[] args) {
		long ans = 0;
		for (int n = 1; n <= 20; n++) {
			ans = 0;
			for (int i = 1; i <= n; i++) {
				ans += 1 * (n - i) * (n - i + 1) / 2;
			}
			System.out.println(ans);
		}
	}
}
