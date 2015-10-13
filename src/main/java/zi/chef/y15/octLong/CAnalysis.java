package zi.chef.y15.octLong;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import cern.colt.Arrays;

public class CAnalysis {

	public static void main(String[] args) throws Exception {
		float hourAng = 0, angDiff;
		int hour = 0, min = 0, minAng = 0;
		MultiMap<Float, int[]> lkup = new MultiMap<Float, int[]>() {
			public String toString() {
				Iterator<Entry<Float, List<int[]>>> i = map.entrySet().iterator();
				if (!i.hasNext())
					return "{}";

				StringBuilder sb = new StringBuilder();
				sb.append('{');
				for (;;) {
					Entry<Float, List<int[]>> e = i.next();
					Float key = e.getKey();
					Iterator<int[]> it = e.getValue().iterator();
					sb.append(key);
					sb.append('=');
					sb.append('[');
					for (;;) {
						int[] ele = it.next();
						sb.append(Arrays.toString(ele));
						if (!it.hasNext()) {
							sb.append(']');
							break;
						}
						sb.append(',').append(' ');
					}
					if (!i.hasNext())
						return sb.append('}').toString();
					sb.append(',').append(' ');
				}
			}
		};
		while (hourAng < 360) {
			hourAng += 0.5;
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
			System.out.println(hour + "\t" + min + "\t" + hourAng + "\t" + minAng + "\t" + angDiff);
		}
	}
}
