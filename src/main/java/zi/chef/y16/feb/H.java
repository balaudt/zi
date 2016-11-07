package zi.chef.y16.feb;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by balaudt on 2/14/16.
 */
public class H {
    static final int P = 998244353;
    static final long MAX_N = (long) 1e9;

    public static void main(String[] args) throws Exception {
        int base = 2;
        Map<Integer, Integer> perfectNumberToLeastRoot = new HashMap<>();
        while (true) {
            if (perfectNumberToLeastRoot.containsKey(base)) {
                base++;
                continue;
            }
            long curNum = base * base;
            while (curNum < MAX_N) {
                if (!perfectNumberToLeastRoot.containsKey(curNum))
                    perfectNumberToLeastRoot.put((int) curNum, base);
                curNum *= base;
            }
            if (curNum == base * base)
                break;
            else
                base++;
        }
        Map<Integer, Integer> sortedMap = new TreeMap<>(perfectNumberToLeastRoot);
        List<Integer> addSums = new ArrayList<>(sortedMap.size() + 1);
        List<Long> subSums = new ArrayList<>(sortedMap.size() + 1);
        addSums.add(0);
        subSums.add(1l);
        int index = 0;
        for (Map.Entry<Integer, Integer> entry : sortedMap.entrySet()) {
            addSums.add(addSums.get(index) + entry.getValue());
            subSums.add(subSums.get(index) + entry.getKey());
            index++;
        }
        List<Integer> searchIndex = new ArrayList<>(sortedMap.keySet());

//        BufferedReader reader = new BufferedReader(new FileReader("/Users/balaudt/Temp/feb/H-gen.in"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(reader.readLine());
        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(reader.readLine());
            if (n > MAX_N)
                throw new UnsupportedOperationException();
            long ans = (long) n * (n + 1) / 2;
            int ip = Collections.binarySearch(searchIndex, n);
            if (ip > 0)
                ip++;
            else
                ip = -ip - 1;
            ans += addSums.get(ip);
            ans -= subSums.get(ip);
            ans %= P;
            System.out.println(ans);
        }
        reader.close();

    }

}
