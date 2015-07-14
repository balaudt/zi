package zi.hrank;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class FibModify {
	public static void main(String[] args) throws Exception {
//		BufferedReader reader = new BufferedReader(new FileReader("/home/bala/temp/ground.txt"));
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String[] inStr = reader.readLine().split(" ");
		memo.put(1, new BigInteger(inStr[0]));
		memo.put(2, new BigInteger(inStr[1]));
		System.out.println(fib(Integer.parseInt(inStr[2])));
//		System.out.println(memo);
		reader.close();
	}

	static Map<Integer, BigInteger> memo = new HashMap<>();

	static BigInteger fib(int n) {
		if (memo.containsKey(n))
			return memo.get(n);
		BigInteger fibn_1 = fib(n - 1);
		BigInteger out = fibn_1.multiply(fibn_1).add(fib(n - 2));
		memo.put(n, out);
		return out;
	}
}
