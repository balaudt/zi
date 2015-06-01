package zi.jam.y15.r1B;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;

import org.apache.commons.lang3.text.StrBuilder;
import org.paukov.combinatorics.Factory;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;

public class B {

	public static final String FOLDER_ROOT = "C:/ft/20/";

	static int r, c, n;

	static int[] pos;

	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader(FOLDER_ROOT + "B-small-attempt0.in"));
		BufferedWriter writer = new BufferedWriter(new FileWriter(FOLDER_ROOT + "B-small-attempt0.out"));
		int t = Integer.parseInt(reader.readLine());
		for (int i = 0; i < t; i++) {
			String[] inStr = reader.readLine().split(" ");
			r = Integer.parseInt(inStr[0]);
			c = Integer.parseInt(inStr[1]);
			n = Integer.parseInt(inStr[2]);

			Integer[] cells = new Integer[r * c];
			for (int j = 0; j < cells.length; j++) {
				cells[j] = j;
			}
			ICombinatoricsVector<Integer> originalVector = Factory.createVector(cells);
			Generator<Integer> gen = Factory.createSimpleCombinationGenerator(originalVector, n);
			int minNoise = Integer.MAX_VALUE;
			for (ICombinatoricsVector<Integer> perm : gen) {
				List<Integer> vector = perm.getVector();
				pos = new int[n];
				int count = 0;
				for (Integer num : vector) {
					pos[count++] = num;
				}
				int currentNoise = getNoiseValue();
				if (currentNoise < minNoise)
					minNoise = currentNoise;
				if (minNoise == 0)
					break;
			}

			StrBuilder builder = new StrBuilder("Case #").append(i + 1).append(": ");
			builder.append(minNoise).append('\n');
			String builderStr = builder.toString();
			System.out.print(builderStr);
			writer.write(builderStr);
		}
		reader.close();
		writer.close();
	}

	static int getNoiseValue() {
		boolean[][] cells = new boolean[r][c];
		for (int i = 0; i < pos.length; i++) {
			int rowVal = pos[i] / c;
			int colVal = pos[i] % c;
			cells[rowVal][colVal] = true;
		}
		int noiseLevel = 0;
		for (int i = 0; i < r - 1; i++) {
			for (int j = 0; j < c - 1; j++) {
				if (cells[i][j] && cells[i][j + 1])
					noiseLevel++;
				if (cells[i][j] && cells[i + 1][j])
					noiseLevel++;
			}
		}
		for (int i = 0; i < c - 1; i++) {
			if (cells[r - 1][i] && cells[r - 1][i + 1])
				noiseLevel++;
		}
		for (int i = 0; i < r - 1; i++) {
			if (cells[i][c - 1] && cells[i + 1][c - 1])
				noiseLevel++;
		}
		return noiseLevel;
	}
}
