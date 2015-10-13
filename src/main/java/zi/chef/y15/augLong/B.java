package zi.chef.y15.augLong;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class B {

	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(reader.readLine());
		for (int i = 0; i < t; i++) {
			char[] l1 = reader.readLine().toCharArray();
			char[] l2 = reader.readLine().toCharArray();
			int l1First = jumpCount(l1, l2);
			int l2First = jumpCount(l2, l1);
			int min = Integer.min(l1First, l2First);
			if (min == Integer.MAX_VALUE)
				System.out.println("No");
			else {
				System.out.println("Yes");
				System.out.println(min);
			}
		}
		reader.close();
	}

	static int jumpCount(char[] l1, char[] l2) {
		int i = 0;
		char[] cur = l1, oth = l2;
		int jump = 0;
		while (i < l1.length) {
			while (i < l1.length && cur[i] == '.')
				i++;
			if (i < l1.length) {
				if (oth[i] == '#')
					return Integer.MAX_VALUE;
				jump++;
				char[] t = cur;
				cur = oth;
				oth = t;
			}
		}
		return jump;
	}
}
