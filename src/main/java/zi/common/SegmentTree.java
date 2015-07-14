package zi.common;

import java.awt.Dimension;
import java.lang.reflect.Array;

import javax.swing.JFrame;

import org.apache.commons.collections15.Transformer;

import edu.uci.ics.jung.algorithms.layout.FRLayout2;
import edu.uci.ics.jung.graph.DelegateTree;
import edu.uci.ics.jung.visualization.RenderContext;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position;

public abstract class SegmentTree<N> {
	public N[] getNodes() {
		return nodes;
	}

	protected N[] nodes;
	protected int maxsize;
	protected Class<N> clazz;
	private DelegateTree<N, Integer> drawTree;

	protected int leftchild(int pos) {
		return 2 * pos + 1;
	}

	protected int rightchild(int pos) {
		return 2 * pos + 2;
	}

	protected int mid(int start, int end) {
		return (start + (end - start) / 2);
	}

	@SuppressWarnings("unchecked")
	public SegmentTree(Class<N> clazz, int size) throws Exception {
		this.clazz = clazz;
		int height = (int) (Math.ceil(Math.log(size) / Math.log(2)));
		maxsize = (int) Math.pow(2, height + 1);
		nodes = (N[]) Array.newInstance(clazz, maxsize + 1);
		maxsize /= 2;
		maxsize--;
	}

	public SegmentTree(Class<N> clazz, N[] leaves) throws Exception {
		this(clazz, leaves.length);
		System.arraycopy(leaves, 0, nodes, maxsize, leaves.length);
		initialize(0, maxsize, 0);
	}

	protected N initialize(int l, int r, int ind) throws Exception {
		if (l == r) {
			return initialize(ind);
		}
		N thisNode = initialize(ind);
		int mid = mid(l, r);
		N leftNode = initialize(l, mid, leftchild(ind));
		N rightNode = initialize(mid + 1, r, rightchild(ind));
		nodes[ind] = merge(leftNode, rightNode, thisNode);
		return nodes[ind];
	}

	abstract protected N merge(N leftNode, N rightNode, N parentNode);

	protected N initialize(int ind) throws Exception {
		return clazz.newInstance();
	}

	public void draw() {
		if (drawTree == null) {
			drawTree = new DelegateTree<N, Integer>();
			drawTree.setRoot(nodes[0]);
			int depth = 1, idx = 1;
			int ec = 0;
			while (idx <= maxsize) {
				int end = (int) (Math.pow(2, depth)) + idx - 1;
				while (idx <= end) {
					drawTree.addChild(ec++, nodes[(idx - 1) / 2], nodes[idx]);
					idx++;
				}
				depth++;
			}
		}
		printTree();
	}

	protected void printTree() {
		VisualizationViewer<N, Integer> vv = new VisualizationViewer<N, Integer>(new FRLayout2<N, Integer>(drawTree),
				new Dimension(1100, 640));
		//		BasicVisualizationServer<N, Integer> vs = new BasicVisualizationServer<N, Integer>(new FRLayout2<N, Integer>(drawTree),
		//				new Dimension(1100, 640));
		RenderContext<N, Integer> renderContext = vv.getRenderContext();
		Transformer<Integer, String> transformer = new ToStringLabeller<Integer>();
		renderContext.setEdgeLabelTransformer(transformer);
		Transformer<N, String> vertexTransformer = new ToStringLabeller<N>();
		renderContext.setVertexLabelTransformer(vertexTransformer);
		vv.getRenderer().getVertexLabelRenderer().setPosition(Position.CNTR);
		JFrame frame = new JFrame();
		frame.getContentPane().add(vv);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

}
