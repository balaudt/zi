package zi.leet.fb;

/**
 * @author balamurugan
 */
public class TaskScheduler {
	public int leastInterval(char[] tasks, int n) {
		int[] frequencies = new int[26];
		for (int i = 0; i < tasks.length; i++) {
			frequencies[tasks[i] - 'A']++;
		}
		int max = -1;
		for (int i = 0; i < frequencies.length; i++) {
			max = Integer.max(max, frequencies[i]);
		}
		return tasks.length + (max - 1) * n;
	}

	public static void main(String[] args) {
		new TaskScheduler().leastInterval(new char[]{'A', 'A', 'A', 'B', 'B', 'B'}, 2);
	}
}
