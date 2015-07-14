package zi.jam.y15.qual;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.text.StrBuilder;

public class B {

	public static final String FOLDER_ROOT = "C:/ft/1/";

	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader(FOLDER_ROOT + "B-small-attempt0.in"));
		BufferedWriter writer = new BufferedWriter(new FileWriter(FOLDER_ROOT + "B-small-attempt0.out"));
		int t = Integer.parseInt(reader.readLine());
		for (int i = 0; i < t; i++) {
			StrBuilder builder = new StrBuilder("Case #").append(i + 1).append(": ");
			int d = Integer.parseInt(reader.readLine());
			String[] inStr = reader.readLine().split(" ");
			Integer[] diners = new Integer[d];
			for (int j = 0; j < diners.length; j++) {
				diners[j] = Integer.parseInt(inStr[j]);
			}
			List<Integer> dinersList = Arrays.asList(diners);
			builder.append(min(dinersList));
			String builderStr = builder.append('\n').toString();
			System.out.print(builderStr);
			writer.write(builderStr);
		}
		reader.close();
		writer.close();
	}

	static int min(List<Integer> dinersList) {
		Collections.sort(dinersList);
		int size = dinersList.size();
		Integer maxNoOfCakes = dinersList.get(size - 1);
		if (maxNoOfCakes == 1) {
			return 1;
		}
		List<Integer> specialMinList = new ArrayList<Integer>(dinersList);
		int splitCakes = maxNoOfCakes / 2;
		specialMinList.set(size - 1, splitCakes);
		specialMinList.add(maxNoOfCakes - splitCakes);
		List<Integer> ordinaryMinList = new ArrayList<Integer>(dinersList);
		for (int i = 0; i < size; i++) {
			ordinaryMinList.set(i, ordinaryMinList.get(i) - 1);
		}
		ordinaryMinList.removeAll(Arrays.asList(0));
		int ordinaryTime = 1 + min(ordinaryMinList);
		int specialTime = 1 + min(specialMinList);
		boolean isSpecial = ordinaryTime < specialTime;
		int minTime = isSpecial ? ordinaryTime : specialTime;
		return minTime;
	}
}
