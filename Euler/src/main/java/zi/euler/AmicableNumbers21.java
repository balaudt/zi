package zi.euler;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.ImmutableMap;

public class AmicableNumbers21 {

	public static void main(String[] args) {
		Map<Integer, Integer> divisorSum = new HashMap<Integer, Integer>();
		Map<Integer, Map<Integer, Boolean>> divisors = new HashMap<Integer, Map<Integer, Boolean>>();
		Set<Integer> amicableNos = new HashSet<>();
		divisors.put(2, ImmutableMap.<Integer, Boolean> of(1, true));
		for (int i = 3; i < 10001; i++) {
			Map<Integer, Boolean> curDivisors = new HashMap<>();
			for (int j = i / 2; j >= 2; j--) {
				if (curDivisors.containsKey(j)) {
					continue;
				}
				if (i % j == 0) {
					curDivisors.put(j, true);
					curDivisors.put(i / j, true);
					curDivisors.putAll(divisors.get(j));
					curDivisors.putAll(divisors.get(i / j));
				}
			}
			if (!curDivisors.containsKey(1)) {
				curDivisors.put(1, true);
			}
			divisors.put(i, curDivisors);
			int sum = 0;
			for (Integer no : curDivisors.keySet()) {
				sum += no;
			}
			divisorSum.put(i, sum);
			Integer oppNum = divisorSum.get(sum);
			if (oppNum != null && oppNum == i && oppNum!= sum) {
				amicableNos.add(oppNum);
				amicableNos.add(sum);
			}
		}
		int sum = 0;
		for (Integer no : amicableNos) {
//			System.out.println(no + "=>" + divisors.get(no).keySet());
			sum += no;
		}
//		System.out.println(amicableNos);
		System.out.println(sum);
	}
}
