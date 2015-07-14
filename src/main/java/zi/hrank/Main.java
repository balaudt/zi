package zi.hrank;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

//TODO Debug
public class Main {
	static final String FOLDER = "/home/bala/temp/7/";
	static final int P = 1000000007;
	static long bc[][];

	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(new File(FOLDER + "F-sample.in"));
		// Scanner scanner = new Scanner(System.in);
		String[] inStr = scanner.nextLine().split(" ");
		int n = Integer.parseInt(inStr[0]);
		bc = new long[n + 3][4];
		bc[0][0] = bc[0][1] = 1;
		bc[1][1] = 2;
		bc[1][0] = bc[1][2] = 1;
		for (int i = 2; i <= n + 2; i++) {
			bc[i][0] = 1;
			for (int j = 1; j <= 3; j++) {
				bc[i][j] = (bc[i - 1][j] + bc[i - 1][j - 1]) % P;
			}
		}
		for (int i = 0; i < bc.length; i++) {
			System.out.print(bc[i][3] + ",");
		}
		System.out.println();
		int q = Integer.parseInt(inStr[1]);
		ArrayList<Query> queries = new ArrayList<Query>(q);
		for (int i = 0; i < q; i++) {
			inStr = scanner.nextLine().split(" ");
			int st = Integer.parseInt(inStr[1]);
			int end = Integer.parseInt(inStr[2]);
			if (inStr[0].equals("1")) {
				queries.add(new Query(st, end));
			} else {
				int ans = 0;
				for (Query query : queries) {
					if (query.end < st || query.st > end)
						continue;
					int actEnd = query.end > end ? end : query.end;
					int actSt = query.st > st ? query.st : st;
					long temp = bc[actEnd - actSt + 2][3] * 2;
					if (st > query.st) {
						temp -= bc[st - query.st + 1][3] * 2 + ans;
					}
					temp += ans;
					ans = (int) (temp % P);
				}
				System.out.println(ans);
			}
		}
		scanner.close();
	}
}

class Query {
	int st, end;

	public Query(int st, int end) {
		this.st = st;
		this.end = end;
	}

}