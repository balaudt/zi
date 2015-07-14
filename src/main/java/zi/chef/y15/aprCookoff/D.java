package zi.chef.y15.aprCookoff;

import java.io.BufferedReader;
import java.io.FileReader;

//TODO Debug WA
public class D {
	public static final String FOLDER_ROOT = "C:/ft/12/";

	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader(FOLDER_ROOT + "D-sample.in"));
		// BufferedReader reader = new BufferedReader(new
		// InputStreamReader(System.in));
		int t = Integer.parseInt(reader.readLine());
		for (int i = 0; i < t; i++) {
			int n = Integer.parseInt(reader.readLine());
			String[] inStr = reader.readLine().split(" ");
			int a = Integer.parseInt(inStr[0]);
			int b = Integer.parseInt(inStr[1]);
			int x, y;
			inStr = reader.readLine().split(" ");
			x = a * Integer.parseInt(inStr[0]);
			y = b * Integer.parseInt(inStr[1]);
			long sum = 0;
			int xsum = x, ysum = y, xdiff, ydiff;
			for (int j = 1; j < n; j++) {
				inStr = reader.readLine().split(" ");
				x = a * Integer.parseInt(inStr[0]);
				y = b * Integer.parseInt(inStr[1]);
				xdiff = Math.abs(j * x - xsum);
				ydiff = Math.abs(j * y - ysum);
				xsum += x;
				ysum += y;
				sum += xdiff > ydiff ? xdiff : ydiff;
				System.out.printf("xdiff=%d,ydiff=%d,xsum=%d,ysum=%d,sum=%d\n", xdiff, ydiff, xsum, ysum, sum);
			}
			System.out.println(sum);
		}
		reader.close();
	}

}
