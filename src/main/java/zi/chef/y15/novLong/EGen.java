package zi.chef.y15.novLong;

import edu.uci.ics.jung.algorithms.generators.random.BarabasiAlbertGenerator;
import edu.uci.ics.jung.graph.DelegateForest;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.util.Pair;
import zi.common.archive.GraphUtil;

import java.util.HashSet;

public class EGen {

	static int vc = 0;
	static int ec = 0;

	public static void main(String[] args) {
		BarabasiAlbertGenerator<Integer, Integer> generator = new BarabasiAlbertGenerator<>(DelegateForest::new, () -> vc++, () -> ec++, 5, 5, new HashSet<>());
		generator.evolveGraph(19);
		Graph<Integer, Integer> graph = generator.get();
		//		GraphUtil.printTree(graph, new TreeLayout<Integer, Integer>((Forest<Integer, Integer>) graph));
		GraphUtil.printTree(graph);

		//		System.setOut(new PrintStream("/Users/balaudt/Temp/E-gen.in"));
		System.out.println("1");
		System.out.println(graph.getVertexCount() + " " + graph.getEdgeCount());
		for (int i = 0; i < graph.getEdgeCount(); i++) {
			Pair<Integer> pair = graph.getEndpoints(i);
			System.out.println((pair.getFirst() + 1) + " " + (pair.getSecond() + 1));
		}
	}
}
