package zi.chef.y15.decLong;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintStream;

public class GChecker {

	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader("/Users/balaudt/Temp/dec/G-gen.in"));
				System.setOut(new PrintStream("/Users/balaudt/Temp/dec/G-gen-cor.out"));
		int t = Integer.parseInt(reader.readLine());
		long sevenPows[] = new long[21];
		sevenPows[0] = 1;
		for (int i = 1; i < sevenPows.length; i++) {
			sevenPows[i] = sevenPows[i - 1] * 7;
		}
		for (int i = 0; i < t; i++) {
			String aLen = reader.readLine();
			long a = Long.parseLong(aLen, 7);
			long b = Long.parseLong(reader.readLine(), 7);
			int l = Integer.parseInt(reader.readLine());
			long c = a / b;
			if (l > 20)
				System.out.println(Long.toString(c, 7));
			else
				System.out.println(Long.toString(c % sevenPows[l], 7));
		}
		reader.close();
	}
}