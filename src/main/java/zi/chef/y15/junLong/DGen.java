package zi.chef.y15.junLong;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Random;

public class DGen {

	static final String FOLDER = "C:/ft/33/";

	public static void main(String[] args) throws Exception {
		BufferedWriter writer = new BufferedWriter(new FileWriter(FOLDER + "D-gen.in"));
		writer.write("100000\n");
		Random random = new Random();
		for (int i = 0; i < 100000; i++) {
			writer.write((random.nextInt(1000000000) + 1) + " ");
			writer.write((random.nextInt(1000000000) + 1) + " ");
			writer.write(random.nextInt(1000000000) + " ");
			writer.write(random.nextInt(1000000000) + "\n");
		}
		writer.close();
	}
}
