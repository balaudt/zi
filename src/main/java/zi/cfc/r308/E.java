package zi.cfc.r308;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class E {
	static final String FOLDER = "/home/bala/temp/6/";

	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(new File(FOLDER + "E-sample.in"));
		// Scanner scanner = new Scanner(System.in);
		char[] cs = scanner.nextLine().toCharArray();
		List<Integer> nums = new ArrayList<Integer>();
		boolean lastPlus = false;
		for (int i = 0; i < cs.length; i++) {
			if (cs[i] == '+') {
				lastPlus = true;
			} else if (cs[i] == '*')
				lastPlus = false;
			else {
				if (!lastPlus)
					nums.add(cs[i] - '0');
				else {
					Integer lastNum = nums.remove(nums.size() - 1);
					nums.add(lastNum + cs[i] - '0');
				}
			}
		}
		System.out.println(nums);
		scanner.close();
	}

}
