package zi.jam.y14.qual;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class DeceitfulWar {
	private static int n;
	private static ArrayList<Double> naomiList = new ArrayList<>();
	private static ArrayList<Double> kenList = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("D-large.in"));
		BufferedWriter writer = new BufferedWriter(new FileWriter("D-large.out"));
		int t = Integer.parseInt(reader.readLine());
		for (int i = 0; i < t; i++) {
			n = Integer.parseInt(reader.readLine());
			double[] naomi = new double[n];
			double[] ken = new double[n];
			String[] curInps = reader.readLine().split(" ");
			for (int j = 0; j < n; j++) {
				naomi[j] = Double.parseDouble(curInps[j]);
			}
			curInps = reader.readLine().split(" ");
			for (int j = 0; j < n; j++) {
				ken[j] = Double.parseDouble(curInps[j]);
			}
			Arrays.sort(naomi);
			Arrays.sort(ken);
			naomiList.clear();
			kenList.clear();
			for (int j = 0; j < n; j++) {
				naomiList.add(naomi[n - 1 - j]);
				kenList.add(ken[n - 1 - j]);
			}
			int normal = 0;
			for (int j = 0; j < n; j++) {
				Double cur = naomiList.get(j);
				// TODO Should be replaced by static values from j,n
				int kenCurSize = kenList.size();
				boolean foundFlag = false;
				for (int k = kenCurSize - 1; k >= 0; k--) {
					if (kenList.get(k) > cur) {
						foundFlag = true;
						kenList.remove(k);
						break;
					}
				}
				if (!foundFlag) {
					kenList.remove(kenCurSize - 1);
					normal++;
				}
			}
			if (normal != n) {
				naomiList.clear();
				kenList.clear();
				for (int j = 0; j < n; j++) {
					naomiList.add(naomi[n - 1 - j]);
					kenList.add(ken[n - 1 - j]);
				}
				while (naomiList.size() > 0 && !everythingWon()) {
					naomiList.remove(naomiList.size() - 1);
					kenList.remove(0);
				}
			}
			writer.write(new StringBuilder("Case #").append(i + 1).append(": ").append(naomiList.size()).append(" ").append(normal)
					.append('\n').toString());
		}
		reader.close();
		writer.close();
	}

	private static boolean everythingWon() {
		int curSize = naomiList.size();
		for (int i = 0; i < curSize; i++) {
			if (naomiList.get(i) < kenList.get(i)) {
				return false;
			}
		}
		return true;
	}
}
