package zi.chef.y15.junLong;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Random;

public class CGen {
	static final String FOLDER = "C:/ft/33/";

	public static void main(String[] args) throws Exception {
		BufferedWriter writer = new BufferedWriter(new FileWriter(FOLDER + "C-gen.in"));
		writer.write("10\n");
		Random random=new Random();
		for (int i = 0; i < 10; i++) {
			writer.write((random.nextInt(5000)+10)+" 5000\n");
		}
		writer.close();
	}

}
