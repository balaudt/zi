package zi.chef.y15.augLong;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang3.text.StrBuilder;

import edu.uci.ics.jung.graph.UndirectedSparseGraph;
import zi.common.archive.GraphUtil;

public class GGen {

	public static void main(String[] args) throws Exception {
		execute();
	}

	public static void generate() throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader("C:/ft/200/G-in-in.in"));
		BufferedWriter writer = new BufferedWriter(new FileWriter("C:/ft/200/G-gen.in"));
		writer.write("1\n");
		StrBuilder builder = new StrBuilder();
		Random random = new Random();
		int l = random.nextInt(5) + 1;
		builder.append(15).append(' ').append(l).append(' ').append(random.nextInt(15 - l) + l).append('\n');
		writer.write(builder.toString());
		String line = null;
		while ((line = reader.readLine()) != null) {
			builder.clear().append(line).append(' ').append(random.nextInt(50)).append('\n');
			writer.write(builder.toString());
		}
		reader.close();
		writer.close();
	}

	public static void execute() throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader("C:/ft/200/G-gen.in"));
		UndirectedSparseGraph<GGVert, Edge> graph = new UndirectedSparseGraph<>();
		Integer.parseInt(reader.readLine());
		String[] inStr = reader.readLine().split(" ");
		int n = Integer.parseInt(inStr[0]);
		byte l = Byte.parseByte(inStr[1]);
		byte r = Byte.parseByte(inStr[2]);
		Map<Integer, GGVert> vertices = new HashMap<>();
		for (int i = 0; i < n; i++) {
			GGVert ggVert = new GGVert(i);
			graph.addVertex(ggVert);
			vertices.put(i, ggVert);
		}
		for (int i = 0; i < n - 1; i++) {
			inStr = reader.readLine().split(" ");
			int a = Integer.parseInt(inStr[0]) - 1;
			int b = Integer.parseInt(inStr[1]) - 1;
			int c = Integer.parseInt(inStr[2]);
			graph.addEdge(new Edge(i, c), vertices.get(a), vertices.get(b));
		}
		/*
		 * for (int i = 0; i < inStr.length; i++) { Collection<GGVert> vs =
		 * graph.getVertices(); for (GGVert ggVert : vs) {
		 * 
		 * } curRoot=i; dfsVisit(i); }
		 */
		GraphUtil.printTree(graph);
		reader.close();
	}

	static int curRoot;

	static void dfsVisit(int v) {

	}
}

class GGVert {
	int id;
	Color c;

	public GGVert(int id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		GGVert other = (GGVert) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return id + "";
	}
}

class Edge {
	int id;
	int w;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		Edge other = (Edge) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public Edge(int id, int w) {
		super();
		this.id = id;
		this.w = w;
	}

	@Override
	public String toString() {
		return w + "";
	}

}
