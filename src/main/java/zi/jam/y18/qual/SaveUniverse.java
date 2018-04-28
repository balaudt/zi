package zi.jam.y18.qual;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author balamurugan
 */
public class SaveUniverse {
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(reader.readLine());
		for (int i = 0; i < t; i++) {
			String[] in = reader.readLine().split(" ");
			char[] p = in[1].toCharArray();
			int d = Integer.parseInt(in[0]);
			boolean possible = true;
			int minSwaps = 0;
			while (true) {
				int damage = 0, power = 1;
				for (char pc : p) {
					if (pc == 'S') {
						damage += power;
					} else {
						power *= 2;
					}
				}
				if (damage <= d) {
					break;
				}
				boolean swapPossible = false;
				for (int j = 0; j < p.length - 1; j++) {
					if (p[j] == 'C' && p[j + 1] == 'S') {
						p[j] = 'S';
						p[j + 1] = 'C';
						minSwaps++;
						swapPossible = true;
						break;
					}
				}
				if (!swapPossible) {
					possible = false;
					break;
				}
			}
			if (possible) {
				System.out.println(String.format("Case #%d: %d", i + 1, minSwaps));
			} else {
				System.out.println(String.format("Case #%d: %s", i + 1, "IMPOSSIBLE"));
			}
		}
	}
}
