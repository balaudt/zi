package zi.leet.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author balamurugan
 */
public class LogSystem {
	private TreeMap<String, List<Integer>> store = new TreeMap<>();
	private List<String> grans = Arrays.asList("Second", "Minute", "Hour", "Day", "Month", "Year");

	public LogSystem() {

	}

	public void put(int id, String timestamp) {
		List<Integer> ids = store.getOrDefault(timestamp, new ArrayList<>());
		ids.add(id);
		store.put(timestamp, ids);
	}

	public List<Integer> retrieve(String s, String e, String gra) {
		s = trim(s, gra);
		e = trim(e, gra);
		List<Integer> results = new ArrayList<>();
		if (store.containsKey(s))
			results.addAll(store.get(s));
		String last = s;
		while (true) {
			Map.Entry<String, List<Integer>> entry = store.higherEntry(last);
			if (entry == null || trim(entry.getKey(), gra).compareTo(e) > 0)
				break;
			results.addAll(entry.getValue());
			last = entry.getKey();
		}
		return results;
	}

	private String trim(String dt, String gra) {
		char[] begin = dt.toCharArray();
		for (int i = 0, j = begin.length - 1; i < grans.size(); i++, j -= 3) {
			if (gra.equals(grans.get(i)))
				break;
			begin[j] = '0';
			begin[j - 1] = '0';
		}
		return new String(begin);
	}
}
