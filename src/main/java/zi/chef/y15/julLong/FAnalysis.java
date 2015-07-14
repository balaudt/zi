package zi.chef.y15.julLong;
import cern.colt.Arrays;

public class FAnalysis {

	public static void main(String[] args) {
		double k1 = -1 / (double) 6;
		double k2 = 1 / (double) 3;
		double k3 = 1 / (double) 2;
		double ksum = k1 + k2 + k3;
		double ksqsum = k1 * k1 + k2 * k2 + k3 * k3;
		System.out.println(Arrays.toString(roots(-1.0, 2 * ksum, ksum * ksum - 2 * ksqsum)));
	}

	static double[] roots(double a, double b, double c) {
		double temp1 = Math.sqrt(b * b - 4 * a * c);
		double root1 = (-b + temp1) / (2 * a);
		double root2 = (-b - temp1) / (2 * a);
		return new double[] { root1, root2 };
	}
}
