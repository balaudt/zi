package zi.chef.y15.augLong;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class A {

	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(reader.readLine());
		String inStr[];
		for (int i = 0; i < t; i++) {
			inStr = reader.readLine().split(" ");
			int a = Integer.parseInt(inStr[0]);
			int b = Integer.parseInt(inStr[1]);
			int c = 0;
			while ((a & a - 1) != 0) {
				a /= 2;
				c++;
			}
			if (a > b)
				while (a != b) {
					a /= 2;
					c++;
				}
			else
				while (a != b) {
					a *= 2;
					c++;
				}
			System.out.println(c);
		}
		reader.close();
	}
}
