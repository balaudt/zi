package zi.leet.medium;

import java.util.*;

//https://leetcode.com/problems/evaluate-division
public class EvaluateDivision {
    private Map<String, Map<String, Double>> adjList;
    private Set<String> visited = new HashSet<>();

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        adjList = new HashMap<>();
        for (int i = 0; i < equations.size(); i++) {
            List<String> equation = equations.get(i);
            double weight = values[i];
            String source = equation.get(0);
            if (!adjList.containsKey(source)) {
                adjList.put(source, new HashMap<>());
            }
            String destination = equation.get(1);
            if (!adjList.containsKey(destination)) {
                adjList.put(destination, new HashMap<>());
            }
            adjList.get(source).put(destination, weight);
            adjList.get(destination).put(source, 1.0 / weight);
        }
        double[] results = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            List<String> query = queries.get(i);
            String source = query.get(0);
            String destination = query.get(1);
            if (!adjList.containsKey(source) || !adjList.containsKey(destination)) {
                results[i] = -1.0;
                continue;
            }
            if (source.equals(destination)) {
                results[i] = 1.0;
                continue;
            }
            visited.clear();
            double result = getDivision(source, destination, 1.0);
            if (Double.POSITIVE_INFINITY != result)
                results[i] = result;
            else
                results[i] = -1.0;
        }
        return results;
    }

    private double getDivision(String node, String dest, double val) {
        if (node.equals(dest))
            return val;
        if (visited.contains(node))
            return Double.POSITIVE_INFINITY;
        visited.add(node);
        for (Map.Entry<String, Double> entry : adjList.get(node).entrySet()) {
            double result = getDivision(entry.getKey(), dest, val * entry.getValue());
            if (result != Double.POSITIVE_INFINITY)
                return result;
        }
        return Double.POSITIVE_INFINITY;
    }

    public static void main(String[] args) {
        EvaluateDivision evaluateDivision = new EvaluateDivision();
        System.out.println(Arrays.toString(evaluateDivision.calcEquation(l(l("a", "b"), l("b", "c")), new double[]{2.0, 3.0}, l(l("a", "c"), l("b", "a"), l("a", "e"), l("a", "a"), l("x", "x")))));
    }

    private static <T> List<T> l(T... a) {
        return Arrays.asList(a);
    }
}

