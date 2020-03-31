package zi.leet.google;

import java.util.Arrays;

/**
 * @author balamurugan
 */
public class PlusOne {
	public int[] plusOne(int[] digits) {
		int carry = 1;
		int[] out = new int[digits.length];
		for (int i = digits.length - 1; i >= 0; i--) {
			int sum = digits[i] + carry;
			out[i] = sum % 10;
			carry = sum / 10;
		}
		if (carry > 0) {
			int temp[] = new int[out.length + 1];
			System.arraycopy(out, 0, temp, 1, out.length);
			temp[0] = carry;
			out = temp;
		}
		return out;
	}

	public static void main(String[] args) {
		PlusOne x = new PlusOne();
		System.out.println(Arrays.toString(x.plusOne(new int[]{1, 2, 3})));
		System.out.println(Arrays.toString(x.plusOne(new int[]{4, 3, 2, 1})));
		System.out.println(Arrays.toString(x.plusOne(new int[]{9, 9, 9, 9})));
	}
}
