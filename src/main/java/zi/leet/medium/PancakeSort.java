package zi.leet.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

//https://leetcode.com/problems/pancake-sorting
public class PancakeSort {
    public List<Integer> pancakeSort(int[] A) {
        List<Integer> out = new ArrayList<>();
        List<Integer> in = new ArrayList<>();
        in.add(A[0]);
        for (int i = 1; i < A.length; i++) {
            int ip = Collections.binarySearch(in, A[i]);
            ip = -ip - 1;
            out.add(i);

        }
        return out;
    }

}
