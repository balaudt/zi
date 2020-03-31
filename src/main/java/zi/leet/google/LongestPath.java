package zi.leet.google;

import com.google.common.base.Joiner;

import java.util.Arrays;
import java.util.Stack;

/**
 * @author balamurugan
 */
public class LongestPath {
	public int lengthLongestPath(String input) {
		String[] lines = input.split("\\n");
//		System.out.println(Arrays.toString(lines));
		int ans = 0, level = 0, curLength = 0;
		Stack<String> directories = new Stack<>();
		for (String line : lines) {
			String[] levels = line.split("\\t");
			int curLevel = levels.length;
			String terminalPath = levels[levels.length - 1];
			if (curLevel > level) {
				if (terminalPath.contains(".")) {
					if (curLength + terminalPath.length() > ans) {
						ans = curLength + terminalPath.length();
//						System.out.println(Joiner.on('/').join(directories).concat(terminalPath));
					}
				} else {
					level++;
					directories.push(terminalPath);
					curLength += terminalPath.length() + 1;
				}
			} else {
				while (directories.size() >= curLevel) {
					curLength -= directories.pop().length() + 1;
				}
				directories.push(terminalPath);
				curLength += terminalPath.length() + 1;
			}
		}
		return ans;
	}

	public static void main(String[] args) {
		System.out.println(new LongestPath().lengthLongestPath("dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext"));
	}
}
