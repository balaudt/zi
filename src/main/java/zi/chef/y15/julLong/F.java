package zi.chef.y15.julLong;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class F {
	static final String FOLDER = "/home/bala/temp/8/";
	static double k1pk2;
	static double k1psqk2;

	public static void main(String[] args) throws Exception {
		//		BufferedReader reader = new BufferedReader(new FileReader(FOLDER + "F-sample.in"));
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(reader.readLine());
		String[] inStr = reader.readLine().split(" ");
		int n0 = Integer.parseInt(inStr[0]);
		long p = Integer.parseInt(inStr[1]);
		int m = Integer.parseInt(inStr[2]);
		int b = Integer.parseInt(inStr[3]);
		inStr = reader.readLine().split(" ");
		double k1 = -1 / Double.parseDouble(inStr[0]);
		double k2 = 1 / Double.parseDouble(inStr[1]);
		k1pk2 = k1 + k2;
		k1psqk2 = k1 * k1 + k2 * k2;

		double k3 = 1 / Double.parseDouble(inStr[2]);
		double k4 = 1 / Double.parseDouble(inStr[3]);
		List<Integer> ns = new ArrayList<>(t);
		int n = (int) ((p * n0) % m + b);
		ns.add(n);
		for (int i = 1; i < t; i++) {
			n = (int) ((p * n) % m + b);
			ns.add(n);
		}
		double rsum = 0, lastRsum = 0;
		Collections.sort(ns);
		int lastN = 4;
		double lastK3 = k3, lastK4 = k4, temp;
		for (int num : ns) {
			if (num <= 4) {
				switch (num) {
				case 1:
					rsum -= 1 / k1;
					break;
				case 2:
					rsum += 1 / k2;
					break;
				case 3:
					rsum += 1 / k3;
					break;
				case 4:
					rsum += 1 / k4;
					break;
				}
				continue;
			}
			for (int i = lastN + 1; i <= num; i++) {
				temp = lastK4;
				lastK4 = nextRadius(k1, k2, lastK3, lastK4);
				lastK3 = temp;
			}
			lastN = num;
			rsum += 1 / lastK4;
			if (rsum - lastRsum < 1e-8)
				break;
			else
				lastRsum = rsum;
		}
		System.out.format("%.6f", rsum);
		reader.close();
	}

	static double nextRadius(double k1, double k2, double k3, double k4) {
		double ksum = k1pk2 + k4;
		double ksqsum = k1psqk2 + k4 * k4;
		double b = 2 * ksum;
		double c = ksum * ksum - 2 * ksqsum;
		double temp1 = Math.sqrt(b * b + 4 * c);
		double root1 = (b - temp1) / 2;
		double root2 = (b + temp1) / 2;
		if (Math.abs(root1 - k3) > Math.abs(root2 - k3)) {
			return root1;
		} else {
			return root2;
		}
	}
}
