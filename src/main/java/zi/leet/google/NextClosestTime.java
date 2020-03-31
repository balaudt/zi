package zi.leet.google;

import java.util.HashSet;
import java.util.Set;

/**
 * @author balamurugan
 */
public class NextClosestTime {
	public String nextClosestTime(String time) {
		String[] in = time.split(":");
		int hr = Integer.parseInt(in[0]), min = Integer.parseInt(in[1]);
		Set<Character> digits = new HashSet<>();
		for (char c : time.toCharArray()) {
			digits.add(c);
		}
		while (true) {
			if (min == 59) {
				min = 0;
				if (hr == 23) hr = 0;
				else hr++;
			} else
				min++;
			String currentTime = (hr < 10 ? "0" : "") + hr + ":" + (min < 10 ? "0" : "") + min;
			boolean isValid = true;
			for (int i = 0; i < 5; i++) {
				if (!digits.contains(currentTime.charAt(i))) {
					isValid = false;
					break;
				}
			}
			if (isValid) return currentTime;
		}
	}
}
