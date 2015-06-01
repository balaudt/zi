package zi.jam;

import java.awt.Dimension;

import javax.swing.JFrame;

import org.apache.commons.collections15.Transformer;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;

public class GraphUtility {

	public static <V, E> void print(Graph<V, E> g) {
		Layout<V, E> layout = new CircleLayout<V, E>(g);
		layout.setSize(new Dimension(300, 300));
		BasicVisualizationServer<V, E> vv = new BasicVisualizationServer<V, E>(layout);
		vv.setPreferredSize(new Dimension(350, 350));
		vv.getRenderContext().setVertexLabelTransformer(new Transformer<V, String>() {

			public String transform(V arg0) {
				return arg0.toString();
			}
		});
		vv.getRenderContext().setEdgeLabelTransformer(new Transformer<E, String>() {

			public String transform(E arg0) {
				return arg0.toString();
			}
		});
		JFrame frame = new JFrame("Simple Graph View");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(vv);
		frame.pack();
		frame.setVisible(true);
	}
}
