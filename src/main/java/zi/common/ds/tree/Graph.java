package zi.common.ds.tree;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author balamurugan
 */
public class Graph<V> {

    class Node {
        //d is distance (in case of BFS)/discovery time (in case of DFS), f is finish time, pi is predecessor
        int ind, d = Integer.MAX_VALUE, f, pi = -1;
        V val;
        Map<Integer, Integer> adjList = new HashMap<>();
        char color;

        Node(int ind, V val) {
            this.ind = ind;
            this.val = val;
        }
    }

    private Node[] vertices;

    public Graph(V[] v, int[][] edges, boolean bidirectional) {
        vertices = (Node[]) Array.newInstance(Node.class, v.length);
        for (int i = 0; i < v.length; i++) vertices[i] = new Node(i, v[i]);
        for (int[] edge : edges) {
            vertices[edge[0]].adjList.put(edge[1], edge.length > 2 ? edge[2] : 1);
            if (bidirectional) vertices[edge[1]].adjList.put(edge[0], edge.length > 2 ? edge[2] : 1);
        }
    }

    /**
     * Do a BFS starting from source (vertex with index st)
     * Generates a breadth-first tree that provides shortest path and distance of each vertex to source (unweighted)
     */
    public void bfs(int st) {
        Node s = vertices[st];
        s.color = 'G';
        s.d = 0;
        Queue<Node> q = new ArrayDeque<>();
        q.offer(s);
        while (!q.isEmpty()) {
            Node u = q.poll();
            u.adjList.keySet().forEach(vInd -> {
                Node v = vertices[vInd];
                if (v.color != 'W')
                    return;
                v.color = 'G';
                v.d = u.d + 1;
                v.pi = u.ind;
                q.offer(v);
            });
            u.color = 'B';
        }
    }

    private int time;

    /**
     * Do a DFS starting from source
     * Provides information about cycles, discovery times
     */
    public void dfs(int st) {
        Node s = vertices[st];
        time++;
        s.d = time;
        s.color = 'G';
        s.adjList.keySet().forEach(vInd -> {
            Node v = vertices[vInd];
            //G here means back edge [link back to parent]
            //B means forward/cross edge [multi-parent / link from uncle('s ancestor)]
            if (v.color != 'W')
                return;
            v.pi = s.ind;
            dfs(v.ind);
        });
        s.color = 'B';
        time++;
        s.f = time;
    }

    /**
     * Do a topology sort of the DAG
     */
    public List<Integer> topologySort() {
        Arrays.stream(vertices).forEach(v -> {
            if (v.color == 'W') dfs(v.ind);
        });
        Node[] nodes = Arrays.copyOf(vertices, vertices.length);
        Arrays.sort(nodes, Comparator.comparingInt(o -> o.f));
        return Arrays.stream(nodes).map(v -> v.ind).collect(Collectors.toList());
    }

    public List<List<Integer>> stronglyConnectedComponents() {
        Arrays.stream(vertices).forEach(v -> {
            if (v.color == 'W') dfs(v.ind);
        });
        List<V> gtVertices = new ArrayList<>(vertices.length);

//        Graph<V> gt = new Graph<>(gtVertices.toArray(Array.newInstance()), null, false);
        return null;
    }
}
