package zi.jam.y18.r2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author balamurugan
 */
public class ChainsawJugglers {
	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(reader.readLine());
		for (int i = 0; i < t; i++) {
			List<Integer> rb = Arrays.stream(reader.readLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());
			int r = rb.get(0), b = rb.get(1);
			int numBalls = 1, pos = 0;
			boolean isAnyFound = true;
			while (isAnyFound) {
				isAnyFound = false;
				for (int j = 0; j <= numBalls; j++) {
					if (r >= j && b >= (numBalls - j)) {
						pos++;
						r -= j;
						b -= numBalls - j;
						isAnyFound = true;
					}
				}
				numBalls++;
			}
			System.out.println(String.format("Case #%d: %d", i + 1, pos));
		}
	}
}
