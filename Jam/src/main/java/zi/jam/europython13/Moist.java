package zi.jam.europython13;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

import org.apache.commons.lang3.text.StrBuilder;

public class Moist {

	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader("C:/ft/8/A-small-practice-2.in"));
		BufferedWriter writer = new BufferedWriter(new FileWriter("C:/ft/8/A-small-practice-2.out"));
		int t = Integer.parseInt(reader.readLine());
		for (int i = 0; i < t; i++) {
			int n = Integer.parseInt(reader.readLine());
			StrBuilder builder = new StrBuilder("Case #").append(i + 1).append(": ");
			int count = 0;
			String[] arr = new String[n];
			for (int j = 0; j < n; j++) {
				arr[j] = reader.readLine();
			}
			for (int j = 1; j < n; j++) {
				int k = 0;
				while (arr[k].compareTo(arr[j]) <= 0 && k < j) {
					k++;
				}
				if (k != j) {
					String temp = arr[j];
					for (int l = j; l >= k + 1; l--) {
						arr[l] = arr[l - 1];
					}
					arr[k] = temp;
					count++;
				}
			}
			builder.append(count).append("\n");
//			System.out.println(builder.toString());
			writer.write(builder.toString());
		}
		reader.close();
		writer.close();
	}
}
