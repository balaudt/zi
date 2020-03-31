package zi.leet.medium;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

//https://leetcode.com/problems/network-delay-time
public class NetworkDelayTimes {
    class V implements Comparable<V> {
        int d;
        Map<Integer, Integer> adj = new HashMap<>();

        public V(int d) {
            this.d = d;
        }

        @Override
        public int compareTo(V o) {
            return d - o.d;
        }
    }

    public int networkDelayTime(int[][] times, int N, int K) {
        V[] vertices = new V[N];
        PriorityQueue<V> q = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            vertices[i] = new V(Integer.MAX_VALUE);
            q.add(vertices[i]);
        }
        q.remove(vertices[K - 1]);
        vertices[K - 1].d = 0;
        q.add(vertices[K - 1]);

        for (int[] time : times) {
            Map<Integer, Integer> adj = vertices[time[0] - 1].adj;
            adj.put(time[1] - 1, Math.min(time[2], adj.getOrDefault(time[1] - 1, Integer.MAX_VALUE)));
        }

        while (!q.isEmpty()) {
            V u = q.poll();
            u.adj.forEach((vi, w) -> {
                V v = vertices[vi];
                if (v.d > u.d + w) {
                    q.remove(v);
                    v.d = u.d + w;
                    q.add(v);
                }
            });
        }
        int maxDelay = Integer.MIN_VALUE;
        for (V v : vertices) {
            maxDelay = Math.max(v.d, maxDelay);
        }
        return maxDelay == Integer.MAX_VALUE ? -1 : maxDelay;
    }


    public static void main(String[] args) {
        NetworkDelayTimes times = new NetworkDelayTimes();
        System.out.println(times.networkDelayTime(new int[][]{{2, 1, 1}, {2, 3, 1}, {3, 4, 1}}, 4, 2));
    }
}
