package zi.jam.y15.r1A;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;

import org.apache.commons.lang3.text.StrBuilder;

import com.google.common.primitives.Ints;

public class B {

	public static final String FOLDER_ROOT = "C:/ft/1/";

	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader(FOLDER_ROOT + "B-small-attempt3.in"));
		BufferedWriter writer = new BufferedWriter(new FileWriter(FOLDER_ROOT + "B-small-attempt3.out"));
		int t = Integer.parseInt(reader.readLine());
		for (int i = 0; i < t; i++) {
			String[] inStr = reader.readLine().split(" ");
			int b = Integer.parseInt(inStr[0]);
			int n = Integer.parseInt(inStr[1]);
			inStr = reader.readLine().split(" ");
			int[] m = new int[b];
			int totalTime = 0;
			for (int j = 0; j < inStr.length; j++) {
				m[j] = Integer.parseInt(inStr[j]);
				totalTime += m[j];
			}

			int fN = n / totalTime;
			int fM = n % totalTime;

			int[] timeToNextCust = Arrays.copyOf(m, b);
			int custServiced = b, foundIndex = 0;
			if (fN > 0 && fM != 0) {
				custServiced = fN * totalTime;
				for (int j = 0; j < timeToNextCust.length; j++) {
					timeToNextCust[j] -= totalTime % timeToNextCust[j];
				}
			}

			if (fM != 0) {
				boolean found = false;
				while (!found) {
					int min = Ints.min(timeToNextCust);
					int currentFreeCount = 0;
					for (int j = 0; j < timeToNextCust.length; j++) {
						timeToNextCust[j] -= min;
						if (timeToNextCust[j] == 0) {
							currentFreeCount++;
							if (custServiced + currentFreeCount == n) {
								found = true;
								foundIndex = j;
								break;
							}
							timeToNextCust[j] = m[j];
						}
					}
					custServiced += currentFreeCount;
				}
			} else {
				foundIndex = b - 1;
			}

			StrBuilder builder = new StrBuilder("Case #").append(i + 1).append(": ").append(foundIndex + 1).append('\n');
			String builderStr = builder.toString();
			System.out.print(builderStr);
			writer.write(builderStr);
		}
		reader.close();
		writer.close();
	}
}
