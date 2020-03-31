package zi.leet.easy;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * @author balamurugan
 */
public class CpuCycles {
	public int leastInterval(char[] tasks, int n) {
		int[] f = new int[26];
		for (int i = 0; i < tasks.length; i++)
			f[tasks[i] - 'A']++;
		PriorityQueue<Integer> taskPri = new PriorityQueue<>(Collections.reverseOrder());
		for (int i = 0; i < 26; i++)
			if (f[i] != 0)
				taskPri.offer(f[i]);
		int cpu = 0;
		while (!taskPri.isEmpty()) {
			PriorityQueue<Integer> next = new PriorityQueue<>(Collections.reverseOrder());
			int i;
			for (i = 0; i <= n && !taskPri.isEmpty(); i++) {
				int prev = taskPri.poll();
				if (prev > 1) next.offer(prev - 1);
			}
			next.addAll(taskPri);
			if (next.isEmpty())
				cpu += i;
			else
				cpu += n + 1;
			taskPri = next;
		}
		return cpu;
	}
}
