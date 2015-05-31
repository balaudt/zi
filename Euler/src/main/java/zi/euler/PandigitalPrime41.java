package zi.euler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.text.StrBuilder;
import org.apache.commons.math3.primes.Primes;
import org.paukov.combinatorics.Factory;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;

public class PandigitalPrime41 {

    public static void main(String[] args) {
        StrBuilder builder;
        int max = 3;
        for (int i = 4; i <= 9; i++) {
            Integer[] pos = new Integer[i];
            for (int j = 0; j < pos.length; j++) {
                pos[j] = j + 1;
            }
            ICombinatoricsVector<Integer> inVector = Factory.createVector(pos);
            Generator<Integer> generator = Factory.createPermutationGenerator(inVector);
            for (ICombinatoricsVector<Integer> perm : generator) {
                List<Integer> vector = perm.getVector();
                if (vector.get(vector.size() - 1) % 2 == 0)
                    continue;
                builder = new StrBuilder();
                for (Integer num : vector) {
                    builder.append(num);
                }
                int num = Integer.parseInt(builder.toString());
                if (Primes.isPrime(num) && num > max)
                    max = num;
            }
        }
        System.out.println(max);
    }

    public static void main1(String[] args) {
        int prime = 3, largest = 3;
        while (prime < 987654321) {
            prime = Primes.nextPrime(prime + 1);
            if (anotherVersion(prime)) {
                largest = prime;
                System.out.println(largest);
            }
            // System.out.println(prime);
        }
        System.out.println(largest);
    }

    static boolean isPrimePandigitial(Integer prime) {
        String str = prime.toString();
        if (str.contains("0") || str.contains("(\\d).*\\1"))
            return false;
        char[] c1 = str.toCharArray();
        char[] c2 = new char[c1.length];
        for (int i = 1; i <= c2.length; i++) {
            c2[i - 1] = new String(i + "").charAt(0);
        }
        Arrays.sort(c1);
        if (Arrays.equals(c1, c2))
            return true;
        return false;
    }

    static boolean anotherVersion(Integer prime) {
        String str = prime.toString();
        if (str.contains("0") || str.contains("(\\d).*\\1"))
            return false;
        char[] c1 = str.toCharArray();
        ArrayList<Character> c3 = new ArrayList<Character>(c1.length);
        for (int i = 1; i <= c1.length; i++) {
            c3.add((char) (i + '0'));
        }
        for (int i = 0; i < c1.length; i++) {
            if (!c3.contains((Character) c1[i]))
                return false;
        }
        return true;
    }
}


