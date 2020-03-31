package zi.leet.easy;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/problems/binary-watch
public class BinaryWatch {
    private int[] p;
    private List<String> out;
    private int count = 0;

    public List<String> readBinaryWatch(int num) {
        p = new int[num];
        out = new ArrayList<>();
        combine(0, 0);
        System.out.println(count);
        return out;
    }

    private void combine(int index, int start) {
        int num = p.length;
        if (index == num) {
            count++;
            char[] stateArr = new char[10];
            for (int i = 0; i < stateArr.length; i++) {
                stateArr[i] = '0';
            }
            for (int i = 0; i < num; i++) {
                stateArr[p[i]] = '1';
            }
            String state = new String(stateArr);
            int hour = Integer.parseInt(state.substring(0, 4), 2);
            int minute = Integer.parseInt(state.substring(4), 2);
            out.add(String.format("%d:%02d", hour, minute));
            return;
        }
        for (int i = start; i <= 10 - num; i++) {
            p[index] = i;
            combine(index + 1, i + 1);
        }
    }


    public static void main(String[] args) {
        System.out.println(new BinaryWatch().readBinaryWatch(2));
    }
}
