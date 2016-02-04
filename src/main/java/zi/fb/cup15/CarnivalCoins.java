package zi.fb.cup15;

import java.io.BufferedReader;
import java.io.FileReader;

import org.apache.commons.math3.distribution.BinomialDistribution;

public class CarnivalCoins {

	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader("C:/ft/fb/B-sample.in"));
		int t = Integer.parseInt(reader.readLine());
		String inStr[];
		for (int i = 0; i < t; i++) {
			inStr = reader.readLine().split(" ");
			int n = Integer.parseInt(inStr[0]);
			int k = Integer.parseInt(inStr[1]);
			double p = Double.parseDouble(inStr[2]);
			double ans = 0;
			for (int j = k; j <= n; j++) {
				double chance = 1 - (new BinomialDistribution(j, p).cumulativeProbability(k - 1));
				double cur = chance * ((int) (n / j));
				if (n % j >= k)
					cur += 1 - (new BinomialDistribution(n % j, p).cumulativeProbability(k - 1));
				if (cur > ans)
					ans = cur;
			}
			System.out.println(ans);
		}
		reader.close();
	}
}
