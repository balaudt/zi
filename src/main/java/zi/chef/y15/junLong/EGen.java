package zi.chef.y15.junLong;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import org.apache.commons.lang3.RandomStringUtils;
import org.paukov.combinatorics.Factory;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;

public class EGen {

	static final String FOLDER = "C:/ft/33/";

	public static void main(String[] args) throws Exception {
		BufferedWriter writer = new BufferedWriter(new FileWriter(FOLDER + "E-gen.in"));
		writer.write("1\n500 100000\n");
		char[] out = new char[500];
		Random random = new Random();
		for (int i = 0; i < 500; i++) {
			out[i] = (char) ('a' + random.nextInt(26));
		}
		writer.write(new String(out) + "\n");
		for (int i = 0; i < 100000; i++) {
			if (random.nextBoolean())
				writer.write((random.nextInt(500) + 1) + "\n");
			else
				writer.write((random.nextInt(100000) + 1) + "\n");
		}
		writer.close();
	}

	public static void gen2(String[] args) throws Exception {
		BufferedWriter writer = new BufferedWriter(new FileWriter(FOLDER + "E-gen-2.in"));
		writer.write("1\n6 10\n");
		writer.write(RandomStringUtils.randomAlphabetic(6) + '\n');
		Random random = new Random();
		for (int i = 0; i < 10; i++) {
			writer.write((random.nextInt(6) + 1) + "\n");
		}
		writer.close();
	}

	public static void bruteForce(String[] args) throws Exception {
		Scanner scanner = new Scanner(new File(FOLDER + "E-gen-2.in"));
		int t = Integer.parseInt(scanner.nextLine());
		for (int i = 0; i < t; i++) {
			String[] inStr = scanner.nextLine().split(" ");
			Integer.parseInt(inStr[0]);
			int q = Integer.parseInt(inStr[1]);
			String in = scanner.nextLine();
			int len = in.length();
			ArrayList<String> subStringSets = new ArrayList<String>();
			for (int j = 0; j < len; j++) {
				for (int k = j + 1; k < len + 1; k++) {
					subStringSets.add(in.substring(j, k));
				}
			}
			ICombinatoricsVector<String> factoryVector = Factory.createVector(subStringSets);
			int k;
			for (int j = 0; j < q; j++) {
				k = Integer.parseInt(scanner.nextLine());
				if (k > factoryVector.getSize()) {
					System.out.println("0");
					continue;
				}
				Generator<String> generator = Factory.createSimpleCombinationGenerator(factoryVector, k);
				int sum = 0;
				for (ICombinatoricsVector<String> genVector : generator) {
					List<String> vector = genVector.getVector();
					String val = vector.get(0);
					boolean isValid = true;
					for (String str : vector) {
						if (!str.equals(val)) {
							isValid = false;
							break;
						}
					}
					if (isValid)
						sum++;
				}
				System.out.println(sum);
			}
		}
		scanner.close();
	}
}
