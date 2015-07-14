package zi.jam.y14.rAChina;


import java.io.BufferedReader;
import java.io.InputStreamReader;

public class A {

	public static final String FOLDER_ROOT = "C:/ft/10/";

	public static void main(String[] args) throws Exception {
		// BufferedReader reader = new BufferedReader(new FileReader(FOLDER_ROOT
		// + "A-sample.in"));
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int in = Integer.parseInt(reader.readLine());
		String[] digits = new String[] { "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine" };
		String[] ones = new String[] { "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen",
				"nineteen" };
		String[] seconds = new String[] { "ten", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety" };
		int units = in % 10;
		int second = in / 10;
		if (second == 0)
			System.out.println(digits[units]);
		else if (second == 1)
			if (units == 0)
				System.out.println("ten");
			else
				System.out.println(ones[units - 1]);
		else {
			String out = seconds[second - 1];
			if (units != 0)
				out += "-" + digits[units];
			System.out.println(out);
		}
		reader.close();
	}

}
