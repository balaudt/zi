package zi.chef.y15.decLong;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;

public class CGen {

	public static void main(String[] args) throws Exception {
		BufferedWriter writer = new BufferedWriter(new FileWriter("/Users/balaudt/Temp/dec/C-gen-1.in"));
		int t = 14, d = 4;
		writer.write(t + "\n");
		Random random = new Random();
		for (int i = 0; i < t; i++) {
			int n = random.nextInt(d) + (t - d);
			int m = random.nextInt(d) + (t - d);
			writer.write(n + "\n");
			for (int j = 0; j < n; j++) {
				writer.write(RandomStringUtils.random(m, "ab") + "\n");
			}
		}
		writer.close();
	}
}
