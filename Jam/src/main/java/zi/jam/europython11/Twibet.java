package zi.jam.europython11;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.text.StrBuilder;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

public class Twibet {

	static Multimap<Integer, Integer> followerList;
	static Set<Integer> monksWhispered;

	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader("C:/ft/7/D-large-practice.in"));
		BufferedWriter writer = new BufferedWriter(new FileWriter("C:/ft/7/D-large-practice.out"));
		int t = Integer.parseInt(reader.readLine());
		for (int i = 0; i < t; i++) {
			int n = Integer.parseInt(reader.readLine());
			String[] inFollows = reader.readLine().split(" ");
			followerList = ArrayListMultimap.<Integer, Integer> create();
			StrBuilder builder = new StrBuilder("Case #").append(i + 1).append(":\n");
			for (int j = 0; j < inFollows.length; j++) {
				followerList.put(Integer.parseInt(inFollows[j]), j + 1);
			}
			for (int j = 1; j <= n; j++) {
				monksWhispered = new HashSet<Integer>();
				follows(j);
				builder.append(monksWhispered.size());
				builder.append("\n");
			}
			// System.out.println(builder.toString());
			writer.write(builder.toString());
		}
		reader.close();
		writer.close();
	}

	static void follows(Integer monk) {
		if (monksWhispered.contains(monk)) {
			return;
		}
		monksWhispered.add(monk);
		Collection<Integer> followers = followerList.get(monk);
		for (Integer anotherMonk : followers) {
			follows(anotherMonk);
		}
	}
}
