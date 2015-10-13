package zi.chef.y15.octLong;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class B {

	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(reader.readLine());
		for (int i = 0; i < t; i++) {
			int n = Integer.parseInt(reader.readLine());
			char[] corAns = reader.readLine().toCharArray();
			char[] chefAns = reader.readLine().toCharArray();
			int corCt = 0;
			for (int j = 0; j < n; j++) {
				if (corAns[j] == chefAns[j])
					corCt++;
			}
			String[] inStr = reader.readLine().split(" ");
			int[] winnings = new int[n + 1];
			for (int j = 0; j < winnings.length; j++) {
				winnings[j] = Integer.parseInt(inStr[j]);
			}
			if (corCt == n) {
				System.out.println(winnings[n]);
				continue;
			}
			int maxWin = winnings[0], ct = 1;
			while (ct <= corCt) {
				if (winnings[ct] > maxWin)
					maxWin = winnings[ct];
				ct++;
			}
			System.out.println(maxWin);
		}
	}
}
