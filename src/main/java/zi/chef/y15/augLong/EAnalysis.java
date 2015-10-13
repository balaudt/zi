package zi.chef.y15.augLong;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EAnalysis {

	public static void main(String[] args) {
		int n = 12;
		ArrayList<N> a = new ArrayList<>(n);
		for (int i = 0; i < n; i++) {
			N num = new N();
			num.n = i;
			a.add(num);
		}
		Collections.shuffle(a);
		for (int i = 0; i < n - 1; i++) {
			for (int j = i + 1; j < n; j++) {
				a.get(max(a, i, j)).c++;
			}
		}
		System.out.println(a);
	}

	static int max(List<N> a, int l, int r) {
		int maxInd = l;
		for (int i = l + 1; i <= r; i++) {
			if (a.get(i).n > a.get(maxInd).n)
				maxInd = i;
		}
		return maxInd;
	}
}

class N {
	int n, c;

	@Override
	public String toString() {
		return "N [n=" + n + ", c=" + c + "]";
	}

}
