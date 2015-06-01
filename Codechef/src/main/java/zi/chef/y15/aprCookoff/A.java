package zi.chef.y15.aprCookoff;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class A {

	public static final String FOLDER_ROOT = "C:/ft/12/";

	public static void main(String[] args) throws Exception {
//		BufferedReader reader = new BufferedReader(new FileReader(FOLDER_ROOT + "A-sample.in"));
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(reader.readLine());
		for (int i = 0; i < t; i++) {
			String[] inStr = reader.readLine().split(" ");
			int n = Integer.parseInt(inStr[0]);
			int m = Integer.parseInt(inStr[1]);
			int k = Integer.parseInt(inStr[2]);
			Integer[] ignored = new Integer[m];
			Integer[] tracked = new Integer[k];
			inStr = reader.readLine().split(" ");
			for (int j = 0; j < ignored.length; j++) {
				ignored[j] = Integer.parseInt(inStr[j]);
			}
			inStr = reader.readLine().split(" ");
			for (int j = 0; j < tracked.length; j++) {
				tracked[j] = Integer.parseInt(inStr[j]);
			}
			List<Integer> ignoredList = Arrays.asList(ignored);
			List<Integer> trackedList = Arrays.asList(tracked);
			int c1 = 0, c2 = 0;
			for (int j = 1; j <= n; j++) {
				if (ignoredList.contains(j) && trackedList.contains(j))
					c1++;
				else if (!ignoredList.contains(j) && !trackedList.contains(j))
					c2++;
			}
			System.out.println(c1 + " " + c2);
		}
//		reader.close();
	}

}
