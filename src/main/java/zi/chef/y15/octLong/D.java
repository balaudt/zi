package zi.chef.y15.octLong;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeMap;

public class D {
	static final int P = (int) (1e9 + 7);

	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String[] inStr;
		inStr = reader.readLine().split(" ");
		int n = Integer.parseInt(inStr[0]);
		int m = Integer.parseInt(inStr[1]);
		int c = Integer.parseInt(inStr[2]);
		NullSafeLookupMap upperMap = new NullSafeLookupMap(n);
		NullSafeLookupMap lowerMap = new NullSafeLookupMap(m);
		inStr = reader.readLine().split(" ");
		for (int i = 0; i < n; i++) {
			upperMap.put(Integer.parseInt(inStr[i]));
		}
		inStr = reader.readLine().split(" ");
		for (int i = 0; i < m; i++) {
			lowerMap.put(Integer.parseInt(inStr[i]));
		}
		TreeMap<Integer, Long> spWithRadii = new TreeMap<>();
		upperMap.entrySet().forEach(entry -> {
			Integer radius = entry.getKey();
			Integer lowVal = lowerMap.get(radius);
			if (lowVal != null)
				spWithRadii.put(radius, entry.getValue() * (long) lowVal);
		});
		int actC = spWithRadii.size();
		long[] spheres = new long[actC];
		Iterator<Long> it = spWithRadii.values().iterator();
		for (int i = 0; i < spheres.length; i++) {
			spheres[i] = it.next();
		}

		if (actC < 2) {
			for (int i = 0; i < c; i++) {
				System.out.print("0 ");
			}
			return;
		}

		long prevSum[] = new long[actC - 1];
		long sum = 0;
		for (int i = 0; i < actC - 1; i++) {
			for (int j = i + 1; j < actC; j++) {
				prevSum[i] += spheres[i] * spheres[j] % P;
			}
			sum = (sum + prevSum[i]) % P;
		}
		System.out.print(sum + " ");
		int j;
		for (int i = 3; i <= actC; i++) {
			prevSum[0] = (sum - prevSum[0]) % P;
			if (prevSum[0] < 0)
				prevSum[0] += P;
			sum = 0;
			for (j = 1; j <= actC - i + 1; j++) {
				prevSum[j] = (prevSum[j - 1] - prevSum[j]) % P;
				if (prevSum[j] < 0)
					prevSum[j] += P;
				prevSum[j - 1] = (prevSum[j - 1] * spheres[j - 1]) % P;
				sum = (sum + prevSum[j - 1]) % P;
			}
			prevSum[j - 1] = (prevSum[j - 1] * spheres[j - 1]) % P;
			sum = (sum + prevSum[j - 1]) % P;
			System.out.print(sum + " ");
		}
		for (int i = actC; i <= c; i++) {
			System.out.print("0 ");
		}
	}
}

class NullSafeLookupMap extends HashMap<Integer, Integer> {

	public NullSafeLookupMap() {
		super();
	}

	public NullSafeLookupMap(int initialCapacity) {
		super(initialCapacity);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Integer put(Integer key) {
		Integer val = get(key);
		if (val == null)
			val = 0;
		return super.put(key, val + 1);
	}
}