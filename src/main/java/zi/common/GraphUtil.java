package zi.common;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import org.apache.commons.collections15.Transformer;

import edu.uci.ics.jung.algorithms.layout.KKLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import edu.uci.ics.jung.visualization.RenderContext;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position;

public class GraphUtil {

    public static <V, E> void printTree(Graph<V, E> tree) {
        printTree(tree, null);
    }

    public static <V, E> void printTree(Graph<V, E> tree, Layout<V, E> layout) {
        if (layout == null)
            layout = new KKLayout<V, E>(tree);
        BasicVisualizationServer<V, E> vs = new BasicVisualizationServer<V, E>(layout, new Dimension(1100, 640));
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

    public static <V, Ed> JFrame visualize(Layout<V, Ed> layout, Transformer<V, Paint> vertexFill) {
        VisualizationViewer<V, Ed> vv = new VisualizationViewer<V, Ed>(layout);
        vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<>());
        vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller<>());
        DefaultModalGraphMouse<V, Ed> gm = new DefaultModalGraphMouse<>();
        gm.setMode(ModalGraphMouse.Mode.TRANSFORMING);
        vv.setGraphMouse(gm);
        if (vertexFill != null)
            vv.getRenderContext().setVertexFillPaintTransformer(vertexFill);

        JFrame frame = new JFrame();
        frame.getContentPane().add(vv);
//        frame.pack();
        frame.setSize(30000,500);
        frame.setVisible(true);

        /*Container c = frame.getContentPane();
        BufferedImage image = new BufferedImage(c.getWidth(), c.getHeight(), BufferedImage.TYPE_INT_ARGB);
        c.printAll(image.createGraphics());
        try {
            ImageIO.write(image, "PNG", new File("/Users/balaudt/Temp/qq.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        return frame;

		/*GraphVisualizer graphVisualizer = new GraphVisualizer();
		graphVisualizer.vvholder.add(vv);
		graphVisualizer.pack();
		graphVisualizer.setVisible(true);
		return graphVisualizer;*/
    }

}
