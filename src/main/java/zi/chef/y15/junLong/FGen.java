package zi.chef.y15.junLong;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Random;

import org.apache.commons.lang3.text.StrBuilder;

public class FGen {
	static final String FOLDER = "C:/ft/33/";

	public static void main(String[] args) throws Exception {
		BufferedWriter writer = new BufferedWriter(new FileWriter(FOLDER + "F-gen.in"));
		writer.write("500\n");
		Random random = new Random();
		for (int i = 0; i < 500; i++) {
			int n = random.nextInt(10) + 11;
			writer.write(n + "\n");
			char out[][] = new char[n][n];
			for (int j = 0; j < n; j++) {
				for (int k = 0; k <= j; k++) {
					if (j == k) {
						out[j][j] = '0';
						continue;
					}
					out[j][k] = out[k][j] = random.nextBoolean() ? '1' : '0';
				}
			}
			for (int j = 0; j < n; j++) {
				StrBuilder builder = new StrBuilder();
				for (int k = 0; k < n; k++) {
					builder.append(out[j][k]).append(' ');
				}
				builder.deleteCharAt(builder.length() - 1).append('\n');
				writer.write(builder.toString());
			}
		}
		writer.close();
	}

}
