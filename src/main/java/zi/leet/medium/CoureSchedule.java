package zi.leet.medium;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

//https://leetcode.com/problems/course-schedule
public class CoureSchedule {
    class V {
        Set<Integer> adj = new HashSet<>();
        int d, f;
        char c = 'w';
        int i;

        public V(int i) {
            this.i = i;
        }
    }

    int time = 1;
    V[] v;

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        v = new V[numCourses];
        for (int i = 0; i < numCourses; i++)
            v[i] = new V(i);
        for (int[] prerequisite : prerequisites)
            v[prerequisite[1]].adj.add(prerequisite[0]);
        for (int i = 0; i < numCourses; i++) {
            if (v[i].d == 0 && !dfs(i))
                return new int[]{};
        }
        Arrays.sort(v, (u, v) -> v.f - u.f);
        int[] out = new int[numCourses];
        for (int i = 0; i < v.length; i++)
            out[i] = v[i].i;
        return out;
    }

    private boolean dfs(int node) {
        V u = v[node];
        time++;
        u.c = 'g';
        u.d = time;
        for (Integer adj : u.adj) {
            if (v[adj].c == 'g')
                return false;
            else if (v[adj].c == 'w')
                if (!dfs(adj))
                    return false;
        }
        time++;
        u.f = time;
        u.c = 'b';
        return true;
    }

    public static void main(String[] args) {
        CoureSchedule coureSchedule = new CoureSchedule();
        System.out.println(Arrays.toString(coureSchedule.findOrder(4, new int[][]{{1, 0}, {2, 0}, {3, 1}, {3, 2}})));
    }
}
