package zi.leet.google;

import java.util.Stack;

/**
 * @author balamurugan
 */
//https://leetcode.com/problems/longest-absolute-file-path/
public class LongestFilePath {
	public int lengthLongestPath(String input) {
		String[] files = input.split("\n");
		Stack<Integer> dirLengths = new Stack<>();
		int runningSum = 0, result = Integer.MIN_VALUE, lastLevel = 0;
		for (String file : files) {
			int currentLevel = 0;
			while (currentLevel < file.length() && file.charAt(currentLevel) == '\t') currentLevel++;
			if (currentLevel <= lastLevel) {
				int diff = lastLevel - currentLevel + 1;
				for (int j = 0; j < diff; j++) {
					if (!dirLengths.isEmpty()) {
						runningSum -= dirLengths.pop();
					}
				}
			}
			dirLengths.push(file.length() - currentLevel);
			runningSum += file.length() - currentLevel;
			if (file.indexOf('.') != -1)
				result = Math.max(result, runningSum + dirLengths.size() - 1);
			lastLevel = currentLevel;
		}
		return result == Integer.MIN_VALUE ? 0 : result;
	}
}
