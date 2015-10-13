package chef.octlong;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Random;

public class EGen {

	public static void main(String[] args) throws Exception {
		BufferedWriter inWriter = new BufferedWriter(new FileWriter("C:/ft/E-gen-large.in"));
		int t = 1000000;
		inWriter.write(t + "\n");
		Random random = new Random();
		for (int i = 0; i < t; i++) {
			inWriter.write(random.nextInt((int) 5e6) + 1 + "\n");
		}
		inWriter.close();
	}
}
