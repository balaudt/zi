package zi.leet.mock.amazon;

import java.util.ArrayList;
import java.util.LinkedHashSet;

/**
 * @author balamurugan
 */
//https://leetcode.com/problems/prison-cells-after-n-days/
public class PrisonAfterNDays {
	public int[] prisonAfterNDays(int[] cells, int N) {
		int l = cells.length;
		char[] lastState = new char[l];
		LinkedHashSet<String> states = new LinkedHashSet<>();
		for (int i = 0; i < l; i++) {
			lastState[i] = (char) (cells[i] + '0');
		}
		states.add(new String(lastState));
		String cycleStart = null;
		while (true) {
			char[] current = new char[l];
			current[0] = current[l - 1] = '0';
			for (int i = 1; i < l - 1; i++) {
				if (lastState[i - 1] == '0' && lastState[i + 1] == '0')
					current[i] = '1';
				else if (lastState[i - 1] == '1' && lastState[i + 1] == '1')
					current[i] = '1';
				else
					current[i] = '0';
			}
			String currentState = new String(current);
			if (states.contains(currentState)) {
				cycleStart = currentState;
				break;
			}
			lastState = current;
			states.add(currentState);
			if (states.size() > N) return transform(new ArrayList<>(states).get(N));
		}
		ArrayList<String> statesList = new ArrayList<>(states);
		int cycleStartInd = -1;
		for (int i = 0; i < statesList.size(); i++) {
			if (statesList.get(i).equals(cycleStart)) {
				cycleStartInd = i;
				break;
			}
		}
		N -= cycleStartInd;
		int cycleLenth = statesList.size() - cycleStartInd;
		return transform(statesList.get(cycleStartInd + N % cycleLenth));
	}

	private int[] transform(String out) {
		int[] result = new int[out.length()];
		for (int i = 0; i < out.length(); i++) {
			result[i] = out.charAt(i) - '0';
		}
		return result;
	}
}
