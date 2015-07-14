package zi.euler;

//Already completed
public class FifthPowers30 {

	public static void main(String[] args) {
		int allSum = 0;
		for (int i = 10001; i < 1000000; i++) {
			char[] cs = new Integer(i).toString().toCharArray();
			int sum = 0;
			for (int j = 0; j < cs.length; j++) {
				sum += new Double(Math.pow(cs[j] - '0', 5)).intValue();
			}
			if (sum == i) {
				System.out.println(i);
				allSum += sum;
			}
		}
		System.out.println(allSum);
	}
}
