package zi.leet.easy;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author balamurugan
 */
public class SnapArray {
	private int version = 0;
	//index to (version to value)
	private Map<Integer, TreeMap<Integer, Integer>> elements;

	public SnapArray(int length) {
		elements = new HashMap<>(length);
	}

	public void set(int index, int val) {
		TreeMap<Integer, Integer> verionValues = elements.getOrDefault(index, new TreeMap<>());
		verionValues.put(version, val);
		elements.put(index, verionValues);
	}

	public int snap() {
		version++;
		return version-1;
	}

	public int get(int index, int snap_id) {
		TreeMap<Integer, Integer> versionValues = elements.get(index);
		if(versionValues==null) return 0;
		if(versionValues.containsKey(snap_id))
			return versionValues.get(snap_id);
		Map.Entry<Integer, Integer> lowerVersion = versionValues.lowerEntry(snap_id);
		if(lowerVersion==null) return 0;
		return lowerVersion.getValue();
	}
}
