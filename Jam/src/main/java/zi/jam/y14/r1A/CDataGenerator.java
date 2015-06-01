package zi.jam.y14.r1A;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import com.google.common.primitives.Ints;

public class CDataGenerator {

	public static void main(String[] args) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter("C-sample.bin"));
		BufferedWriter eWriter = new BufferedWriter(new FileWriter("C-sample-e.out"));
		Random random = new Random();
		int[] data = new int[1000];
		writer.write("120\n");
		for (int i = 0; i < 120; i++) {
			boolean isGood = random.nextBoolean();
			if (isGood) {
				eWriter.write(new StringBuilder().append("Case #").append(i + 1).append(": GOOD\n").toString());
			} else {
				eWriter.write(new StringBuilder().append("Case #").append(i + 1).append(": BAD\n").toString());
			}
			for (int j = 0; j < 1000; j++) {
				data[j] = j ;
			}
			for (int j = 0; j < 999; j++) {
				int ind = 0;
				if (isGood) {
					ind = random.nextInt(999 - j) + j;
				} else {
					ind = random.nextInt(999);
				}
				int temp = data[j];
				data[j] = data[ind];
				data[ind] = temp;
			}
			writer.write("1000\n");
			writer.write(Ints.join(" ", data) + "\n");
		}
		writer.close();
		eWriter.close();
	}

}
