package zi.cfc.r304;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

//TODO - Debug WA
public class C {
	public static final String FOLDER_ROOT = "C:/ft/25/";

	public static void main(String[] args) throws Exception {
//		Scanner scanner = new Scanner(new File(FOLDER_ROOT + "C-sample.in"));
		 Scanner scanner = new Scanner(System.in);
		scanner.nextInt();
		int k = scanner.nextInt();
		Stack p1 = new Stack();
		for (int i = 0; i < k; i++) {
			p1.push(scanner.nextByte());
		}
		k = scanner.nextByte();
		Stack p2 = new Stack();
		for (int i = 0; i < k; i++) {
			p2.push(scanner.nextByte());
		}
		Set<Long> playStates = new HashSet<Long>();
		playStates.add(p1.number + (p2.number >> 36));
		long playCount = 0;
		while (true) {
			byte p1Num = p1.pop();
			byte p2Num = p2.pop();
			if (p1Num > p2Num) {
				p1.push(p2Num);
				p1.push(p1Num);
			} else {
				p2.push(p1Num);
				p2.push(p2Num);
			}
			playCount++;
			if (p1.count == 0) {
				System.out.println(playCount + " 2");
				break;
			} else if (p2.count == 0) {
				System.out.println(playCount + " 1");
				break;
			} else if (!playStates.add(p1.number + (p2.number >> 36))) {
				System.out.println("-1");
				break;
			}
		}
		scanner.close();
	}
}

class Stack {
	long number;

	byte count;

	void push(byte num) {
		long no = num;
		no <<= count * 4;
		number |= no;
		count++;
	}

	byte pop() {
		byte no = (byte) (number & 0b1111);
		number >>= 4;
		count--;
		return no;
	}
}