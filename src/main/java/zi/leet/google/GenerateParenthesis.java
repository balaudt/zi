package zi.leet.google;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author balamurugan
 */
public class GenerateParenthesis {
	public List<String> generateParenthesis(int n) {
		Map<String, List<Integer>> cur = new HashMap<>();
		cur.put("()", Collections.singletonList(1));
		for (int it = 1; it < n; it++) {
			Map<String, List<Integer>> next = new HashMap<>();
			for (Map.Entry<String, List<Integer>> curEntry : cur.entrySet()) {
				String key = curEntry.getKey();
			}
		}
		return new ArrayList<>(cur.keySet());
	}

	public static void main(String[] args) {
		System.out.println(new GenerateParenthesis().generateParenthesis(3));
	}
}
