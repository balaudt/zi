package zi.hrank;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Ground {
	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader("/home/bala/temp/ground.txt"));
		// BufferedReader reader = new BufferedReader(new
		// InputStreamReader(System.in));
		String[] inStr = reader.readLine().split(" ");
		int n = Integer.parseInt(inStr[0]);
		int k = Integer.parseInt(inStr[1]);
		int q = Integer.parseInt(inStr[2]);
		k %= n;
		inStr = reader.readLine().split(" ");
		Integer in[] = new Integer[n];
		for (int i = 0; i < inStr.length; i++) {
			in[i] = Integer.parseInt(inStr[i]);
		}
		List<Integer> list = Arrays.asList(in);
		Collections.rotate(list, k);
		for (int i = 0; i < q; i++) {
			int idx = Integer.parseInt(reader.readLine());
			System.out.println(list.get(idx));
		}
		reader.close();
	}

}

interface Default1{
	default void a(){};
}
interface Default2{
	void a();
}
class Conc implements Default2,Default1{

	@Override
	public void a() {
		Default1.super.a();
	}
	
}