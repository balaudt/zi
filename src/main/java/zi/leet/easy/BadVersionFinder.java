package zi.leet.easy;

/**
 * @author balamurugan
 */
public class BadVersionFinder extends VersionControl {
	public int firstBadVersion(int n) {
		if (n == 1) return 1;
		long l = 1L, r = n, mid;
		while (l < r) {
			if (r == l + 1) return isBadVersion((int) l) ? (int) l : (int) r;
			mid = (l + r) / 2;
			if (isBadVersion((int) mid))
				r = mid;
			else
				l = mid;
		}
		return -1;
	}
}

class VersionControl {
	boolean isBadVersion(int n) {
		return n >= 1702766719;
	}
}
