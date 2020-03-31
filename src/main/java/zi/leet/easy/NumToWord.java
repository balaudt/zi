package zi.leet.easy;

/**
 * @author balamurugan
 */
public class NumToWord {
	String[] units = new String[]{"Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
	String[] elevens = new String[]{"", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
	String[] tens = new String[]{"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};

	public String numberToWords(int num) {
		String least = convertLeast(num);
		System.out.println(least);
		if (num < 100) return least;
		num /= 100;
		String hundreds = "";
		if (num % 10 != 0) {
			hundreds = units[num % 10] + " Hundred";
			System.out.println(hundreds);
		}
		if (num < 1000)
			return hundreds + " " + least;
		return "";
	}

	private String convertLeast(int n) {
		int t = n % 100;
		if (t < 10) return units[t];
		else if (t % 10 == 0) return tens[t / 10];
		else if (t < 20) return elevens[t - 10];
		return tens[t / 10] + " " + units[t % 10];
	}
}
