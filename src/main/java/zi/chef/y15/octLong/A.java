package zi.chef.y15.octLong;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class A {

	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(reader.readLine());
		for (int i = 0; i < t; i++) {
			int n = Integer.parseInt(reader.readLine());
			String[] inStr = reader.readLine().split(" ");
			long ans = 0;
			int last = Integer.parseInt(inStr[0]), ct = 1, curCt = 1, cur;
			while (ct < n) {
				cur = Integer.parseInt(inStr[ct]);
				if (cur >= last) {
					curCt++;
				} else {
					ans += (long) curCt * (curCt + 1) / 2;
					curCt = 1;
				}
				last = cur;
				ct++;
			}
			ans += (long) curCt * (curCt + 1) / 2;
			System.out.println(ans);
		}
	}
}
