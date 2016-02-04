package zi.chef.y15.decLong;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class E {

	static final long MAX_POS = (long) 1e6;

	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(reader.readLine());
		String inStr[];
		for (int i = 0; i < t; i++) {
			inStr = reader.readLine().split(" ");
			int n = Integer.parseInt(inStr[0]);
			int m = Integer.parseInt(inStr[1]);
			long z = Long.parseLong(inStr[2]);
			long l = Long.parseLong(inStr[3]);
			long r = Long.parseLong(inStr[4]);
			long b = Long.parseLong(inStr[5]);

			if (z > MAX_POS || l > MAX_POS || r > MAX_POS) {
				reader.close();
				throw new UnsupportedOperationException();
			}
			if (r != 0) {
				
			}else{
				
			}
		}
		reader.close();
	}

}
