package zi.chef.y16.feb;

import java.util.*;

/**
 * Created by balaudt on 8/17/16.
 */
public class GraphAlgos {
    static List<Node> vertices = new ArrayList<>();
    static int ts = 0;

    public static void traversals(String[] args) {
        vertices.add(new Node(0, Arrays.asList(1, 2)));
        vertices.add(new Node(1, Arrays.asList(2)));
        vertices.add(new Node(2, Arrays.asList(0, 3)));
        vertices.add(new Node(3, Arrays.asList(3)));
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> toVisit = new ArrayDeque<>();
        toVisit.offer(2);
        while (!toVisit.isEmpty()) {
            Node node = vertices.get(toVisit.poll());
            System.out.println(node.v);
            visited.add(node.v);
            for (Integer neighbor : node.neighbors) {
                if (!visited.contains(neighbor))
                    toVisit.offer(neighbor);
            }
        }

        dfs(2);
        System.out.println(vertices);
    }

    public static void main(String[] args) {
        vertices.add(new Node(0, Arrays.asList()));
        vertices.add(new Node(1, Arrays.asList()));
        vertices.add(new Node(2, Arrays.asList(3)));
        vertices.add(new Node(3, Arrays.asList(1)));
        vertices.add(new Node(4, Arrays.asList(1, 0)));
        vertices.add(new Node(5, Arrays.asList(2, 0)));
        dfs(5);
//        dfs(4);
        System.out.println(st);
    }

    static Stack<Integer> st = new Stack<>();

    static void dfs(int vertex) {
        System.out.println(vertex);
        Node node = vertices.get(vertex);
        ts++;
        node.startTime = ts;
        node.color = Node.GRAY;
        for (Integer neighbor : node.neighbors) {
            if (vertices.get(neighbor).color == Node.WHITE)
                dfs(neighbor);
        }
        ts++;
        node.finishTime = ts;
        node.color = Node.BLACK;
        st.push(node.v);
    }
}

class Node {
    int v;
    List<Integer> neighbors;
    byte color;
    static final byte WHITE = 0;
    static final byte BLACK = 1;
    static final byte GRAY = 2;
    int startTime, finishTime;

    @Override
    public String toString() {
        return "Node{" +
                "v=" + v +
                ", color=" + color +
                ", startTime=" + startTime +
                ", finishTime=" + finishTime +
                "}\n";
    }

    public Node(int v, List<Integer> neighbors) {
        this.v = v;
        this.neighbors = neighbors;
    }
}