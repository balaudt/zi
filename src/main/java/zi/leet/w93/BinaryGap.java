package zi.leet.w93;

/**
 * @author balamurugan
 */
public class BinaryGap {
	public int binaryGap(int N) {
		char[] binaryRep = Integer.toBinaryString(N).toCharArray();
		int lastPos = 0, max = 0;
		for (int i = 1; i < binaryRep.length; i++) {
			if (binaryRep[i] == '1') {
				if ((i - lastPos) > max) max = i - lastPos;
				lastPos = i;
			}
		}
		return max;
	}

	public static void main(String[] args) {
		BinaryGap binaryGap = new BinaryGap();
		System.out.println(binaryGap.binaryGap(22));
		System.out.println(binaryGap.binaryGap(5));
		System.out.println(binaryGap.binaryGap(6));
		System.out.println(binaryGap.binaryGap(8));
	}
}
