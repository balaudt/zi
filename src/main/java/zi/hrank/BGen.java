package zi.hrank;
import java.util.Random;

public class BGen {

	public static void main(String[] args) {
		Random random = new Random();
		char[] out = new char[300];
		for (int i = 0; i < out.length; i++) {
			out[i] = (char) ('a' + random.nextInt(26));
		}
		System.out.println(new String(out));
		long ans = out.length;
		for (int i = 0; i < out.length - 1; i++) {
			for (int j = i + 1; j < out.length; j++) {
				if (out[i] == out[j])
					ans++;
			}
		}
		System.out.println(ans);
	}
}
