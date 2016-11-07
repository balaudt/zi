package zi.chef.y16.feb;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.text.StrBuilder;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintStream;

/**
 * Created by balaudt on 2/7/16.
 */
public class DAnalysis {
    static int N_M = (int) 1e6;
    static int MAX_N = 10;

    public static void main(String[] args) throws Exception {
        System.setOut(new PrintStream("/Users/balaudt/Temp/feb/D-gen.in"));
        int t = N_M / MAX_N / MAX_N;
        System.out.println(t);
        for (int i = 0; i < t; i++) {
            System.out.println(MAX_N + " " + MAX_N);
            for (int j = 0; j < MAX_N; j++) {
                StrBuilder builder = new StrBuilder();
                for (int k = 0; k < MAX_N; k++) {
                    builder.append(RandomUtils.nextInt(0, MAX_N / 3)).append(' ');
                }
                builder.deleteCharAt(builder.length() - 1);
                System.out.println(builder.toString());
            }
        }
    }
}
