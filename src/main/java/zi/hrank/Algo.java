package zi.hrank;

import java.util.Map;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Algo {

	public static void main(String[] args) {
		char[] in = "[ { ( ) ]".toCharArray();
		Stack<Character> stk = new Stack<>();
		boolean isValid = true;
		Map<Character, Character> map = Stream.<String> of("{}", "[]", "()")
				.collect(Collectors.<String, Character, Character> toMap(k -> k.charAt(1), v -> v.charAt(0)));
		for (int i = 0; i < in.length; i++) {
			if (in[i] == ' ')
				continue;
			if (in[i] == '(' || in[i] == '[' || in[i] == '{')
				stk.push(in[i]);
			else if (stk.isEmpty()) {
				isValid = false;
				break;
			} else {
				if (stk.pop() != map.get(in[i])) {
					isValid = false;
					break;
				}
			}
		}
		if (isValid && stk.isEmpty())
			System.out.println("Valid");
		else
			System.out.println("Invalid");
	}
}
