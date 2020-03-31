package zi.leet.easy;

/**
 * @author balamurugan
 */
public class ReaderTester extends Reader {
	private int ind = 0;
	private char[] in;
	private boolean testEof = false;

	public ReaderTester(String in) {
		this.in = in.toCharArray();
	}

	@Override
	int read4(char[] buf) {
		if (testEof) return 0;
		int min = Math.min(4, in.length - ind);
		System.arraycopy(in, ind, buf, 0, min);
		if (min < 4) testEof = true;
		ind += 4;
		return min;
	}
}
