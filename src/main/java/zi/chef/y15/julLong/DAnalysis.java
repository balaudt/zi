package zi.chef.y15.julLong;
import edu.uci.ics.jung.graph.DelegateTree;
import zi.common.GraphUtil;

public class DAnalysis {

	static int vc = 0, ec = 0;
	static DelegateTree<Vertex, Integer> tree;

	public static void main(String[] args) {
		int n = 4;
		int leaves = (int) Math.pow(2, n);
		tree = new DelegateTree<Vertex, Integer>();
		Vertex root = new Vertex(vc++, 0, leaves - 1);
		tree.setRoot(root);
		iterate(root);
		GraphUtil.printTree(tree);
	}

	static void iterate(Vertex v) {
		if (v.st == v.ed)
			return;
		int it = v.ed - v.st;
		Vertex left = new Vertex(vc++, v.st, v.st + it / 2);
		tree.addChild(ec++, v, left);
		Vertex right = new Vertex(vc++, v.ed - it / 2, v.ed);
		tree.addChild(ec++, v, right);
		iterate(left);
		iterate(right);
	}
}

class Vertex {
	int num, st, ed;

	public Vertex(int num, int st, int ed) {
		super();
		this.num = num;
		this.st = st;
		this.ed = ed;
	}

	@Override
	public String toString() {
		return num+"";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ed;
		result = prime * result + num;
		result = prime * result + st;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vertex other = (Vertex) obj;
		if (ed != other.ed)
			return false;
		if (num != other.num)
			return false;
		if (st != other.st)
			return false;
		return true;
	}

}