package zi.jam.y18.qual;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * @author balamurugan
 */
public class TroubleSort {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(reader.readLine());
        int temp;
        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(reader.readLine());
            Integer[] v = Arrays.stream(reader.readLine().split(" ")).map(Integer::parseInt).toArray(Integer[]::new);
            boolean done = false;
            while (!done) {
                done = true;
                for (int j = 0; j < v.length - 2; j++) {
                    if (v[j] > v[j + 2]) {
                        done = false;
                        temp = v[j];
                        v[j] = v[j + 2];
                        v[j + 2] = temp;
                    }
                }
            }
            boolean isSorted = true;
            int pos = -1;
            for (int j = 0; j < v.length - 1; j++) {
                if (v[j + 1] < v[j]) {
                    pos = j;
                    isSorted = false;
                    break;
                }
            }
            if (isSorted) {
                System.out.println(String.format("Case #%d: %s", i + 1, "OK"));
            } else {
                System.out.println(String.format("Case #%d: %d", i + 1, pos));
            }
        }
    }
}
