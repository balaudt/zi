package zi.jam.y18.r1C;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author balamurugan
 */
public class NewWord {
	static class Node implements Comparable<Node> {
		Map<String, Node> children;
		int count;

		@Override
		public int compareTo(Node o) {
			return count - o.count;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(reader.readLine());
		for (int i = 0; i < t; i++) {
			String[] split = reader.readLine().split(" ");
			int n = Integer.parseInt(split[0]);
			int l = Integer.parseInt(split[1]);
			if (l > 2) {
				Map<Integer, Set<Character>> chars = new HashMap<>();
				for (int j = 0; j < l; j++) {
					chars.put(j, new HashSet<>());
				}
				Set<String> existingWords = new HashSet<>();
				for (int j = 0; j < n; j++) {
					String line = reader.readLine();
					existingWords.add(line);
					char[] lineArray = line.toCharArray();
					for (int k = 0; k < lineArray.length; k++) {
						chars.get(k).add(lineArray[k]);
					}
				}
				Node root = new Node();
				root.children = new HashMap<>();
				for (String existingWord : existingWords) {
					Node node = root;
					for (int j = 0; j < l; j++) {
						String prefix = existingWord.substring(0, j + 1);
						if (node.children.containsKey(prefix)) {
							node = node.children.get(prefix);
							node.count++;
						} else {
							Node newNode = new Node();
							newNode.count = 1;
							newNode.children = new HashMap<>();
							node.children.put(prefix, newNode);
							node = newNode;
						}
					}
				}
				boolean found = false;
				Node node = root;
				for (int j = 0; j < l; j++) {
					int size = node.children.size();
					int allPossible = chars.get(j).size();
					if (size < allPossible) {
						Set<Character> levelDup = new HashSet<>(chars.get(j));
						for (Map.Entry<String, Node> child : node.children.entrySet()) {
							levelDup.remove(child.getKey().charAt(j));
						}
						StringBuilder possible = new StringBuilder();
						possible.append(node.children.keySet().iterator().next(), 0, j);
						possible.append(levelDup.iterator().next());
						for (int k = j + 1; k < l; k++) {
							possible.append(chars.get(k).iterator().next());
						}
						System.out.println(String.format("Case #%d: %s", i + 1, possible.toString()));
						found = true;
						break;
					}
					int leastCount = Integer.MAX_VALUE;
					Node next = null;
					for (Map.Entry<String, Node> entry : node.children.entrySet()) {
						if (entry.getValue().count < leastCount) {
							next = entry.getValue();
							leastCount = next.count;
						}
					}
					node = next;
				}
				if (!found) {
					System.out.println(String.format("Case #%d: -", i + 1));
				}
			}
			if (l == 2) {
				Set<String> existingWords = new HashSet<>();
				Set<Character> firstChars = new HashSet<>();
				Set<Character> secondChars = new HashSet<>();
				for (int j = 0; j < n; j++) {
					String line = reader.readLine();
					firstChars.add(line.charAt(0));
					secondChars.add(line.charAt(1));
					existingWords.add(line);
				}
				boolean found = false;
				for (Character a : firstChars) {
					if (found) {
						break;
					}
					for (Character b : secondChars) {
						String out = new String(new char[]{a, b});
						if (!existingWords.contains(out)) {
							found = true;
							System.out.println(String.format("Case #%d: %s", i + 1, out));
							break;
						}
					}
				}
				if (!found) {
					System.out.println(String.format("Case #%d: -", i + 1));
				}
			}
			if (l == 1) {
				for (int j = 0; j < n; j++) {
					reader.readLine();
				}
				System.out.println(String.format("Case #%d: -", i + 1));
			}
		}
	}
}
