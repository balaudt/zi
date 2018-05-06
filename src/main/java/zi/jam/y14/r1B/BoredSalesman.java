package zi.jam.y14.r1B;

import edu.uci.ics.jung.algorithms.layout.KKLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.UndirectedSparseMultigraph;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BoredSalesman {
	static Graph<City, Integer> cityMap;
	static Set<City> visitedCities;
	static Map<Integer, City> index;
	static Map<String, City> pinIndex;
	static StringBuilder cityBuilder;
	static Set<String> citiesByParents;

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("C-small-attempt0.bin"));
		BufferedWriter writer = new BufferedWriter(new FileWriter("C-small-attempt0.out"));
		int t = Integer.parseInt(reader.readLine());
		for (int i = 0; i < t; i++) {
			String[] inps = reader.readLine().split(" ");
			int n = Integer.parseInt(inps[0]);
			int m = Integer.parseInt(inps[1]);
			cityMap = new UndirectedSparseMultigraph<City, Integer>();
			index = new HashMap<>();
			pinIndex = new HashMap<>();
			int edgeCount = 0;
			for (int j = 0; j < n; j++) {
				String pin = reader.readLine();
				City city = new City(pin);
				cityMap.addVertex(city);
				pinIndex.put(pin, city);
				index.put(j, city);
			}
			for (int j = 0; j < m; j++) {
				inps = reader.readLine().split(" ");
				cityMap.addEdge(edgeCount++, index.get(Integer.parseInt(inps[0]) - 1), index.get(Integer.parseInt(inps[1]) - 1));
			}
			// displayGraph(cityMap);
			visitedCities = new HashSet<>();
			cityBuilder = new StringBuilder();
			citiesByParents = new HashSet<>();
			List<String> pins = new ArrayList<>(pinIndex.keySet());
			Collections.sort(pins);
			travel(pinIndex.get(pins.get(0)));
			writer.write(new StringBuilder("Case #").append(i+1).append(": ").append(cityBuilder.toString()).append('\n').toString());
			System.out.println(cityBuilder.toString());
		}
		reader.close();
		writer.close();
	}

	static void travel(City node) {
		visitedCities.add(node);
		cityBuilder.append(node.pin);
		Collection<City> neighbors = cityMap.getNeighbors(node);
		ArrayList<String> pins = new ArrayList<String>();
		for (City neighbour : neighbors) {
			if (visitedCities.contains(neighbour) || citiesByParents.contains(neighbour.pin)) {
				continue;
			}
			pins.add(neighbour.pin);
		}
		Collections.sort(pins);
		citiesByParents.addAll(pins);
		for (String pin : pins) {
			City anotherCity = pinIndex.get(pin);
			travel(anotherCity);
		}
	}

	static <V, E> void displayGraph(Graph<V, E> graph) {
		Layout<V, E> layout = new KKLayout<V, E>(graph);
		layout.setSize(new Dimension(300, 300));
		BasicVisualizationServer<V, E> vv = new BasicVisualizationServer<V, E>(layout);
		vv.setPreferredSize(new Dimension(350, 350));
		vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());

		JFrame frame = new JFrame("Graph");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(vv);
		frame.pack();
		frame.setVisible(true);
	}
}

class City {
	public City(String pin) {
		this.pin = pin;
	}

	String pin;

	@Override
	public String toString() {
		return pin;
	}
}
