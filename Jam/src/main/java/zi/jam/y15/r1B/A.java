package zi.jam.y15.r1B;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.StrBuilder;

import edu.uci.ics.jung.graph.DelegateTree;

public class A {

	public static final String FOLDER_ROOT = "C:/ft/20/";

	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader(FOLDER_ROOT + "A-small-attempt1.in"));
		BufferedWriter writer = new BufferedWriter(new FileWriter(FOLDER_ROOT + "A-small-attempt1.out"));
		// BufferedReader reader = new BufferedReader(new FileReader(FOLDER_ROOT
		// + "A-sample.in"));
		// BufferedWriter writer = new BufferedWriter(new FileWriter(FOLDER_ROOT
		// + "A-sample.out"));
		int t = Integer.parseInt(reader.readLine());
		DelegateTree<Integer, Integer> memo = new DelegateTree<Integer, Integer>();
		memo.setRoot(1);
		ArrayList<Integer> lastVisited = new ArrayList<Integer>();
		lastVisited.add(1);
		int count = 1;
		for (int i = 0; i < t; i++) {
			int n = Integer.parseInt(reader.readLine());
			StrBuilder builder = new StrBuilder("Case #").append(i + 1).append(": ");
			if (memo.containsVertex(n)) {
				builder.append(memo.getDepth(n) + 1).append('\n');
				String builderStr = builder.toString();
				System.out.print(builderStr);
				writer.write(builderStr);
				continue;
			}
			int ans = -1;
			while (true) {
//				System.out.println(count + "\t" + memo.getDepth(lastVisited.get(0)));
				ArrayList<Integer> nextVisit = new ArrayList<Integer>();
				for (Integer num : lastVisited) {
					if (num == n) {
						ans = memo.getDepth(num) + 1;
					}
					int flipNo = num + 1;
					if (!memo.containsVertex(flipNo)) {
						memo.addChild(count++, num, flipNo);
						nextVisit.add(flipNo);
					}
					flipNo = Integer.parseInt(StringUtils.reverse(num.toString().replaceFirst("^0*", "")));
					if (!memo.containsVertex(flipNo)) {
						memo.addChild(count++, num, flipNo);
						nextVisit.add(flipNo);
					}
				}
				lastVisited = nextVisit;
				if (ans != -1)
					break;
			}
			builder.append(ans).append('\n');
			String builderStr = builder.toString();
			System.out.print(builderStr);
			writer.write(builderStr);
		}
		reader.close();
		writer.close();
	}

}
