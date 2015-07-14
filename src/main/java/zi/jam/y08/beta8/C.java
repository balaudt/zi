package zi.jam.y08.beta8;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.text.StrBuilder;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.util.EdgeType;

public class C {

	public static final String FOLDER_ROOT = "C:/ft/16/";

	static Graph<String, Integer> graph;
	static Map<Integer, Integer> roadTimeMap;
	static Multimap<String, List<Integer>> shortestRoutes;
	static Map<String, Integer> shortestRouteLengths;

	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader(FOLDER_ROOT + "C-large-practice.in"));
		BufferedWriter writer = new BufferedWriter(new FileWriter(FOLDER_ROOT + "C-large-practice.out"));
		int t = Integer.parseInt(reader.readLine());
		for (int i = 0; i < t; i++) {
			// Initializations
			String[] inStr = reader.readLine().split(" ");
			int n = Integer.parseInt(inStr[0]);
			String src = inStr[1];
			graph = new DirectedSparseMultigraph<String, Integer>();
			roadTimeMap = new HashMap<Integer, Integer>();
			shortestRouteLengths = new HashMap<String, Integer>();
			shortestRoutes = ArrayListMultimap.<String, List<Integer>> create();
			for (int j = 0; j < n; j++) {
				inStr = reader.readLine().split(" ");
				String c1 = inStr[0];
				String c2 = inStr[1];
				if (!graph.containsVertex(c1))
					graph.addVertex(c1);
				if (!graph.containsVertex(c2))
					graph.addVertex(c2);
				graph.addEdge(j, c1, c2, EdgeType.DIRECTED);
				roadTimeMap.put(j, Integer.parseInt(inStr[2]));
			}
//			GraphUtility.print(graph);

			// Algorithm execution
			shortestRouteLengths.put(src, 0);
			shortestRoutes.put(src, new ArrayList<Integer>());
			execute(src, new HashSet<String>(), new ArrayList<Integer>(), 0);

			// Probability calculation
			Set<String> reachableNodes = shortestRoutes.keySet();
			double cityProb = 1.0 / (reachableNodes.size() - 1);
			Map<Integer, Double> answer = new HashMap<Integer, Double>();
			for (String reachableNode : reachableNodes) {
				if(reachableNode.equals(src))
				{
					continue;
				}
				Collection<List<Integer>> shortRoutes = shortestRoutes.get(reachableNode);
				double routeProb = 1.0 / shortRoutes.size();
				double cSrP = cityProb * routeProb;
				for (List<Integer> route : shortRoutes) {
					for (Integer road : route) {
						Double prob = answer.get(road);
						if (prob == null)
							answer.put(road, cSrP);
						else
							answer.put(road, cSrP + prob);
					}
				}
			}

			// Output
			StrBuilder builder = new StrBuilder("Case #").append(i + 1).append(": ");
			for (int j = 0; j < n; j++) {
				if (answer.containsKey(j))
					builder.append(String.format("%.7f",answer.get(j)));
				else
					builder.append("0.0000000");
				builder.append(' ');
			}
			builder.append('\n');
			String builderStr = builder.toString();
			System.out.print(builderStr);
			writer.write(builderStr);
		}
		reader.close();
		writer.close();
	}

	static void execute(String node, Set<String> currentPath, List<Integer> currentTrace, int currentTime) {
		Collection<Integer> outEdges = graph.getOutEdges(node);
		for (Integer outEdge : outEdges) {
			String dest = graph.getDest(outEdge);
			if (currentPath.contains(dest))
				continue;
			Integer curTime = currentTime + roadTimeMap.get(outEdge);
			HashSet<String> curPath = new HashSet<String>(currentPath);
			curPath.add(dest);
			ArrayList<Integer> curTrace = new ArrayList<Integer>(currentTrace);
			curTrace.add(outEdge);
			if (shortestRouteLengths.containsKey(dest)) {
				Integer prevShortRouteLength = shortestRouteLengths.get(dest);
				if (prevShortRouteLength > curTime) {
					shortestRouteLengths.put(dest, curTime);
					shortestRoutes.removeAll(dest);
					shortestRoutes.put(dest, curTrace);
				} else if (prevShortRouteLength == curTime) {
					shortestRoutes.put(dest, curTrace);
				}
			} else {
				shortestRoutes.put(dest, curTrace);
				shortestRouteLengths.put(dest, curTime);
			}
			execute(dest, curPath, curTrace, curTime);
		}
	}
}
