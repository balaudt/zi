package zi.leet.mock.microsoft;

import java.util.HashSet;
import java.util.Set;

/**
 * @author balamurugan
 */
public class FloodFill {
	private int[][] image;
	private int newColor, R, C, oldColor;

	public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
		this.image = image;
		this.newColor = newColor;
		R = image.length;
		C = image[0].length;
		oldColor = image[sr][sc];
		recurseFill(sr, sc, new HashSet<>());
		return image;
	}

	private void recurseFill(int r, int c, Set<Integer> visited) {
		int cell = r * C + c;
		if (visited.contains(cell)) return;
		visited.add(cell);
		if (image[r][c] != oldColor) return;
		image[r][c] = newColor;
		if (r > 0) recurseFill(r - 1, c, visited);
		if (c > 0) recurseFill(r, c - 1, visited);
		if (r < R - 1) recurseFill(r + 1, c, visited);
		if (c < C - 1) recurseFill(r, c + 1, visited);
	}
}
