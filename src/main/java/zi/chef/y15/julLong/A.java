package zi.chef.y15.julLong;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class A {

	static final String FOLDER = "/home/bala/temp/8/";

	public static void main(String[] args) throws Exception {
		Map<String, Byte> colorMap = Stream
				.<Object[]> of(new Object[] { "black", 1 }, new Object[] { "blue", 2 }, new Object[] { "red", 3 },
						new Object[] { "green", 4 }, new Object[] { "yellow", 5 }, new Object[] { "orange", 6 })
				.collect(Collectors.<Object[], String, Byte> toMap(obj -> (String) obj[0], obj -> ((Integer) obj[1]).byteValue()));
		int[] pairAdjTrip = new int[] { 38, 39, 76, 112, 53, 59, 101, 137 };
//		BufferedReader reader = new BufferedReader(new FileReader(FOLDER + "A-sample.in"));
		 BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(reader.readLine());
		byte[] in = new byte[6];
		for (int i = 0; i < t; i++) {
			String[] inStr = reader.readLine().split(" ");
			for (int j = 0; j < inStr.length; j++) {
				if (j % 2 == 0)
					in[j / 2] = colorMap.get(inStr[j]);
				else
					in[5 - j / 2] = colorMap.get(inStr[j]);
			}
			boolean found = false;
			for (int j = 0; j < pairAdjTrip.length; j++) {
				char[] cs = Integer.toString(pairAdjTrip[j], 6).toCharArray();
				if (in[cs[0] - '0'] == in[cs[1] - '0'] && in[cs[1] - '0'] == in[cs[2] - '0']) {
					found = true;
					break;
				}
			}
			if (found)
				System.out.println("YES");
			else
				System.out.println("NO");
		}
		reader.close();
	}
}
