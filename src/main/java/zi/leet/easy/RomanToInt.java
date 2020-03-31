package zi.leet.easy;

//https://leetcode.com/problems/roman-to-integer
public class RomanToInt {
    public int romanToInt(String s) {
        int out = 0;
        char[] digits = s.toCharArray();
        for (int i = 0; i < digits.length; i++) {
            switch (digits[i]) {
                case 'M':
                    out += 1000;
                    break;
                case 'D':
                    out += 500;
                    break;
                case 'C':
                    if (i + 1 < digits.length && (digits[i + 1] == 'D' || digits[i + 1] == 'M'))
                        out -= 100;
                    else
                        out += 100;
                    break;
                case 'L':
                    out += 50;
                    break;
                case 'X':
                    if (i + 1 < digits.length && (digits[i + 1] == 'L' || digits[i + 1] == 'C'))
                        out -= 10;
                    else
                        out += 10;
                    break;
                case 'V':
                    out += 5;
                    break;
                case 'I':
                    if (i + 1 < digits.length && (digits[i + 1] == 'V' || digits[i + 1] == 'X'))
                        out -= 1;
                    else
                        out += 1;
            }
        }
        return out;
    }
}
