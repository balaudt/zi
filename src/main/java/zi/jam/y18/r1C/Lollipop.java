package zi.jam.y18.r1C;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * @author balamurugan
 */
public class Lollipop {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int t = scanner.nextInt();
		for (int i = 0; i < t; i++) {
			int n = scanner.nextInt();
			Set<Integer> soldFlavours = new HashSet<>();
			Map<Integer, Integer> frequency = new HashMap<>();
			for (int j = 0; j < n; j++) {
				int favCount = scanner.nextInt();
				int[] favs = new int[favCount];
				for (int k = 0; k < favCount; k++) {
					favs[k] = scanner.nextInt();
					int customerFav = favs[k];
					if (!soldFlavours.contains(customerFav)) {
						if (frequency.containsKey(customerFav)) {
							frequency.put(customerFav, frequency.get(customerFav) + 1);
						} else {
							frequency.put(customerFav, 1);
						}
					}
				}
				int leastFav = n + 1, selFlavour = -1;
				for (int k = 0; k < favCount; k++) {
					int customerFav = favs[k];
					if (!soldFlavours.contains(customerFav)) {
						Integer favFreq = frequency.get(customerFav);
						if (favFreq < leastFav) {
							leastFav = favFreq;
							selFlavour = customerFav;
						}
					}
				}
				if (selFlavour != -1) {
					soldFlavours.add(selFlavour);
					System.out.println(selFlavour);
				} else {
					System.out.println(-1);
				}
			}

		}

	}
}
