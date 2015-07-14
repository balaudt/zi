package zi.hrank;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SnakeLadder {

	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader("/home/bala/temp/ground.txt"));
		// BufferedReader reader = new BufferedReader(new
		// InputStreamReader(System.in));
		int t = Integer.parseInt(reader.readLine());
		String[] inStr;
		for (int i = 0; i < t; i++) {
			int ln = Integer.parseInt(reader.readLine());
			Map<Integer, Integer> ladders = new HashMap<>();
			for (int j = 0; j < ln; j++) {
				inStr = reader.readLine().split(" ");
				ladders.put(Integer.parseInt(inStr[0]), Integer.parseInt(inStr[1]));
			}
			int sn = Integer.parseInt(reader.readLine());
			Map<Integer, Integer> snakes = new HashMap<>();
			for (int j = 0; j < sn; j++) {
				inStr = reader.readLine().split(" ");
				snakes.put(Integer.parseInt(inStr[0]), Integer.parseInt(inStr[1]));
			}
			Set<Integer> visited = new HashSet<>();
			visited.add(1);
			boolean added = true;
			Set<Integer> lastVisit = new HashSet<>();
			lastVisit.add(1);
			int count = 0;
			while (added) {
				added = false;
				Set<Integer> currentVisit = new HashSet<>();
				for (Integer num : lastVisit) {
					for (int diceFace = 1; diceFace <= 6; diceFace++) {
						int nextNum = num + diceFace;
						if (!visited.contains(nextNum) && !currentVisit.contains(nextNum)) {
							currentVisit.add(nextNum);
							if (ladders.containsKey(nextNum))
								currentVisit.add(ladders.get(nextNum));
							else if (snakes.containsKey(nextNum))
								currentVisit.add(snakes.get(nextNum));
							added = true;
						}
					}
				}
				count++;
				if (currentVisit.contains(100)) {
					break;
				}
				visited.addAll(currentVisit);
				lastVisit = currentVisit;
			}
			if (added)
				System.out.println(count);
			else
				System.out.println(-1);
		}
		reader.close();
	}
}
