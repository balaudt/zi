package zi.leet.easy;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author balamurugan
 */
public class SimpleKSrot {
	class Word implements Comparable<Word> {
		String word;
		int frequency;

		public Word(String word, int frequency) {
			this.word = word;
			this.frequency = frequency;
		}

		public int compareTo(Word o) {
			if (o.frequency != frequency) return frequency - o.frequency;
			return o.word.compareTo(word);
		}

		public String toString() {
			return word + "-" + frequency;
		}
	}

	public List<String> topKFrequent(String[] words, int k) {
		Map<String, Integer> frequencies = new HashMap<>();
		for (String word : words) {
			int ct = frequencies.getOrDefault(word, 0);
			frequencies.put(word, ct + 1);
		}
		Iterator<Map.Entry<String, Integer>> it = frequencies.entrySet().iterator();
		PriorityQueue<Word> q = new PriorityQueue<>();
		for (int i = 0; i < k; i++) {
			Map.Entry<String, Integer> entry = it.next();
			q.add(new Word(entry.getKey(), entry.getValue()));
		}
		while (it.hasNext()) {
			Word min = q.peek();
			Map.Entry<String, Integer> entry = it.next();
			Word w = new Word(entry.getKey(), entry.getValue());
			if (w.compareTo(min) > 0) {
				q.poll();
				q.offer(w);
			}
		}
		System.out.println(q);
		LinkedList<String> result = new LinkedList<>();
		for (Word w : q) {
			result.addFirst(w.word);
		}
		return result;
	}
}
