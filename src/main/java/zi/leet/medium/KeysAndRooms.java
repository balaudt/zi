package zi.leet.medium;

import java.util.ArrayDeque;
import java.util.BitSet;
import java.util.List;
import java.util.Queue;

//https://leetcode.com/problems/keys-and-rooms
public class KeysAndRooms {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int n = rooms.size();
        BitSet state = new BitSet(n);
        Queue<Integer> keys = new ArrayDeque<>();
        keys.add(0);
        state.set(0);
        while (!keys.isEmpty() && state.nextClearBit(0) != -1) {
            Integer roomKey = keys.poll();
            rooms.get(roomKey).forEach(key -> {
                if (!state.get(key)) {
                    state.set(key - 1);
                    keys.add(key - 1);
                }
            });
        }
        return state.cardinality() == n;
    }

}
