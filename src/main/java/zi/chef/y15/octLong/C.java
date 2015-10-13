package zi.chef.y15.octLong;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class C {

	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		float hourAng = 0, angDiff;
		int hour = 0, min = 0, minAng = 0;
		MultiMap<Float, int[]> lkup = new MultiMap<Float, int[]>(720);
		lkup.put(0f, new int[] { 0, 0 });
		while (true) {
			hourAng += 0.5;
			if (hourAng == 360) {
				break;
			}
			minAng += 6;
			min++;
			if (minAng == 360) {
				minAng = 0;
				hour++;
				min = 0;
			}
			angDiff = Math.abs(hourAng - minAng);
			if (angDiff > 180)
				angDiff = 360 - angDiff;
			lkup.put(angDiff, new int[] { hour, min });
			//			System.out.println(hour + "\t" + min + "\t" + hourAng + "\t" + minAng + "\t" + angDiff);
		}
		int t = Integer.parseInt(reader.readLine());
		StringBuilder builder = new StringBuilder();
		float threshold = 1f / 120;
		for (int i = 0; i < t; i++) {
			//			float ang = roundHalf(Float.parseFloat(reader.readLine()));
			float inAng = Float.parseFloat(reader.readLine());
			float ang = Math.round(inAng * 2) / 2f;
			if (Math.abs(inAng - ang) > threshold)
				continue;
			Collection<int[]> results = lkup.get(ang);
			for (int[] res : results) {
				builder.setLength(0);
				if (res[0] < 10)
					builder.append('0');
				builder.append(res[0]).append(':');
				if (res[1] < 10)
					builder.append('0');
				builder.append(res[1]);
				System.out.println(builder.toString());
			}
		}
	}

	static float roundHalf(float number) {
		float diff = number - (int) number;
		if (diff < 0.25)
			return (int) number;
		else if (diff < 0.75)
			return (int) number + 0.5f;
		else
			return (int) number + 1;
	}
}

class MultiMap<K, V> {
	Map<K, List<V>> map;

	public MultiMap() {
		map = new HashMap<K, List<V>>();
	}

	public MultiMap(int capacity) {
		map = new HashMap<K, List<V>>(capacity);
	}

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