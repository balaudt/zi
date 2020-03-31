package zi.leet.medium;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author balamurugan
 */
//https://leetcode.com/problems/time-based-key-value-store/
public class TimeMap {
	/**
	 * Initialize your data structure here.
	 */
	private Map<String, TreeMap<Integer, String>> ds;

	public TimeMap() {
		ds = new HashMap<>();
	}

	public void set(String key, String value, int timestamp) {
		TreeMap<Integer, String> keyMap = ds.getOrDefault(key, new TreeMap<>());
		keyMap.put(timestamp, value);
		ds.put(key, keyMap);
	}

	public String get(String key, int timestamp) {
		if (!ds.containsKey(key)) {
			return "";
		}
		TreeMap<Integer, String> keyMap = ds.get(key);
		if (keyMap.containsKey(timestamp)) return keyMap.get(timestamp);
		Map.Entry<Integer, String> lowerEntry = keyMap.lowerEntry(timestamp);
		return lowerEntry == null ? "" : lowerEntry.getValue();
	}
}
