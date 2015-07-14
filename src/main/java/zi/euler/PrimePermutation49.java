package zi.euler;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.math3.primes.Primes;
import org.paukov.combinatorics.Factory;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multiset;

public class PrimePermutation49 {

    public static void main(String[] args) {
        int prime = Primes.nextPrime(1000);
        Set<Integer> primes = new HashSet<Integer>();
        while (prime < 10000) {
            primes.add(prime);
            prime = Primes.nextPrime(prime + 1);
        }
        System.out.println(primes.size());
        ICombinatoricsVector<Integer> cv = Factory.createVector(primes);
        Generator<Integer> generator = Factory.createSimpleCombinationGenerator(cv, 2);
        Multimap<Integer, List<Integer>> map = ArrayListMultimap.<Integer, List<Integer>> create();
        for (ICombinatoricsVector<Integer> perm : generator) {
            List<Integer> vector = perm.getVector();
            char[] c1s = vector.get(0).toString().toCharArray();
            char[] c2s = vector.get(1).toString().toCharArray();
            Arrays.sort(c1s);
            Arrays.sort(c2s);
            if (Arrays.equals(c1s, c2s))
                map.put(Math.abs(vector.get(0) - vector.get(1)), vector);
        }
        Multiset<Integer> keys = map.keys();
        for (Integer key : keys) {
            Collection<List<Integer>> collection = map.get(key);
            if (collection.size() < 2)
                continue;
            Generator<List<Integer>> gen = Factory.createSimpleCombinationGenerator(Factory.createVector(collection), 2);
            for (ICombinatoricsVector<List<Integer>> comb : gen) {
                List<List<Integer>> combVec = comb.getVector();
                List<Integer> c1 = combVec.get(0);
                List<Integer> c2 = combVec.get(1);
                if (c1.get(0) == c2.get(0) || c1.get(0) == c2.get(1))
                    System.out.println(combVec);
                if (c1.get(1) == c2.get(0) || c1.get(1) == c2.get(1))
                    System.out.println(combVec);
            }
        }
    }
}


