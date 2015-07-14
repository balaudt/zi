package zi.jam.europython13;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.text.StrBuilder;

public class BadHorse {

	static Set<String> g1;
	static Set<String> g2;
	static List<String[]> procrastedPairs;

	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader("C:/ft/8/C-small-practice-1.in"));
		BufferedWriter writer = new BufferedWriter(new FileWriter("C:/ft/8/C-small-practice-1.out"));
		int t = Integer.parseInt(reader.readLine());
		for (int i = 0; i < t; i++) {
			int m = Integer.parseInt(reader.readLine());
			StrBuilder builder = new StrBuilder("Case #").append(i + 1).append(": ");
			g1 = new HashSet<String>();
			g2 = new HashSet<String>();
			procrastedPairs = new ArrayList<String[]>();
			boolean isPossible = true;
			String[] troulePair = reader.readLine().split(" ");
			g1.add(troulePair[0]);
			g2.add(troulePair[1]);
			for (int j = 1; j < m; j++) {
				troulePair = reader.readLine().split(" ");
				if(!isPossible)
				{
					continue;
				}
				if (!something(true, troulePair)) {
					isPossible = false;
				}
			}
			for (String[] troublePair : procrastedPairs) {
				something(false, troublePair);
			}
			if (isPossible) {
				builder.append("Yes\n");
			} else {
				builder.append("No\n");
			}
//			System.out.println(builder.toString());
			writer.write(builder.toString());
		}
		reader.close();
		writer.close();
	}

	private static boolean something(boolean doProcrastinate, String[] troulePair) {
		String p1 = troulePair[0];
		String p2 = troulePair[1];
		if (g1.contains(p1)) {
			if (g1.contains(p2)) {
				return false;
			} else {
				if (!g2.contains(p2)) {
					g2.add(p2);
				}
			}
		} else if (g2.contains(p1)) {
			if (g2.contains(p2)) {
				return false;
			} else {
				if (!g1.contains(p2)) {
					g1.add(p2);
				}
			}
		} else {
			if (g1.contains(p2)) {
				g2.add(p1);
			} else if (g2.contains(p2)) {
				g1.add(p1);
			} else {
				if (doProcrastinate) {
					procrastedPairs.add(troulePair);
				} else {
					g1.add(p1);
					g2.add(p2);
				}
			}
		}
		return true;
	}
}
