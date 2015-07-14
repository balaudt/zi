package zi.jam.europython13;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

import org.apache.commons.lang3.text.StrBuilder;

public class CaptainHammer {

	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader("C:/ft/8/B-small-practice-1.in"));
		BufferedWriter writer = new BufferedWriter(new FileWriter("C:/ft/8/B-small-practice-1.out"));
		int t = Integer.parseInt(reader.readLine());
		double g = 9.8;
		for (int i = 0; i < t; i++) {
			String[] inStr = reader.readLine().split(" ");
			double v = Integer.parseInt(inStr[0]);
			double d = Integer.parseInt(inStr[1]);
			double ans = d * g / (v * v);
			if(ans>1)
				ans=1.0;
			StrBuilder builder = new StrBuilder("Case #").append(i + 1).append(": ")
					.append(Math.toDegrees(Math.asin(ans) / 2)).append("\n");
//			System.out.println(builder.toString());
			writer.write(builder.toString());
		}
		reader.close();
		writer.close();
	}
}
