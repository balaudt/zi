package zi.leet.easy;

import spire.math.algebraic.Sub;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * @author balamurugan
 */
public class ExpressionEvaluation {
	class SubExp {
		boolean isOp;
		char op;
		int num;

		SubExp(char op) {
			isOp = true;
			this.op = op;
		}

		SubExp(int num) {
			isOp = false;
			this.num = num;
		}

		public String toString() {
			return isOp ? "" + op : "" + num;
		}
	}

	Comparator<Character> preComp = (c1, c2) -> {
		switch (c1) {
			case '+':
			case '-':
				if (c2 == '+' || c2 == '-') return 0;
				else return -1;
			case '*':
			case '/':
				if (c2 == '*' || c2 == '/') return 0;
				else return 1;
		}
		return 0;
	};
	Set<Character> operators = new HashSet<>(Arrays.asList('+', '-', '*', '/'));

	public int calculate(String s) {
		List<SubExp> postfix = postfix(s.replaceAll(" ", ""));
		System.out.println(postfix);
		return evaluate(postfix);
	}

	private List<SubExp> postfix(String infix) {
		char[] in = infix.toCharArray();
		List<SubExp> postFix = new ArrayList<>();
		Deque<Character> stackOps = new LinkedList<>();
		for (int i = 0; i < in.length; i++) {
			char op = in[i];
			if (operators.contains(op)) {
				while (!stackOps.isEmpty() &&
						preComp.compare(stackOps.peek(), op) > 0) {
					postFix.add(new SubExp(stackOps.pop()));
				}
				stackOps.push(op);
				continue;
			}
			int num = 0;
			while (i < in.length && !operators.contains(in[i])) {
				num = num * 10 + in[i] - '0';
				i++;
			}
			postFix.add(new SubExp(num));
			i--;
		}
		while (!stackOps.isEmpty()) postFix.add(new SubExp(stackOps.pop()));
		return postFix;
	}

	private int evaluate(List<SubExp> postfix) {
		Deque<Integer> operands = new LinkedList<>();
		for (int i = 0; i < postfix.size(); i++) {
			SubExp subExp = postfix.get(i);
			if (subExp.isOp) {
				Integer r = operands.pop();
				Integer l = operands.pop();
				switch (subExp.op) {
					case '+':
						operands.push(l + r);
						break;
					case '-':
						operands.push(l - r);
						break;
					case '*':
						operands.push(l * r);
						break;
					case '/':
						if (r == 0) operands.push(0);
						else operands.push(l / r);
						break;
				}
			} else {
				operands.push(subExp.num);
			}
		}
		return operands.pop();
	}
}
