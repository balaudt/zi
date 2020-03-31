package zi.leet.hard;

import java.util.Arrays;

/**
 * @author balamurugan
 */
public class BookShelf {
	private int[] memo;
	private int[][] books;
	private int width, n;

	public int minHeightShelves(int[][] books, int shelf_width) {
		this.books = books;
		width = shelf_width;
		n = books.length;
		memo = new int[n];
		Arrays.fill(memo, -1);
		int result = minHeight(0);
		System.out.println(Arrays.toString(memo));
		return result;
	}

	private int minHeight(int st) {
		if (st == n)
			return 0;
		if (memo[st] != -1)
			return memo[st];
		int curWidth = books[st][0];
		int bookInd = st + 1;
		int maxHeight = books[st][1];
		int t;
		int result = books[st][1] + minHeight(bookInd);

		while (bookInd < n) {
			curWidth += books[bookInd][0];
			if (curWidth <= width) {
				t = maxHeight;
				maxHeight = Math.max(books[bookInd][1], maxHeight);
				result = Math.min(result, Math.min(maxHeight + minHeight(bookInd + 1), t + minHeight(bookInd)));
				bookInd++;
			} else {
				break;
			}
		}

		memo[st] = result;
		return result;
	}
}
