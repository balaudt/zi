package zi.leet.hard;

//https://leetcode.com/problems/similar-string-groups
public class SimilarStringGroups {
    class DSU {
        int[] parent;

        DSU(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++)
                parent[i] = i;
        }

        int find(int x) {
            if (parent[x] != x) parent[x] = find(parent[x]);
            return parent[x];
        }

        void union(int x, int y) {
            parent[find(x)] = find(y);
        }
    }

    public int numSimilarGroups(String[] A) {
        char[][] in = new char[A.length][];
        DSU dsu = new DSU(A.length);
        for (int i = 0; i < in.length; i++) {
            in[i] = A[i].toCharArray();
        }
        for (int i = 0; i < A.length - 1; i++) {
            for (int j = i + 1; j < A.length; j++) {
                if (isSimilar(in[i], in[j]))
                    dsu.union(i, j);
            }
        }
        int result = 0;
        for (int i = 0; i < in.length; i++) {
            if (dsu.find(i) == i) result++;
        }
        return result;
    }

    private boolean isSimilar(char[] a, char[] b) {
        boolean swapDone = false, mismatchFound = false;
        int mismatchIndex = -1;
        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i])
                if (swapDone)
                    return false;
                else if (!mismatchFound) {
                    mismatchFound = true;
                    mismatchIndex = i;
                } else {
                    if (a[mismatchIndex] != b[i] || a[i] != b[mismatchIndex])
                        return false;
                    else
                        swapDone = true;
                }
        }
        return true;
    }
}
