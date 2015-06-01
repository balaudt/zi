package zi.jam.y14.rAChina;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class B {

	public static final String FOLDER_ROOT = "C:/ft/10/";

	public static void main(String[] args) throws Exception {
//		BufferedReader reader = new BufferedReader(new FileReader(FOLDER_ROOT + "B-sample.in"));
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String str = reader.readLine();
		long ans = Long.parseLong(str.replaceAll("4", "0").replaceAll("7", "1"), 2);
		int length = str.length();
		ans += Math.pow(2, length) - 1;
		System.out.println(ans);
	}
}
