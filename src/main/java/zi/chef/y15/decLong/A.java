package zi.chef.y15.decLong;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class A {

	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(reader.readLine()), m;
		long n1, n2, ne;
		String inStr[];
		for (int i = 0; i < t; i++) {
			inStr = reader.readLine().split(" ");
			n1 = Long.parseLong(inStr[0]);
			n2 = Long.parseLong(inStr[1]);
			m = Integer.parseInt(inStr[2]);
			ne = (long) m * (m + 1) / 2;
			if (ne < n1 && ne < n2) {
				System.out.println((n1 - ne) + (n2 - ne));
			} else {
				System.out.println(Math.abs(n1 - n2));
			}
		}
		reader.close();
	}
}
