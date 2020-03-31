package zi.leet.hard;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author balamurugan
 */
public class StringDecoder {
	public String decodeString(String s) {
		StringBuilder b;
		Deque<String> st = new LinkedList<>();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (Character.isDigit(c)) {
				b = new StringBuilder();
				for (; Character.isDigit(s.charAt(i)); i++)
					b.append(s.charAt(i));
				st.push(b.toString());
				System.out.println(st);
				continue;
			}
			if (c != ']') {
				st.push("" + c);
				System.out.println(st);
				continue;
			}
			//c==']'
			b = new StringBuilder();
			while (!Character.isDigit(st.peek().charAt(0)))
				b.append(st.pop());
			String cur = b.toString();
			int ct = Integer.parseInt(st.pop());

			b = new StringBuilder();
			for (int j = 0; j < ct; j++)
				b.append(cur);
			st.push(b.toString());

			System.out.println(st);
		}

		b = new StringBuilder();
		while (!st.isEmpty())
			b.append(st.pop());
		return b.reverse().toString();
	}
}
