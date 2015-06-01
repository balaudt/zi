package zi.cfc.r304;
import java.util.Scanner;

public class A {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int k = scanner.nextInt();
		int n = scanner.nextInt();
		int w = scanner.nextInt();
		int ans = k * w * (w + 1) / 2 - n;
		System.out.println(ans > 0 ? ans : 0);
		scanner.close();
	}
}
