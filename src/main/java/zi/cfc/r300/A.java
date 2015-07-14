package zi.cfc.r300;

import java.io.BufferedReader;
import java.io.InputStreamReader;

//TODO Debug WA
public class A {

	public static final String FOLDER_ROOT = "C:/ft/19/";

	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		// BufferedReader reader = new BufferedReader(new FileReader(FOLDER_ROOT
		// + "A-sample.in"));
		System.out.println(reader.readLine().matches(".*C.*O.*D.*E.*F.*O.*R.*C.*E.*S.*") ? "YES" : "NO");
		reader.close();
	}
}
