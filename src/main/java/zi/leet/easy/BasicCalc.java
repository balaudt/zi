package zi.leet.easy;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author balamurugan
 */
public class BasicCalc {
	private Map<Character, Integer> precedence;

	public BasicCalc() {
		precedence = new HashMap<>();
		precedence.put('+', 0);
		precedence.put('-', 0);
		precedence.put('*', 1);
		precedence.put('/', 1);
	}

	public int calculate(String s) {
		Deque<Character> operators = new LinkedList<>();
		Deque<Integer> operands = new LinkedList<>();
		s = s.replaceAll(" ", "");
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			switch (c) {
				case '+':
				case '-':
				case '*':
				case '/':
					while (!operators.isEmpty() && operators.peek() != '(' && precedence.get(operators.peek()) >= precedence.get(c))
						process(operators, operands);
					operators.push(c);
					break;
				case '(':
					operators.push(c);
					break;
				case ')':
					while (operators.peek() != '(')
						process(operators, operands);
					operators.pop();
					break;
				default:
					int num = 0;
					while (i < s.length() && Character.isDigit(s.charAt(i))) {
						num = num * 10 + s.charAt(i) - '0';
						i++;
					}
					i--;
					operands.push(num);
			}
		}
		while (!operators.isEmpty())
			process(operators, operands);
		return operands.pop();
	}

	private void process(Deque<Character> operators, Deque<Integer> operands) {
		Character op = operators.pop();
		Integer o1 = operands.pop();
		Integer o2 = operands.isEmpty() ? 0 : operands.pop();
		switch (op) {
			case '+':
				operands.push(o1 + o2);
				break;
			case '-':
				operands.push(o2 - o1);
				break;
			case '*':
				operands.push(o1 * o2);
				break;
			case '/':
				operands.push(o2 / o1);
		}
	}
}
