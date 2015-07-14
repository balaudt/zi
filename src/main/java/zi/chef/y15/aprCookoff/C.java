package zi.chef.y15.aprCookoff;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

//TODO Debug WA
public class C {
	public static final String FOLDER_ROOT = "C:/ft/12/";

	public static void main(String[] args) throws Exception {
//		BufferedReader reader = new BufferedReader(new FileReader(FOLDER_ROOT + "C-sample.in"));
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String[] inStr = reader.readLine().split(" ");
		int n = Integer.parseInt(inStr[0]);
		int q = Integer.parseInt(inStr[1]);
		int[] a = new int[n];
		inStr = reader.readLine().split(" ");
		for (int j = 0; j < n; j++) {
			a[j] = Integer.parseInt(inStr[j]);
		}

		MultiMap<Integer, Integer> greaterSequence = new MultiMap<Integer, Integer>();
		MultiMap<Integer, Integer> lesserSequence = new MultiMap<Integer, Integer>();

		for (int j = 0; j < a.length; j++) {
			for (int k = j + 1; k < a.length; k++) {
				if (a[j] > a[k])
					greaterSequence.put(j, k);
				else if (a[j] < a[k])
					lesserSequence.put(j, k);
			}
		}

		Set<Sequence> nextSequence = new HashSet<Sequence>();

		Set<Integer> keySet = greaterSequence.keySet();
		for (Integer key : keySet) {
			Collection<Integer> collection = greaterSequence.get(key);
			for (Integer value : collection) {
				Collection<Integer> col = lesserSequence.get(value);
				for (Integer num : col) {
					nextSequence.add(new Sequence(key, value, num, -1, -1));
				}
			}
		}

		Set<Sequence> fourthSequence = new HashSet<Sequence>();
		for (Sequence sequence : nextSequence) {
			int val = sequence.n3;
			Collection<Integer> collection = greaterSequence.get(val);
			for (Integer num : collection) {
				fourthSequence.add(new Sequence(sequence.n1, sequence.n2, sequence.n3, num, -1));
			}
		}

		nextSequence = new HashSet<Sequence>();
		for (Sequence sequence : fourthSequence) {
			int val = sequence.n4;
			Collection<Integer> collection = lesserSequence.get(val);
			for (Integer num : collection) {
				nextSequence.add(new Sequence(sequence.n1, sequence.n2, sequence.n3, sequence.n4, num));
			}
		}

		fourthSequence = null;
		greaterSequence = null;
		lesserSequence = null;
		// System.out.println(nextSequence);

		for (int j = 0; j < q; j++) {
			inStr = reader.readLine().split(" ");
			int l = Integer.parseInt(inStr[0]) - 1;
			int r = Integer.parseInt(inStr[1]) - 1;
			long count = 0;
			for (Sequence sequence : nextSequence) {
				if (sequence.n1 == l && sequence.n5 == r)
					count++;
			}
			System.out.println(count);
		}
	}

	static class Sequence {
		int n1, n2, n3, n4, n5;

		public Sequence(int n1, int n2, int n3, int n4, int n5) {
			this.n1 = n1;
			this.n2 = n2;
			this.n3 = n3;
			this.n4 = n4;
			this.n5 = n5;
		}

		@Override
		public String toString() {
			return new StringBuilder().append(n1).append(' ').append(n2).append(' ').append(n3).append(' ').append(n4).append(' ')
					.append(n5).toString();
		}
	}

}

class MultiMap<K, V> {
	Map<K, List<V>> map = new HashMap<K, List<V>>();

	public void put(K key, V value) {
		List<V> list = map.get(key);
		if (list == null) {
			list = new ArrayList<V>();
			map.put(key, list);
		}
		list.add(value);
	}

	public Collection<V> get(K key) {
		List<V> out = map.get(key);
		if (out == null)
			return new ArrayList<V>();
		return out;
	}

	public Set<K> keySet() {
		return map.keySet();
	}
}
