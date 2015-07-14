package zi.jam.y15.qual;

import java.io.BufferedReader;
import java.io.FileReader;

import org.apache.commons.lang3.text.StrBuilder;

public class Dummy {

	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader("C:/Users/c0chaba/Desktop/tetranomino.txt"));
		String line = reader.readLine();
		StrBuilder builder = new StrBuilder();
		builder.append("ImmutableSet.<int[][]> of(");
		while (line != null) {
			if (line.trim().isEmpty()) {
				builder.deleteCharAt(builder.length() - 1).append(");");
				System.out.println(builder.toString());
				builder.clear().append("ImmutableSet.<int[][]> of(");
				line = reader.readLine();
				continue;
			}
			builder.append("new int[][]{");
			String[] split = line.split(" ");
			for (int i = 0; i < split.length; i++) {
				builder.append("{").append(split[i]).append("}").append(",");
			}
			builder.deleteCharAt(builder.length() - 1).append("},");
			line = reader.readLine();
		}
		reader.close();
	}
}

class AB {
	static String str = "A";
}

class BA extends AB {
	static String str = "B";

}