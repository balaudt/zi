package zi.leet.mock.apple;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * @author balamurugan
 */
//https://leetcode.com/problems/reorder-log-files/
public class ReorderLogs {
	public String[] reorderLogFiles(String[] logs) {
		List<String> digitLogs = new ArrayList<>();
		TreeSet<Map.Entry<String, String>> letterLogs = new TreeSet<>((o1, o2) ->
				o1.getKey().equals(o2.getKey()) ? o1.getValue().compareTo(o2.getValue())
						: o1.getKey().compareTo(o2.getKey()));
		for (int i = 0; i < logs.length; i++) {
			String[] split = logs[i].split(" ");
			String identifier = split[0];
			char c = split[1].charAt(0);
			if (c >= 'a' && c <= 'z') {
				letterLogs.add(new AbstractMap.SimpleEntry<>(logs[i].substring(identifier.length() + 1), identifier));
			} else {
				digitLogs.add(logs[i]);
			}
		}
		List<String> output = new ArrayList<>();
		output.addAll(letterLogs.stream().map(e -> e.getValue() + " " + e.getKey()).collect(Collectors.toList()));
		output.addAll(digitLogs);
		return output.toArray(new String[logs.length]);
	}
}
