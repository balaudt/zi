package zi.cfc.r308;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Random;

public class DGen {

	static final String FOLDER = "/home/bala/temp/6/";

	public static void main(String[] args) throws Exception {
		BufferedWriter writer = new BufferedWriter(new FileWriter(FOLDER + "D-gen.in"));
		writer.write("2000\n");
		Random random = new Random();
		for (int i = 0; i < 2000; i++) {
			int r = random.nextInt(128);
			if (random.nextBoolean())
				r *= -1;
			writer.write(r + " ");
			r = random.nextInt(128);
			if (random.nextBoolean())
				r *= -1;
			writer.write(r + "\n");
		}
		writer.close();
	}
}
