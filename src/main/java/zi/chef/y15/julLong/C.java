package zi.chef.y15.julLong;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class C{
	static final String FOLDER = "/home/bala/temp/8/";

	public static void main(String[] args) throws Exception {
//		BufferedReader reader = new BufferedReader(new FileReader(FOLDER + "C-sample.in"));
				BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(reader.readLine());
		String[] inStr;
		for (int i = 0; i < t; i++) {
			inStr = reader.readLine().split(" ");
			long k = Long.parseLong(inStr[1]);
			inStr = reader.readLine().split(" ");
			long pkgCount = 1;
			long curBread = k;
			for (int j = 0; j < inStr.length; j++) {
				long toEat = Long.parseLong(inStr[j]);
				if (toEat > curBread) {
					long reqd = toEat - curBread;
					if (reqd % k == 0) {
						curBread += reqd;
						pkgCount += reqd / k;
					} else {
						long addPkg = (reqd / k) + 1;
						pkgCount += addPkg;
						curBread += addPkg * k;
					}
				}
				curBread -= toEat;
				if (curBread != 0) {
					curBread--;
				}
			}
			System.out.println(pkgCount);
		}
		reader.close();
	}

}
