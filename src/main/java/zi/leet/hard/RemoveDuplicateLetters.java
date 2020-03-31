package zi.leet.hard;

import java.util.*;

public class RemoveDuplicateLetters {
    public String removeDuplicateLetters(String s) {
        char[] in = s.toCharArray();
        TreeMap<Character, List<Integer>> charPos = new TreeMap<>(Comparator.reverseOrder());
        for (int i = 0; i < in.length; i++) {
            List<Integer> positions = charPos.getOrDefault(in[i], new ArrayList<>());
            positions.add(i);
            charPos.put(in[i], positions);
        }
        TreeSet<Integer> insertedPositions = new TreeSet<>();
        for (Map.Entry<Character, List<Integer>> entry : charPos.entrySet()) {
            List<Integer> positions = entry.getValue();
            int size = positions.size();
            if (size == 1) {
                insertedPositions.add(positions.get(0));
                continue;
            }
            int positionToInsert = -1;
            for (int i = size - 1; i >= 0; i--) {
                int position = positions.get(i);
                if (insertedPositions.higher(position) != null) {
                    positionToInsert = position;
                    break;
                }
            }
            positionToInsert = positionToInsert == -1 ? positions.get(size - 1) : positionToInsert;
            for (Integer position : positions) {
                if (position != positionToInsert) in[position] = '#';
            }
            insertedPositions.add(positionToInsert);
        }
        char[] out = new char[charPos.size()];
        int j = 0;
        for (char c : in) {
            if (c != '#') {
                out[j++] = c;
            }
        }
        return new String(out);
    }
}
