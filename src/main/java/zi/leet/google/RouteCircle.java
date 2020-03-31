package zi.leet.google;

import java.util.HashMap;
import java.util.Map;

/**
 * @author balamurugan
 */
public class RouteCircle {
	public boolean judgeCircle(String moves) {
		Map<Character, Integer> directions = new HashMap<Character, Integer>() {
			{
				put('U', 0);
				put('D', 0);
				put('L', 0);
				put('R', 0);
			}
		};
		for (char c : moves.toCharArray()) {
			directions.put(c, directions.get(c) + 1);
		}
		return directions.get('U').equals(directions.get('D')) && directions.get('L').equals(directions.get('R'));
	}
}
