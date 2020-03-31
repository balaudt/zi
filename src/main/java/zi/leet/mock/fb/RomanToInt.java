package zi.leet.mock.fb;

/**
 * @author balamurugan
 */
//https://leetcode.com/problems/roman-to-integer/
public class RomanToInt {
	public int romanToInt(String s) {
		char[] c = s.toCharArray();
		int result = 0;
		for (int i = 0; i < c.length; i++) {
			char next = (i + 1) < c.length ? c[i + 1] : ' ';
			switch (c[i]) {
				case 'M':
					result += 1000;
					break;
				case 'D':
					result += 500;
					break;
				case 'C':
					switch (next) {
						case 'D':
							result += 400;
							i++;
							break;
						case 'M':
							result += 900;
							i++;
							break;
						default:
							result += 100;
					}
					break;
				case 'L':
					result += 50;
					break;
				case 'X':
					switch (next) {
						case 'L':
							result += 40;
							i++;
							break;
						case 'C':
							result += 90;
							i++;
							break;
						default:
							result += 10;
					}
					break;
				case 'V':
					result += 5;
					break;
				case 'I':
					switch (next) {
						case 'V':
							result += 4;
							i++;
							break;
						case 'X':
							result += 9;
							i++;
							break;
						default:
							result += 1;
					}
			}
		}
		return result;
	}
}
