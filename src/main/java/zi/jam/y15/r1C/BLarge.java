package zi.jam.y15.r1C;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang3.text.StrBuilder;

public class BLarge {
	public static final String FOLDER_ROOT = "C:/ft/24/";

	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader(FOLDER_ROOT + "B-large.in"));
		BufferedWriter writer = new BufferedWriter(new FileWriter(FOLDER_ROOT + "B-large.out"));
		int t = Integer.parseInt(reader.readLine());
		for (int i = 0; i < t; i++) {
			String[] inStr = reader.readLine().split(" ");
			int k = Integer.parseInt(inStr[0]);
			int l = Integer.parseInt(inStr[1]);
			int s = Integer.parseInt(inStr[2]);

			char[] keysC = reader.readLine().toCharArray();
			Map<Character, Integer> keyFreq = new HashMap<Character, Integer>();
			for (int j = 0; j < keysC.length; j++) {
				if (!keyFreq.containsKey(keysC[j])) {
					keyFreq.put(keysC[j], 0);
				}
				keyFreq.put(keysC[j], keyFreq.get(keysC[j]) + 1);
			}
			Map<Character, Double> charProb = new HashMap<Character, Double>();
			Set<Entry<Character, Integer>> entrySet = keyFreq.entrySet();
			for (Entry<Character, Integer> entry : entrySet) {
				charProb.put(entry.getKey(), (double) entry.getValue() / k);
			}

			String targetStr = reader.readLine();

			StrBuilder tarBuilder = new StrBuilder();
			while (tarBuilder.length() < s)
				tarBuilder.append(targetStr);
			int maxCount = getCount(tarBuilder.substring(0, s), targetStr);

			double p = 1;
			char[] targetChars = targetStr.toCharArray();
			for (int j = 0; j < targetChars.length; j++) {
				Double pc = charProb.get(targetChars[j]);
				if (pc == null) {
					p = 0;
					maxCount = 0;
					break;
				}
				p *= pc;
			}
			p *= (double) (s - l + 1);

//			System.out.println(maxCount);
//			System.out.println(p);

			StrBuilder builder = new StrBuilder("Case #").append(i + 1).append(": ").append(maxCount - p).append('\n');
			String builderStr = builder.toString();
			System.out.print(builderStr);
			writer.write(builderStr);
		}
		reader.close();
		writer.close();
	}

	static int getCount(String str, String targetStr) {
//		System.out.println(str + "\t" + targetStr);
		int length = str.length();
		int tarLength = targetStr.length();
		int count = 0;
		for (int i = 0; i < length - tarLength + 1; i++) {
			if (str.substring(i, i + tarLength).equals(targetStr)) {
				count++;
			}
		}
		return count;
	}
}
