package zi.chef.y15.julLong;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class B {
	static final String FOLDER = "/home/bala/temp/8/";

	public static void main(String[] args) throws Exception {
//		BufferedReader reader = new BufferedReader(new FileReader(FOLDER + "B-sample.in"));
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		reader.readLine();
		String[] inStr = reader.readLine().split(" ");
		for (int i = 0; i < inStr.length; i++) {
			int num = Integer.parseInt(inStr[i]);
			int origNum = num;
			int fiveCount = 0;
			while (num % 5 == 0) {
				fiveCount++;
				num /= 5;
			}
			while (num % 2 == 0 && fiveCount > 0) {
				num /= 2;
				fiveCount--;
			}
			if (fiveCount > 0) {
				if (fiveCount % 2 != 0)
					fiveCount++;
				long mult = 1;
				for (int j = 0; j < fiveCount; j++) {
					mult *= 2;
				}
				System.out.println(mult * origNum);
			} else
				System.out.println(origNum);
		}
		reader.close();
	}

}
