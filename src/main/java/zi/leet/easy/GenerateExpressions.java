package zi.leet.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * @author balamurugan
 */
public class GenerateExpressions {
	private Set<Character> ops = new HashSet<>(Arrays.asList('+', '-', '*'));

	public List<String> addOperators(String num, int target) {
		int n = num.length();
		int max = (int) Math.pow(4, n - 1);
		List<String> result = new ArrayList<>();

		for (int i = 0; i < max; i++) {
			StringBuilder b = new StringBuilder(Integer.toString(i, 4));
			while (b.length() < n - 1) b.insert(0, '0');
			StringBuilder exp = new StringBuilder();
			exp.append(num.charAt(0));
			for (int j = 0; j < n - 1; j++) {
				switch (b.charAt(j)) {
					case '1':
						exp.append('+');
						break;
					case '2':
						exp.append('-');
						break;
					case '3':
						exp.append('*');
						break;
				}
				exp.append(num.charAt(j + 1));
			}
			if (evaluate(exp.toString()) == target) {
				result.add(exp.toString());
			}
		}
//		System.out.println(memo);
		return result;
	}

	private int evaluate(String in) {
		Deque<Integer> operands = new LinkedList<>();
		Deque<Character> operators = new LinkedList<>();
		int i = 0, n = in.length();
		while (i < n) {
			if (ops.contains(in.charAt(i))) {
				char op = in.charAt(i);
				while (!operators.isEmpty()) {
					if (operators.peek() == '*') {
						char lastOp = operators.pop();
						Integer l = operands.poll();
						Integer r = operands.poll();
						switch (lastOp) {
							case '+':
								operands.push(l + r);
								break;
							case '-':
								operands.push(l - r);
								break;
							case '*':
								operands.push(l * r);
						}
					} else {
						break;
					}
				}
				operators.push(op);
				i++;
				continue;
			}
			int num = 0;
			while (i < n && !ops.contains(in.charAt(i))) {
				num *= 10;
				num += in.charAt(i) - '0';
				i++;
			}
			operands.push(num);
		}

		while (!operators.isEmpty()) {
			char lastOp = operators.pop();
			Integer l = operands.poll();
			Integer r = operands.poll();
			switch (lastOp) {
				case '+':
					operands.push(l + r);
					break;
				case '-':
					operands.push(l - r);
					break;
				case '*':
					operands.push(l * r);
			}
		}
		return operands.poll();
	}
}
