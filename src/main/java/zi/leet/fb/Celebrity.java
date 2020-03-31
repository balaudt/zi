package zi.leet.fb;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author balamurugan
 */
public class Celebrity {
	private boolean knows(int p1, int p2) {
		Multimap<Integer, Integer> in = ArrayListMultimap.create();
		in.put(0, 1);
		in.put(0, 2);
		in.put(2, 1);
		in.put(3, 1);
		return in.containsKey(p1) && in.get(p1).contains(p2);
	}

	private int findCelebrity(int n) {
		Set<Integer> nonCelebrities = new HashSet<>();
		Map<Integer, Integer> knownMap = new HashMap<>();
		//Multimap<Integer, Integer> knownMap;
		for (int p = 0; p < n; p++) {
			if(knownMap.get(p)!=p-1){
				//p is not a celebrity
			}
			boolean isCeleb = true;
			if (nonCelebrities.contains(p)) continue;
			for (int i = p+1; i < n; i++) {
				boolean iKnowsP = knows(i, p);
				if (iKnowsP) {
					nonCelebrities.add(i);
				}
				boolean pKnowsI = knows(p, i);
				if (knownMap.get(i) == null) {
					knownMap.put(i, 0);
				} else {
					knownMap.put(i, knownMap.get(i) + 1);
				}
				if (!iKnowsP || pKnowsI) {
					isCeleb = false;
					break;
				}
			}
			if (isCeleb) {
				return p;
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		System.out.println(new Celebrity().findCelebrity(4));
	}
}
