package zi.leet.medium;

import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/problems/lexicographically-smallest-equivalent-string
public class LexigrophicEquivalence {
    class DSU {
        int[] parent;

        DSU() {
            parent = new int[26];
            for (int i = 0; i < 26; i++) parent[i] = i;
        }

        int find(int x) {
            if (parent[x] != x) parent[x] = find(parent[x]);
            return parent[x];
        }

        void union(int x, int y) {
            parent[find(x)] = find(y);
        }
    }


    public String smallestEquivalentString(String A, String B, String S) {
        DSU dsu = new DSU();
        char[] a = A.toCharArray();
        char[] b = B.toCharArray();
        for (int i = 0; i < a.length; i++)
            dsu.union(a[i] - 'a', b[i] - 'a');
        Map<Integer, Integer> lexMap = new HashMap<>();
        for (int i = 0; i < 26; i++) {
            int parent = dsu.find(i);
            if (lexMap.containsKey(parent))
                lexMap.put(parent, Math.min(i, lexMap.get(parent)));
            else
                lexMap.put(parent, i);
        }
        char[] s = S.toCharArray();
        char[] out = new char[s.length];
        for (int i = 0; i < s.length; i++) {
            out[i] = (char) ('a' + lexMap.get(dsu.find(s[i] - 'a')));
        }
        return new String(out);
    }

    public static void main(String[] args) {
        LexigrophicEquivalence equivalence = new LexigrophicEquivalence();
        System.out.println(equivalence.smallestEquivalentString("leetcode", "programs", "sourcecode"));
    }
}
