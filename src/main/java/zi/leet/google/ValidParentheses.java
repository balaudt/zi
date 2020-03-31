package zi.leet.google;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author balamurugan
 */
public class ValidParentheses {
	public boolean isValid(String s) {
		Stack<Character> unclosedParens = new Stack<>();
		Map<Character, Character> matchingParens = new HashMap<Character, Character>() {
			{
				put(')', '(');
				put('}', '{');
				put(']', '[');
			}
		};
		for (char c : s.toCharArray()) {
			if (matchingParens.keySet().contains(c)) {
				if (unclosedParens.isEmpty()) {
					return false;
				}
				if (unclosedParens.pop() != matchingParens.get(c)) {
					return false;
				}
			} else {
				unclosedParens.push(c);
			}
		}
		return unclosedParens.isEmpty();
	}

	public static void main(String[] args) {
		ValidParentheses validParentheses = new ValidParentheses();
		System.out.println(validParentheses.isValid("()"));
		System.out.println(validParentheses.isValid("()[]{}"));
		System.out.println(validParentheses.isValid("(]"));
		System.out.println(validParentheses.isValid("([)]"));
		System.out.println(validParentheses.isValid("{[]}"));
	}
}
