package zi.jam.y14.r1A;

import java.util.Random;

public class ADataGenerator {

	public static void main(String[] args) {
		Random random=new Random();
		for (int i = 0; i < 150; i++) {
			System.out.print(Long.toBinaryString(random.nextLong()).substring(0, 40)+' ');
		}
		System.out.println();
		for (int i = 0; i < 150; i++) {
			System.out.print(Long.toBinaryString(random.nextLong()).substring(0, 40)+' ');
		}
	}
}
