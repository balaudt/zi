package zi.sdp.amazon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

/*
 * Retrieve the operation without any requisites. Print it. Remove it from any requisites list in other operations. Repeat until operations' list becomes empty
 * 
 * Analysis of complexity of program
 * n - input number of operations
 * Complexity of building a single operation with requisites is O(n) as we can have atmost n requisites for a single operation and each addition takes constant time for a HashSet/Map
 * Complexity of building the base set = O(n log n) as each insertion into red black tree used by TreeSet/Map is O(log n)
 * Removal operation during each main iteration would take again O(log n) [A stricter analysis would decrement n by 1 after each main iteration]
 * Atmost n nodes rebuild during each main iteration leading to O(n log n) [Again a stricter analysis would decrement n by 1]
 * So for a single main iteration, complexity will be O(n log n)
 * Total complexity=[O(n^2)+O(n log n)]+[O(n^2 log n)]
 * 		=O(n^2 log n)
 *  
 *  Space complexity is O(n^2) as the total number of requisites can be O(n^2) [O(n^2) for requisites+O(n) for op]
 *  
 * Building a dependency graph and running a topology sort can be more efficient.
 * Attempted the graph solution and was successful. Refer Solution1
 * 
 */
class FirstSolution {

	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		//		BufferedReader reader = new BufferedReader(new FileReader("/home/bala/temp/100/input001.txt"));
		String line;
		Set<Operation> operations = new TreeSet<>();
		while ((line = reader.readLine()) != null) {
			if (!line.startsWith("#")) {
				operations.add(new Operation(line));
			}
		}
		//Main iteration
		while (operations.size() > 0) {
			Operation op = operations.iterator().next();
			if (op.requisites.size() != 0) {
				break;
			}
			System.out.println(new StringBuilder("Operation ").append(op.op).append(" on part X").toString());
			operations.remove(op);
			Iterator<Operation> opIt = operations.iterator();
			//As the key comparison is dependent on requisites.size, we need to correct the node's position when requisites.size decreases.
			Set<Operation> opToRebuild = new HashSet<>();
			while (opIt.hasNext()) {
				Operation oper = opIt.next();
				if (oper.requisites.contains(op.op)) {
					oper.requisites.remove(op.op);
					opIt.remove();
					opToRebuild.add(oper);
				}
			}
			operations.addAll(opToRebuild);
		}
		reader.close();
	}
}

class Operation implements Comparable<Operation> {
	String op;
	Set<String> requisites;

	public Operation(String operation) {
		String[] in = operation.split(",");
		op = in[0];
		requisites = new HashSet<>(in.length - 1);
		for (int i = 1; i < in.length; i++) {
			requisites.add(in[i]);
		}
	}

	@Override
	public int compareTo(Operation o) {
		int diff = requisites.size() - o.requisites.size();
		if (diff != 0)
			return diff;
		return op.compareTo(o.op);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((op == null) ? 0 : op.hashCode());
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
		Operation other = (Operation) obj;
		if (op == null) {
			if (other.op != null)
				return false;
		} else if (!op.equals(other.op))
			return false;
		return true;
	}

}

/*
 * Build a graph with operations as vertices and directed edges from an operation to operation that is dependent on it.
 * Do a topology sort and print the result.
 * 
 * Analysis of complexity of program
 * n - input number of operations
 * Complexity of building the vertices map: O(n) for vertex insertions in map + O(n^2) for each requisite insertion [As atmost number of edges in the graph is O(n^2)]
 * Complexity of DFS here is again O(n^2) 
 * So total complexity is O(n^2)
 * 
 * Space complexity is similar to that of the other solution : O(n^2) [O(n^2)+O(n) for the stack = O(n^2)]
 */
public class Solution {
	static Map<String, Vertex> vertices = new HashMap<>();
	static Stack<String> result = new Stack<>();

	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		//		BufferedReader reader = new BufferedReader(new FileReader("/home/bala/temp/100/input001.txt"));
		String line;
		while ((line = reader.readLine()) != null) {
			if (!line.startsWith("#")) {
				String[] inStr = line.split(",");
				Vertex vertex = null;
				if (vertices.containsKey(inStr[0]))
					vertex = vertices.get(inStr[0]);
				else {
					vertex = new Vertex(inStr[0]);
					vertices.put(vertex.data, vertex);
				}
				vertex.reqSize = inStr.length - 1;
				for (int i = 1; i < inStr.length; i++) {
					Vertex requisite = null;
					if (vertices.containsKey(inStr[i])) {
						requisite = vertices.get(inStr[i]);
					} else {
						requisite = new Vertex(inStr[i]);
						vertices.put(inStr[i], requisite);
					}
					requisite.adjList.add(vertex.data);
				}
			}
		}
		//		System.out.println(vertices.values());
		for (Vertex u : vertices.values()) {
			if (u.c == Vertex.Color.WHITE && u.reqSize == 0) {
				dfsVisit(u);
			}
		}
		while (!result.isEmpty()) {
			System.out.println(new StringBuilder("Operation ").append(result.pop()).append(" on part X").toString());
		}
		reader.close();
	}

	static int t = 0;

	static void dfsVisit(Vertex u) {
		t++;
		u.d = t;
		u.c = Vertex.Color.GREY;
		Set<String> adjList = u.adjList;
		for (String v : adjList) {
			Vertex vVertex = vertices.get(v);
			if (vVertex.c == Vertex.Color.WHITE) {
				dfsVisit(vVertex);
			}
		}
		u.c = Vertex.Color.BLACK;
		t++;
		u.f = t;
		result.push(u.data);
	}
}

class Vertex {
	String data;
	int d, f, reqSize;
	Color c = Color.WHITE;
	Set<String> adjList = new HashSet<>();

	public Vertex(String data) {
		this.data = data;
	}

	public static enum Color {
		WHITE, BLACK, GREY;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
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
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Vertex [d=" + data + ", d=" + d + ", f=" + f + ", c=" + c + ", al=" + adjList + "]";
	}
}