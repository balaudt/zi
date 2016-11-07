package zi.euler;

import org.paukov.combinatorics.Factory;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;

import java.util.Arrays;
import java.util.List;

/**
 * Created by balaudt on 2/17/16.
 */
public class A {
    public static void main(String[] args) {
        for(int n=2;n<=10;n++) {
            Integer a[] = new Integer[n];
            for (int i = 0; i < n; i++) {
                a[i] = i+1;
            }
            Generator<Integer> generator = Factory.createPermutationGenerator(Factory.createVector(a));
            int ans = 0;
            for (ICombinatoricsVector<Integer> v : generator) {
                List<Integer> vector = v.getVector();
                int count = 1;
                boolean isCorrect = true;
                for (int num : vector) {
                    if (num % count != 0 && count%num!=0) {
                        isCorrect = false;
                        break;
                    }
                    count++;
                }
                if (isCorrect)
                    ans++;
            }
            System.out.print(ans+", ");
        }
    }
}
