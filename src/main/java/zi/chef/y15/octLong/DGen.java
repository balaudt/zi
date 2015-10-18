package zi.chef.y15.octLong;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Random;

import org.apache.commons.lang3.text.StrBuilder;

public class DGen {

	static final String FOLDER = "C:/ft/37/";

	public static void main(String[] args) throws Exception {
		BufferedWriter writer = new BufferedWriter(new FileWriter(FOLDER + "D-gen.in"));
		int n = 16, m = 16;
		Random random = new Random();
		n += random.nextInt(16);
		m += random.nextInt(16);
		writer.write("1\n");
		writer.write(n + " 400 " + m + "\n");
		StrBuilder builder = new StrBuilder();
		for (int i = 0; i < n; i++) {
			int a = random.nextInt((int) 1e9);
			if (random.nextBoolean())
				builder.append('-');
			builder.append(a).append(' ');
		}
		builder.deleteCharAt(builder.length() - 1).append('\n');
		writer.write(builder.toString());
		for (int i = 0; i < m; i++) {
			int l = random.nextInt(n) + 1;
			int r = random.nextInt(n - l + 1) + l;
			int c = random.nextInt(200);
			writer.write(new StrBuilder().append(l).append(' ').append(r).append(' ').append(c).append('\n').toString());
		}
		writer.close();
	}
}
