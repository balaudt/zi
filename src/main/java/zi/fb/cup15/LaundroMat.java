package zi.fb.cup15;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.Comparator;
import java.util.PriorityQueue;

import org.apache.commons.lang3.tuple.MutablePair;

public class LaundroMat {

	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader("/Users/balaudt/Temp/fb/B.in"));
		System.setOut(new PrintStream("/Users/balaudt/Temp/fb/B.out"));
		int t = Integer.parseInt(reader.readLine());
		for (int i = 0; i < t; i++) {
			String[] inStr = reader.readLine().split(" ");
			int l = Integer.parseInt(inStr[0]);
			int n = Integer.parseInt(inStr[1]);
			int m = Integer.parseInt(inStr[2]);
			int d = Integer.parseInt(inStr[3]);
			inStr = reader.readLine().split(" ");
			long[] w = new long[n];
			for (int j = 0; j < n; j++) {
				w[j] = Integer.parseInt(inStr[j]);
			}
			PriorityQueue<MutablePair<Integer, Long>> washWaitingTimes = new PriorityQueue<>(n,
					new Comparator<MutablePair<Integer, Long>>() {

						@Override
						public int compare(MutablePair<Integer, Long> o1, MutablePair<Integer, Long> o2) {
							long o = o1.right - o2.right;
							return o == 0 ? 0 : (o > 0 ? 1 : -1);
						}
					});
			for (int j = 0; j < n; j++) {
				washWaitingTimes.add(new MutablePair<Integer, Long>(j, w[j]));
			}
			long[] washOuts = new long[l];
			for (int j = 0; j < l; j++) {
				MutablePair<Integer, Long> washToUse = washWaitingTimes.poll();
				washOuts[j] = washToUse.right;
				washToUse.right += w[washToUse.left];
				washWaitingTimes.add(washToUse);
			}
			if (m >= l)
				System.out.println(String.format("Case #%d: %d", i + 1, washOuts[l - 1] + d));
			else {
				PriorityQueue<Long> dryerAvails = new PriorityQueue<>(m);
				for (int j = 0; j < m; j++) {
					dryerAvails.add(0l);
				}
				for (int j = 0; j < l; j++) {
					Long dryerToUse = dryerAvails.poll();
					dryerToUse = Math.max(washOuts[j], dryerToUse) + d;
					dryerAvails.add(dryerToUse);
				}
				long ans = Long.MIN_VALUE;
				for (Long dryerAvail : dryerAvails) {
					if (dryerAvail > ans)
						ans = dryerAvail;
				}
				System.out.println(String.format("Case #%d: %d", i + 1, ans));
			}
		}
		reader.close();
	}
}
