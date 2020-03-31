package zi.leet.easy;

/**
 * @author balamurugan
 */
public abstract class Reader {
	private boolean eof;
	private int lastAvail, lastStart;
	private char[] internal;

	public Reader() {
		eof = false;
		lastAvail = 0;
		internal = new char[4];
	}

	/**
	 * @param buf Destination buffer
	 * @param n   Number of characters to read
	 * @return The number of actual characters read
	 */
	public int read(char[] buf, int n) {
		if (eof) return 0;
		int readCt = 0;
		if (lastAvail > 0) {
			System.arraycopy(internal, lastStart, buf, 0, Math.min(lastAvail, n));
			readCt = Math.min(lastAvail, n);
			if (n <= lastAvail) {
				lastAvail -= n;
				lastStart += n;
				return n;
			}
			n -= lastAvail;
			lastAvail = 0;
		}
		int calls = n / 4;
		boolean eof = false;
		for (int i = 0; i < calls; i++) {
			int currentRead = read4(internal);
			System.arraycopy(internal, 0, buf, readCt, currentRead);
			readCt += currentRead;
			if (currentRead < 4) {
				eof = true;
				break;
			}
		}
		if (eof)
			return readCt;
		if (n % 4 == 0)
			return readCt;
		int last = read4(internal), lastReqd = n % 4;
		System.arraycopy(internal, 0, buf, readCt, Math.min(last, lastReqd));
		readCt += Math.min(last, lastReqd);
		if (last > lastReqd) {
			lastAvail = last - lastReqd;
			lastStart = last - lastAvail;
		}
		return readCt;
	}

	abstract int read4(char[] buf);
}
