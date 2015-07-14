package zi.cfc.r308;
import java.util.Scanner;

public class A {
	static final String FOLDER = "/home/bala/temp/6/";

	public static void main(String[] args) throws Exception {
//		Scanner scanner = new Scanner(new File(FOLDER + "A-sample.in"));
		 Scanner scanner = new Scanner(System.in);
		int n = Integer.parseInt(scanner.nextLine());
		int mat[][] = new int[100][100];
		for (int i = 0; i < n; i++) {
			String[] inStr = scanner.nextLine().split(" ");
			int tly = Integer.parseInt(inStr[0]) - 1;
			int tlx = Integer.parseInt(inStr[1]) - 1;
			int bry = Integer.parseInt(inStr[2]) - 1;
			int brx = Integer.parseInt(inStr[3]) - 1;
			for (int j = tly; j <= bry; j++) {
				for (int k = tlx; k <= brx; k++) {
					mat[j][k]++;
				}
			}
		}
		long ans=0;
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				ans+=mat[i][j];
			}
		}
		System.out.println(ans);
		scanner.close();
	}

}
