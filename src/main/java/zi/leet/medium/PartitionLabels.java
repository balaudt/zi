package zi.leet.medium;

import java.util.*;
import java.util.stream.Collectors;

//https://leetcode.com/problems/partition-labels
public class PartitionLabels {
    class Union {
        int start, end;
        Set<Character> uniqueChars;

        @Override
        public String toString() {
            return "Union{" + "start=" + start + ", end=" + end + ", uniqueChars=" + uniqueChars + '}';
        }
    }

    public List<Integer> partitionLabels(String S) {
        List<Union> unions = new ArrayList<>();
        char[] cs = S.toCharArray();
        for (int i = 0; i < cs.length; i++) {
            int unionMerge = -1;
            for (int j = 0; j < unions.size(); j++) {
                if (unions.get(j).uniqueChars.contains(cs[i])) {
                    unionMerge = j;
                    break;
                }
            }
            Union union = new Union();
            if (unionMerge == -1) {
                union.start = i;
                union.uniqueChars = new HashSet<>();
                union.uniqueChars.add(cs[i]);
            } else {
                ListIterator<Union> it = unions.listIterator(unionMerge);
                union.start = unions.get(unionMerge).start;
                union.uniqueChars = new HashSet<>();
                while (it.hasNext()) {
                    Union prev = it.next();
                    union.uniqueChars.addAll(prev.uniqueChars);
                    it.remove();
                }
            }
            union.end = i;
            unions.add(union);
        }
        return unions.stream().map(union -> union.end - union.start + 1).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        System.out.println(new PartitionLabels().partitionLabels("ababcbacadefegdehijhklij"));
    }
}
