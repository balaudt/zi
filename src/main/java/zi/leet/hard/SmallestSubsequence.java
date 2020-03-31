package zi.leet.hard;

import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * @author balamurugan
 */
public class SmallestSubsequence {
	public String smallestSubsequence(String text) {
		TreeMap<Character, TreeSet<Integer>> map = new TreeMap<>();
		for(int i=0;i<text.length();i++) {
			char c = text.charAt(i);
			TreeSet<Integer> pos = map.getOrDefault(c , new TreeSet<>());
			pos.add(i);
			map.put(c, pos);
		}

		int max = -1;
		TreeMap<Integer, Character> finalPos = new TreeMap<>();
		for(Map.Entry<Character, TreeSet<Integer>> entry: map.entrySet()) {
			char c = entry.getKey();
			TreeSet<Integer> pos = map.get(c);
			Integer curFinal = pos.higher(max);
			if(curFinal!=null) {
				max = curFinal;
			}   else {
				curFinal = pos.lower(max);
			}
			finalPos.put(curFinal, c);
		}

		char []out = new char[finalPos.size()];
		int ind = 0;
		for(char c : finalPos.values())
			out[ind++]=c;
		return new String(out);
	}
}
