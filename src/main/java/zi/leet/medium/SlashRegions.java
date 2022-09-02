package zi.leet.medium;

import java.util.Arrays;

public class SlashRegions {
    class DSU {
        int[] parent;

        DSU(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++)
                parent[i] = i;
        }

        int findSet(int v) {
            if (v == parent[v])
                return v;
            return parent[v] = findSet(parent[v]);
        }

        void union(int u, int v) {
            u = findSet(u);
            v = findSet(v);
            if (u != v)
                parent[v] = u;
        }

        int cardinality() {
            int res = 0;
            for (int i = 0; i < parent.length; i++)
                if (parent[i] == i)
                    res++;
            return res;
        }

        public String toString() {
            return Arrays.toString(parent);
        }
    }

    public int regionsBySlashes(String[] grid) {
        int n = grid.length;
        DSU dsu = new DSU(n * n * 4);
        for (int r = 0; r < n; r++) {
            String row = grid[r];
            for (int c = 0; c < n; c++) {
                int cell = (r * n + c) * 4;
                switch (row.charAt(c)) {
                    case '\\':
                        dsu.union(cell, cell + 3);
                        dsu.union(cell + 1, cell + 2);
                        break;
                    case '/':
                        dsu.union(cell, cell + 1);
                        dsu.union(cell + 2, cell + 3);
                        break;
                    case ' ':
                        dsu.union(cell, cell + 1);
                        dsu.union(cell + 1, cell + 2);
                        dsu.union(cell + 2, cell + 3);
                }

                if (r != n - 1)
                    dsu.union(cell + 3, cell + n * 4 + 1);
                if (c != n - 1)
                    dsu.union(cell + 2, cell + 4);
            }
        }
        return dsu.cardinality();
    }

    public static void main(String[] args) {
        SlashRegions slashRegions = new SlashRegions();
        System.out.println(slashRegions.regionsBySlashes(new String[]{" /", "/ "}));
        System.out.println(slashRegions.regionsBySlashes(new String[]{" /", "  "}));
        System.out.println(slashRegions.regionsBySlashes(new String[]{"/\\", "\\/"}));
    }
}
