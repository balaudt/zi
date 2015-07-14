package zi.cfc.r308;

public class BGen {

	public static void main(String[] args) throws Exception {
//		Random random = new Random();
//		int n = random.nextInt(1000) + 1000;
		long ans = 0;
		for (int n = 1; n <=10000 ; n++) {
			ans=0;
			for (int i = 1; i <= n; i++) {
				ans += Integer.toString(i).length();
			}
			System.out.println(n + "\t" + ans);
		}
	}
}
