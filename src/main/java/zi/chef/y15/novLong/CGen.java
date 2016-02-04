package zi.chef.y15.novLong;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Random;

import org.apache.commons.math3.util.ArithmeticUtils;

public class CGen {

	public static void main(String[] args) throws Exception {
		int primes[] = {
			    3,    5,    7,    11,   13,   17,   19,   23,   29,   31,   37,   41,
			    43,   47,   53,   59,   61,   67,   71,   73,   79,   83,   89,   97,
			    101,  103,  107,  109,  113,  127,  131,  137,  139,  149,  151,  157,
			    163,  167,  173,  179,  181,  191,  193,  197,  199,  211,  223,  227,
		};
		System.out.println(primes.length);
	}

	static void gen() throws Exception {
		BufferedWriter writer = new BufferedWriter(new FileWriter("/Users/balaudt/Temp/C-gen-1.in"));
		writer.write("1000000\n");
		Random random = new Random();
		for (int i = 0; i < 1000000; i++) {
			writer.write(random.nextInt((int) 1e7) + "\n");
		}
		writer.close();
	}

	static void check() throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader("/Users/balaudt/Temp/C-gen.in"));
		BufferedWriter writer = new BufferedWriter(new FileWriter("/Users/balaudt/Temp/C-gen-cor.out"));
		int t = Integer.parseInt(reader.readLine());
		for (int i = 0; i < t; i++) {
			int n = Integer.parseInt(reader.readLine());
			long sum = 0;
			for (int j = 1; j <= n; j++) {
				sum += n / ArithmeticUtils.gcd(n, j);
			}
			writer.write(sum + "\n");
		}
		reader.close();
		writer.close();
	}
}
