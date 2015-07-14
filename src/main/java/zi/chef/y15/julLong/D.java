package zi.chef.y15.julLong;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class D{
	static final String FOLDER = "C:/ft/37/";

	public static void main(String[] args) throws Exception {
//		BufferedReader reader = new BufferedReader(new FileReader(FOLDER + "D-gen.in"));
						BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(reader.readLine());
		String[] inStr;
		for (int cs = 0; cs < t; cs++) {
			inStr = reader.readLine().split(" ");
			int n = Integer.parseInt(inStr[0]);
			int k = Integer.parseInt(inStr[1]);
			int m = Integer.parseInt(inStr[2]);
			int num[] = new int[n];
			long negSum[] = new long[n + 1];
			long sum = 0;
			inStr = reader.readLine().split(" ");
			for (int j = 0; j < n; j++) {
				num[j] = Integer.parseInt(inStr[j]);
				sum += num[j];
				negSum[j + 1] = negSum[j];
				if (num[j] < 0)
					negSum[j + 1] += -num[j];
			}
			SegTree segTree = new SegTree(n);
			for (int j = 0; j < m; j++) {
				inStr = reader.readLine().split(" ");
				int r = Integer.parseInt(inStr[1]) - 1;
				int l = Integer.parseInt(inStr[0]) - 1;
				short weight = Short.parseShort(inStr[2]);
				long value = negSum[r + 1] - negSum[l];
				if (value > 0 && weight <= k) {
					segTree.update(l, r, weight);
				}
			}
			//			System.out.println(Arrays.toString(segTree.tree));
			//			System.out.println(segTree.maxsize);
			//			GraphUtil.printSegTree(segTree);
			List<Item> chefs = new ArrayList<Item>();
			for (int i = 0; i < n; i++) {
				if (num[i] < 0) {
					short min = segTree.min(i);
					if (min != 0)
						chefs.add(new Item(min, -num[i]));
				}
			}
//			System.out.println(chefs);
			num = null;
			segTree = null;
			int newM = chefs.size();
			long maxRemoval[][] = new long[newM + 1][k + 1];
			for (int i = 1; i <= newM; i++) {
				for (int j = 0; j <= k; j++) {
					Item chef = chefs.get(i - 1);
					long withoutThis = maxRemoval[i - 1][j];
					if (chef.weight > j) {
						maxRemoval[i][j] = withoutThis;
						continue;
					}
					long chefValue = chef.value;
					long withThis = maxRemoval[i - 1][j - chef.weight] + chefValue;
					if (withThis > withoutThis) {
						maxRemoval[i][j] = withThis;
					} else {
						maxRemoval[i][j] = withoutThis;
					}
				}
			}
			System.out.println(sum + maxRemoval[newM][k]);
		}
		reader.close();
	}

}

class Item {
	int weight;
	long value;

	public Item(int weight, long value) {
		super();
		this.weight = weight;
		this.value = value;
	}

	@Override
	public String toString() {
		return "I[w=" + weight + ", v=" + value + "]";
	}

}

class SegTree {
	short[] tree;
	int maxsize;
	int height;

	public SegTree(int size) {
		height = (int) (Math.ceil(Math.log(size) / Math.log(2)));
		maxsize = (int) Math.pow(2, height + 1);
		tree = new short[maxsize + 1];
		maxsize /= 2;
		maxsize--;
	}

	int leftchild(int pos)

	{
		return 2 * pos + 1;
	}

	int rightchild(int pos) {
		return 2 * pos + 2;
	}

	int mid(int start, int end) {
		return (start + (end - start) / 2);
	}

	void update(int l, int r, short weight) {
		//		System.out.println(new StrBuilder("Master update[l=").append(l).append(",r=").append(r).append(",w=").append(weight)
		//				.append(']').toString());
		update(l, r, weight, 0, maxsize, 0);
	}

	//nl-node left, nr-node right
	void update(int l, int r, short weight, int nl, int nr, int ind) {
		if (l > nr || r < nl)
			return;
		//		System.out.println(new StrBuilder("Update[l=").append(l).append(",r=").append(r).append(",w=").append(weight)
		//				.append(",nl=").append(nl).append(",nr=").append(nr).append(",ind=").append(ind).append(']').toString());
		if (tree[ind] != 0 && tree[ind] <= weight)
			return;
		if (l <= nl && r >= nr) {
			tree[ind] = weight;
			return;
		}
		if (nl == nr) {
			tree[ind] = weight;
			return;
		}
		int nmid = mid(nl, nr);
		update(l, r, weight, nl, nmid, leftchild(ind));
		update(l, r, weight, nmid + 1, nr, rightchild(ind));
	}

	short min(int ind) {
		short min = min(ind, 0, maxsize, 0);
		//		System.out.println(new StrBuilder("Master min[ind=").append(ind).append(",ans=").append(min).append(']').toString());
		return min;
	}

	//nind-node index
	short min(int ind, int nl, int nr, int nind) {
		//		System.out.println(new StrBuilder("Min[ind=").append(ind).append(",nl=").append(nl).append(",nr=").append(nr)
		//				.append(",nind=").append(nind).append(']').toString());
		if (nl == nr)
			return tree[nind];
		short min = tree[nind];
		int nmid = mid(nl, nr);
		short childMin;
		if (nmid >= ind) {
			childMin = min(ind, nl, nmid, leftchild(nind));
		} else {
			childMin = min(ind, nmid + 1, nr, rightchild(nind));
		}
		if (min == 0)
			return childMin;
		else if (childMin != 0 && childMin < min)
			return childMin;
		else
			return min;
	}
}