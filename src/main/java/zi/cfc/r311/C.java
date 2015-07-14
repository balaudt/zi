package zi.cfc.r311;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class C {

	static final String FOLDER = "C:/ft/39/";
	static int n;

	public static void main(String[] args) throws Exception {
//		Scanner scanner = new Scanner(new File(FOLDER + "C-sample.in"));
		 Scanner scanner = new Scanner(System.in);
		n = Integer.parseInt(scanner.nextLine());
		Map<Integer, EquiLengthLeg> map = new HashMap<Integer, EquiLengthLeg>();
		Map<Integer, Integer> countMap = new HashMap<Integer, Integer>();
		String[] lStr = scanner.nextLine().split(" ");
		String[] dStr = scanner.nextLine().split(" ");
		for (int i = 0; i < n; i++) {
			int l = Integer.parseInt(lStr[i]);
			int d = Integer.parseInt(dStr[i]);
			EquiLengthLeg leg = map.get(l);
			if (leg == null) {
				leg = new EquiLengthLeg();
				map.put(l, leg);
			}
			leg.add(d);
			countMap.put(l, leg.count);
		}
		ArrayList<EquiLengthLeg> legs = new ArrayList<EquiLengthLeg>(map.values());
		Collections.sort(legs);
		ArrayList<Integer> counts = new ArrayList<Integer>(countMap.values());
		Collections.sort(counts, Collections.reverseOrder());
		int min = Integer.MAX_VALUE;
		for (Integer currentCount : counts) {
			if (currentCount > n / 2 + 1) {
				min = 0;
				break;
			}
			int legsToRemove = n - (2 * currentCount - 1), legsRemoved = 0;
			int currentAns = 0;
			for (EquiLengthLeg leg : legs) {
				int legCount = leg.count;
				if (legsRemoved + legCount <= legsToRemove) {
					currentAns += leg.dSum;
					legsRemoved += leg.count;
					continue;
				}
				int curLegToRemove = legsToRemove - legsRemoved;
				ArrayList<Integer> ds = leg.ds;
				for (int i = 0; i < curLegToRemove; i++) {
					currentAns += ds.get(i);
					legsRemoved++;
				}
				break;
			}
			if (currentAns < min)
				min = currentAns;
		}
		System.out.println(min);
		scanner.close();
	}

	static class EquiLengthLeg implements Comparable<EquiLengthLeg> {
		int count = 0, dSum = 0;
		ArrayList<Integer> ds = new ArrayList<Integer>();

		void add(int d) {
			count++;
			dSum += d;
			int ip = Collections.binarySearch(ds, d);
			if (ip >= 0) {
				ds.add(ip, d);
			} else {
				ip = -ip - 1;
				ds.add(ip, d);
			}
		}

		public int compareTo(EquiLengthLeg o) {
			return dSum - o.dSum;
		}

		@Override
		public String toString() {
			return "EquiLengthLeg [count=" + count + ", dSum=" + dSum + ", ds=" + ds + "]";
		}

	}
}
