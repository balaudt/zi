package zi.chef.y16.feb;

import java.util.Arrays;

/**
 * Created by balaudt on 8/19/16.
 */
public class MapR1 {
    public static void main(String[] args) {
        System.out.println(arraySearch(new int[]{1,2,4,4,4,5}, SearchType.GREATER_THAN_EQUALS, 3));
    }

    public enum SearchType {
        LESS_THAN, LESS_THAN_EQUALS, EQUALS, GREATER_THAN_EQUALS, GREATER_THAN
    }

/*
 * Complete the function below.
 */

    static int arraySearch(int[] list, SearchType query, int value) {
        if (list.length == 0)
            return -1;
        int ip = Arrays.binarySearch(list, value);
        switch (query) {
            case EQUALS:
                if (ip < 0)
                    return -1;
                else
                    return firstIndex(list, ip);
            case LESS_THAN:
                if (ip < 0) {
                    ip = -ip - 1;
                    if (ip > 0)
                        return ip - 1;
                    else
                        return -1;
                } else {
                    ip = firstIndex(list, ip);
                    if (ip > 0)
                        return ip - 1;
                    else
                        return -1;
                }

            case GREATER_THAN:
                if (ip < 0) {
                    ip = -ip - 1;
                    if (ip < list.length)
                        return ip;
                    else
                        return -1;
                } else {
                    ip = lastIndex(list, ip);
                    if (ip < list.length - 1)
                        return ip + 1;
                    else
                        return -1;
                }
            case LESS_THAN_EQUALS:
                if (ip < 0) {
                    ip = -ip - 1;
                    if (ip > 0)
                        return ip - 1;
                    else
                        return -1;
                } else {
                    ip = firstIndex(list, ip);
                    if (ip > 0)
                        return ip - 1;
                    else
                        return ip;
                }
            case GREATER_THAN_EQUALS:
                if (ip < 0) {
                    ip = -ip - 1;
                    if (ip < list.length)
                        return ip;
                    else
                        return -1;
                } else
                    return firstIndex(list, ip);
        }
        return -1;
    }

    static int firstIndex(int[] list, int index) {
        int out = index;
        while (out >= 0 && list[out] == list[index])
            out--;
        return out + 1;
    }

    static int lastIndex(int[] list, int index) {
        int out = index;
        while (out < list.length && list[out] == list[index])
            out++;
        return out - 1;
    }
}
