package zi.leet.medium;


import java.util.*;

//https://leetcode.com/problems/reorganize-string/
public class ReorganizeString {
    class C implements Comparable<C> {
        char c;
        int frequency;

        public C(char c, int frequency) {
            this.c = c;
            this.frequency = frequency;
        }

        @Override
        public int compareTo(C o) {
            return o.frequency - frequency;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            C c1 = (C) o;
            return c == c1.c;
        }

        @Override
        public int hashCode() {
            return Objects.hash(c);
        }
    }

    public String reorganizeString(String S) {
        Map<Character, Integer> frequencies = new HashMap<>();
        for (char c : S.toCharArray()) {
            Integer ct = frequencies.getOrDefault(c, 0);
            frequencies.put(c, ct + 1);
        }
        PriorityQueue<C> q = new PriorityQueue<>();
        frequencies.forEach((c, f) -> q.add(new C(c, f)));
        char[] out = new char[S.length()];
        int ind = 0;
        char lastChar = 'A';
        while (!q.isEmpty()) {
            C mostFrequent = q.poll();
            if (mostFrequent.c == lastChar) {
                if (q.isEmpty())
                    return "";
                else {
                    C nextFrequent = q.poll();
                    out[ind++] = nextFrequent.c;
                    if (nextFrequent.frequency != 1) {
                        nextFrequent.frequency--;
                        q.offer(nextFrequent);
                    }
                    q.offer(mostFrequent);
                }
            } else {
                out[ind++] = mostFrequent.c;
                if (mostFrequent.frequency != 1) {
                    mostFrequent.frequency--;
                    q.offer(mostFrequent);
                }
            }
            lastChar = out[ind - 1];
        }
        return new String(out);
    }

}
