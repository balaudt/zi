package zi.jam.apac15.rA;

import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.bidimap.DualHashBidiMap;
import org.apache.commons.lang3.text.StrBuilder;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Addition {

	static BidiMap<Integer, String> variableMap;
	static int varCount;
	static Pattern inPattern = Pattern.compile("(\\w+)\\+(\\w+)=(\\-?\\d+)");
	static Pattern outPattern = Pattern.compile("(\\w+)\\+(\\w+)");
	static Map<Left, Integer> knownFacts;
	static Stack<Left> usedFacts;

	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader("A-small-practice.in"));
		BufferedWriter writer = new BufferedWriter(new FileWriter("A-small-practice.out"));
		int t = Integer.parseInt(reader.readLine());
		for (int i = 0; i < t; i++) {
			writer.write(new StrBuilder("Case #").append(i + 1).append(":\n").toString());
			variableMap = new DualHashBidiMap<Integer, String>();
			varCount = 0;
			knownFacts = new HashMap<Left, Integer>();
			int n = Integer.parseInt(reader.readLine());
			for (int j = 0; j < n; j++) {
				Object[] line = getFact(reader.readLine());
				Integer vName1 = variableMap.getKey(line[0]);
				if (vName1 == null) {
					variableMap.put(varCount, (String) line[0]);
					vName1 = varCount++;
				}
				Integer vName2 = variableMap.getKey(line[1]);
				if (vName2 == null) {
					variableMap.put(varCount, (String) line[1]);
					vName2 = varCount++;
				}
				Left newFact = vName1 <= vName2 ? new Left(vName1, vName2, false) : new Left(vName2, vName1, false);
				if (knownFacts.containsKey(newFact)) {
					// Equation already added.
					continue;
				}
				knownFacts.put(newFact, (Integer) line[2]);
				usedFacts = new Stack<Left>();
				inferPossibleFacts(newFact);
			}
			System.out.println(knownFacts);
			int q = Integer.parseInt(reader.readLine());
			for (int j = 0; j < q; j++) {
				String inLine = reader.readLine();
				String[] question = getQuestion(inLine);
				Left requiredLeft = null;
				try {
					Integer vName1 = variableMap.getKey(question[0]);
					Integer vName2 = variableMap.getKey(question[1]);
					requiredLeft = vName1 <= vName2 ? new Left(vName1, vName2, false) : new Left(vName2, vName1, false);
				} catch (Exception e) {
					// Means question related to unmapped variable is asked
					// which could of course can't be answered
					continue;
				}
				// System.out.println(requiredLeft);
				if (knownFacts.containsKey(requiredLeft)) {
					writer.write(inLine + "=" + knownFacts.get(requiredLeft) + "\n");
				}
			}
		}
		reader.close();
		writer.close();
	}

	static void inferPossibleFacts(Left newFact) throws Exception {
		usedFacts.push(newFact);
		System.out.println("Call to infer from " + newFact + " combined aganist " + knownFacts + " given that " + usedFacts
				+ " have been already used");
		HashSet<Left> copyOfKnown = new HashSet<Left>(knownFacts.keySet());
		for (Left knownFact : copyOfKnown) {
			if (usedFacts.contains(knownFact))
				continue;
			Map<Left, Integer> possibleFacts = inferPossibleFacts(knownFact, newFact);
			for (Entry<Left, Integer> entry : possibleFacts.entrySet()) {
				Left mostRecentFact = entry.getKey();
				if (knownFacts.containsKey(mostRecentFact))
					continue;
				knownFacts.put(mostRecentFact, entry.getValue());
				inferPossibleFacts(mostRecentFact);
			}
		}
		usedFacts.pop();
	}

	static Map<Left, Integer> inferPossibleFacts(Left knownFact, Left newFact) throws Exception {
		// System.out.println(knownFact + "\t" + newFact);
		Map<Left, Integer> output = new HashMap<Left, Integer>();
		for (Op op : Op.values()) {
			Map<Integer, Integer> varIndices = addIndex(knownFact, newFact, op);
			int result;
			switch (op) {
			case SUB1:
				result = knownFacts.get(knownFact) - knownFacts.get(newFact);
				break;
			case SUB2:
				result = -knownFacts.get(knownFact) + knownFacts.get(newFact);
				break;
			case ADD:
				result = knownFacts.get(knownFact) + knownFacts.get(newFact);
				break;
			default:
				result = 0;
			}
			Set<Integer> toRemove = new HashSet<Integer>();
			for (Entry<Integer, Integer> entry : varIndices.entrySet()) {
				if (entry.getValue() == 0)
					toRemove.add(entry.getKey());
			}
			for (Integer key : toRemove) {
				varIndices.remove(key);
			}
			if (varIndices.size() == 1) {
				Integer varVal = varIndices.values().iterator().next();
				Integer varInd = varIndices.keySet().iterator().next();
				if (varVal == -2) {
					result *= -1;
				} else if (varVal != 2) {
					throw new Exception("Single variable with more than 2 inferred!");
				}
				Left inferedFact = new Left(varInd, varInd, false);
				output.put(inferedFact, result);
				// TODO If knownFact/newFact is a conjugate of inferedFact, it
				// is a special case where in another fact can be inferred
				continue;
			}
			if (varIndices.size() == 2) {
				List<Integer> values = new ArrayList<Integer>(varIndices.values());
				List<Integer> keys = new ArrayList<Integer>(varIndices.keySet());
				if (Math.abs(values.get(0)) != 1 || Math.abs(values.get(1)) != 1) {
					continue;
				}
				Left inferedFact = null;
				if (values.get(0) == -1 && values.get(1) == -1) {
					result *= -1;
					Integer key0 = keys.get(0);
					Integer key1 = keys.get(1);
					inferedFact = key0 <= key1 ? new Left(key0, key1, false) : new Left(key1, key0, false);
				} else if (values.get(0) == 1 && values.get(1) == 1) {
					Integer key0 = keys.get(0);
					Integer key1 = keys.get(1);
					inferedFact = key0 <= key1 ? new Left(key0, key1, false) : new Left(key1, key0, false);
				} else if (values.get(0) == -1 && values.get(1) == 1) {
					inferedFact = new Left(keys.get(1), keys.get(0), true);
				} else if (values.get(0) == 1 && values.get(1) == -1) {
					inferedFact = new Left(keys.get(0), keys.get(1), true);
				}
				if (isOppositeOfExisting(inferedFact, knownFacts.keySet()) || knownFacts.containsKey(inferedFact))
					continue;
				output.put(inferedFact, result);
				knownFacts.put(inferedFact, result);
				inferPossibleFacts(inferedFact);
				if (inferedFact.isConjugate(newFact)) {
					Map<Left, Integer> specialFacts = inferPossibleFacts(newFact, inferedFact);
					for (Entry<Left, Integer> fact : specialFacts.entrySet()) {
						if (!fact.equals(knownFact)) {
							output.put(fact.getKey(), fact.getValue());
						}
					}
				} else if (inferedFact.isConjugate(knownFact)) {
					Map<Left, Integer> specialFacts = inferPossibleFacts(knownFact, inferedFact);
					for (Entry<Left, Integer> fact : specialFacts.entrySet()) {
						if (!fact.equals(newFact)) {
							output.put(fact.getKey(), fact.getValue());
						}
					}
				}
				continue;
			}
			// System.out.println("More than two variables inferred");
		}
		return output;
	}

	static boolean isOppositeOfExisting(Left newFact, Set<Left> existingFacts) {
		for (Left left : existingFacts) {
			if (left.isOpposite(newFact)) {
				return true;
			}
		}
		return false;
	}

	public static void main2(String[] args) throws Exception {
		// step1Test();
		// step2Test();
		step3Test();
	}

	static void step2Test() throws Exception {
		knownFacts = new HashMap<Left, Integer>();
		Left knownFact = new Left(0, 1, false);
		// Left knownFact = new Left(0, 1, true);
		// Left knownFact = new Left(0, 0, false);
		knownFacts.put(knownFact, 10);
		Left newFact = new Left(0, 2, false);
		// Left newFact = new Left(0, 1, false);
		// Left newFact = new Left(0, 1, false);
		knownFacts.put(newFact, 15);
		System.out.println(inferPossibleFacts(knownFact, newFact));
	}

	static void step3Test() throws Exception {
		knownFacts = new HashMap<Left, Integer>();
		usedFacts = new Stack<Left>();
		Left knownFact = new Left(0, 1, false);
		// Left knownFact = new Left(0, 1, true);
		// Left knownFact = new Left(0, 0, false);
		knownFacts.put(knownFact, 10);
		Left newFact = new Left(0, 2, false);
		// Left newFact = new Left(0, 1, false);
		// Left newFact = new Left(0, 1, false);
		knownFacts.put(newFact, 15);
		inferPossibleFacts(newFact);
		System.out.println(knownFacts);
	}

	static void step1Test() {
		System.out.println(addIndex(new Left(0, 0, false), new Left(0, 1, false), Op.SUB1));
	}

	static Map<Integer, Integer> addIndex(Left fact1, Left fact2, Op op) {
		Map<Integer, Integer> varIndices = new TreeMap<Integer, Integer>();
		Integer v = varIndices.get(fact1.v1);
		if (v == null) {
			v = 0;
		}
		if (op == Op.SUB2)
			v--;
		else
			v++;
		varIndices.put(fact1.v1, v);
		v = varIndices.get(fact1.v2);
		if (v == null) {
			v = 0;
		}
		if ((!fact1.s2 && op == Op.SUB2) || (fact1.s2 && op != Op.SUB2))
			v--;
		else
			v++;
		varIndices.put(fact1.v2, v);

		v = varIndices.get(fact2.v1);
		if (v == null) {
			v = 0;
		}
		if (op == Op.SUB1)
			v--;
		else
			v++;
		varIndices.put(fact2.v1, v);
		v = varIndices.get(fact2.v2);
		if (v == null) {
			v = 0;
		}
		if ((!fact2.s2 && op == Op.SUB1) || (fact2.s2 && op != Op.SUB1))
			v--;
		else
			v++;
		varIndices.put(fact2.v2, v);
		return varIndices;
	}

	static String[] getQuestion(String in) {
		Matcher matcher = outPattern.matcher(in);
		matcher.find();
		return new String[] { matcher.group(1), matcher.group(2) };
	}

	static Object[] getFact(String in) {
		Matcher matcher = inPattern.matcher(in);
		matcher.find();
		return new Object[] { matcher.group(1), matcher.group(2), Integer.parseInt(matcher.group(3)) };
	}

	static class Left {
		int v1, v2;
		boolean s2;

		public Left() {
		}

		public Left(int v1, int v2, boolean s2) {
			super();
			this.v1 = v1;
			this.v2 = v2;
			this.s2 = s2;
		}

		@Override
		public String toString() {
			StrBuilder builder = new StrBuilder(variableMap.get(v1));
			if (s2) {
				builder.append("-");
			} else {
				builder.append("+");
			}
			builder.append(variableMap.get(v2));
			return builder.toString();
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + (s2 ? 1231 : 1237);
			result = prime * result + v1;
			result = prime * result + v2;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Left other = (Left) obj;
			if (s2 != other.s2)
				return false;
			if (v1 != other.v1)
				return false;
			if (v2 != other.v2)
				return false;
			return true;
		}

		public boolean isOpposite(Left anotherFact) {
			return v1 == anotherFact.v2 && v2 == anotherFact.v1 && s2 == anotherFact.s2 && s2 == true;
		}

		public boolean isConjugate(Left a) {
			if (s2 == a.s2)
				return false;
			int[] left = v1 >= v2 ? new int[] { v1, v2 } : new int[] { v2, v1 };
			int[] right = a.v1 >= a.v2 ? new int[] { a.v1, a.v2 } : new int[] { a.v2, a.v1 };
			return Arrays.equals(left, right);
		}
	}
}

enum Op {
	ADD, SUB1, SUB2;
}
