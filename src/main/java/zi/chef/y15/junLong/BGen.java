package zi.chef.y15.junLong;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Random;

import org.apache.commons.lang3.text.StrBuilder;

public class BGen {

	static final String FOLDER = "C:/ft/33/";

	public static void main(String[] args) throws Exception {
		BufferedWriter writer = new BufferedWriter(new FileWriter(FOLDER + "B-gen.in"));
		writer.write("5\n");
		Random random = new Random();
		for (int i = 0; i < 5; i++) {
			int n = random.nextInt(10) + 2;
			writer.write(n + "\n");
			for (int j = 0; j < n; j++) {
				int poly = random.nextInt(10) + 3;
				writer.write(poly + "\n");
				StrBuilder builder = new StrBuilder();
				for (int k = 0; k < poly; k++) {
					builder.append(random.nextInt(31)).append(' ').append(random.nextInt(31)).append(' ');
				}
				writer.write(builder.deleteCharAt(builder.length() - 1).append('\n').toString());
			}
		}
		writer.close();
	}
}
