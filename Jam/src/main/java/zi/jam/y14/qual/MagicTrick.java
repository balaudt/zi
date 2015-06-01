package zi.jam.y14.qual;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MagicTrick {

	private static int[][][] boardData = new int[2][4][4];
	private static int[] choices = new int[2];
	private static List<Integer> chosen = new ArrayList<>(4);
	private static int[] possibleSelection;

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("A-small-attempt0.bin"));
		BufferedWriter writer = new BufferedWriter(new FileWriter("A-small-attempt0.out"));
		int t = Integer.parseInt(reader.readLine());
		for (int i = 0; i < t; i++) {
			for (int j = 0; j < 2; j++) {
				choices[j] = Integer.parseInt(reader.readLine());
				for (int k = 0; k < 4; k++) {
					String[] curInps = reader.readLine().split(" ");
					for (int l = 0; l < 4; l++) {
						boardData[j][k][l] = Integer.parseInt(curInps[l]);
					}
				}
			}
			magic();
			StringBuilder builder = new StringBuilder("Case #").append(i + 1).append(": ");
			if (chosen.isEmpty()) {
				builder.append("Volunteer cheated!");
			} else if (chosen.size() > 1) {
				builder.append("Bad magician!");
			} else {
				builder.append(chosen.get(0));
			}
			builder.append("\n");
			writer.write(builder.toString());
		}
		reader.close();
		writer.close();
	}

	private static void magic() {
		for (int i = 0; i < 4; i++) {
			possibleSelection = boardData[0][choices[0] - 1];
		}
		chosen.clear();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (boardData[1][choices[1] - 1][j] == possibleSelection[i]) {
					chosen.add(possibleSelection[i]);
					break;
				}
			}
		}
	}
}
