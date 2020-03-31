package zi.leet.easy;

/**
 * @author balamurugan
 */
public class BuyAndSellK {
	public int maxProfit(int k, int[] in) {
		if (in.length == 0) return 0;
		k = Math.min(k, in.length);
		int[] prevProfits = new int[in.length + 1];
		for (int trans = 1; trans <= k; trans++) {
			int[] curProfits = new int[in.length + 1];
			for (int st = 0; st < in.length; st++) {
				int min = in[st], profit = 0;
				if (trans != 1 && prevProfits[st] == 0)
					break;
				for (int i = st + 1; i < in.length; i++) {
					if (in[i] >= min)
						profit = Math.max(profit, in[i] - min + prevProfits[i + 1]);
					else
						min = in[i];
				}
				curProfits[st] = profit;
			}
			prevProfits = curProfits;
		}
		return prevProfits[0];
	}

}
