package zi.common;

import java.awt.Dimension;

import javax.swing.JFrame;

import org.apache.commons.collections15.Transformer;

import edu.uci.ics.jung.algorithms.layout.KKLayout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import edu.uci.ics.jung.visualization.RenderContext;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position;

public class GraphUtil {

	public static <V, E> void printTree(Graph<V, E> tree) {
		BasicVisualizationServer<V, E> vs = new BasicVisualizationServer<V, E>(new KKLayout<V, E>(tree), new Dimension(1100, 640));
		RenderContext<V, E> renderContext = vs.getRenderContext();
		Transformer<E, String> transformer = new ToStringLabeller<E>();
		renderContext.setEdgeLabelTransformer(transformer);
		Transformer<V, String> vertexTransformer = new ToStringLabeller<V>();
		renderContext.setVertexLabelTransformer(vertexTransformer);
		vs.getRenderer().getVertexLabelRenderer().setPosition(Position.CNTR);
		JFrame frame = new JFrame();
		frame.getContentPane().add(vs);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

}
