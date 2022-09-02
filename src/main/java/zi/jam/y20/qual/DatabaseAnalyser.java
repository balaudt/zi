package zi.jam.y20.qual;

import java.util.*;
import java.util.stream.Collectors;

public class DatabaseAnalyser {
    public static void main(String[] args) {
        Set<List<Integer>> solutions = new HashSet<>();
        for (int at = 0; at < 1000000; at++) {
            Random random = new Random();
            int first = random.nextInt(512);
            int second = random.nextInt(512);
            int[] pos = new int[8];
            pos[0] = (second << 10) | Database.reverseBits(first, 10);
            pos[1] = (second << 10) | (Database.reverseBits(first, 10) ^ 0x3FF);
            pos[2] = Database.reverseBits(pos[0], 20);
            pos[3] = Database.reverseBits(pos[1], 20);
            pos[4] = pos[0] ^ 0xFFFFF;
            pos[5] = pos[1] ^ 0xFFFFF;
            pos[6] = Database.reverseBits(pos[3], 20);
            pos[7] = Database.reverseBits(pos[4], 20);
//            Arrays.stream(pos).forEach(x -> Database.prettyPrint(x, 20, System.out));
            Set<String> possibilities = Arrays.stream(pos).mapToObj(x -> Database.padBinaryStr(x, 20)).collect(Collectors.toSet());
            Combination combination = new Combination(20, 9);
            boolean isFound = false;
            while (combination.hasNext() && !isFound) {
                int[] next = combination.next();
                //            System.out.println(Arrays.toString(next));
                Set<String> visited = new HashSet<>();
                boolean isUnique = true;
                for (String possibility : possibilities) {
                    char[] c = new char[10];
                    for (int i = 0; i < next.length; i++)
                        c[i] = possibility.charAt(next[i]);
                    String bin = new String(c);
                    if (visited.contains(bin)) {
                        isUnique = false;
                        break;
                    }
                    visited.add(bin);
                }
                if (isUnique) {
                    List<Integer> solution = new ArrayList<>();
                    for (int j : next) {
                        solution.add(j);
                    }
                    solutions.add(solution);
                    isFound = true;
                }
            }
        }
        System.out.println(solutions);
    }

}

class Combination {
    private int n, r;
    private int[] index;
    private boolean hasNext = true;

    public Combination(int n, int r) {
        this.n = n;
        this.r = r;
        index = new int[r];
        for (int i = 0; i < r; i++) index[i] = i;
    }

    public boolean hasNext() {
        return hasNext;
    }

    // Based on code from KodersCode:
    // The algorithm is from Applied Combinatorics, by Alan Tucker.
    // Move the index forward a notch. The algorithm finds the rightmost
    // index element that can be incremented, increments it, and then
    // changes the elements to the right to each be 1 plus the element on their left.
    //
    // For example, if an index of 5 things taken 3 at a time is at {0 3 4}, only the 0 can
    // be incremented without running out of room. The next index is {1, 1+1, 1+2) or
    // {1, 2, 3}. This will be followed by {1, 2, 4}, {1, 3, 4}, and {2, 3, 4}.

    private void moveIndex() {
        int i = rightmostIndexBelowMax();
        if (i >= 0) {
            index[i] = index[i] + 1;
            for (int j = i + 1; j < r; j++)
                index[j] = index[j - 1] + 1;
        } else hasNext = false;
    }

    public int[] next() {
        if (!hasNext) return null;
        int[] result = new int[r];
        for (int i = 0; i < r; i++) result[i] = index[i];
        moveIndex();
        return result;
    }

    // return int,the index which can be bumped up.
    private int rightmostIndexBelowMax() {
        for (int i = r - 1; i >= 0; i--)
            if (index[i] < n - r + i) return i;
        return -1;
    }
}
