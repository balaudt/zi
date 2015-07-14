package zi.euler;

public class NonMersennePrime97 {

	public static void main(String[] args) {
		Long prod = 1l;
		for (int i = 1; i <= 7830457; i++) {
			prod *= 2;
			// System.out.println(prod);
			String prodStr = prod.toString();
			if (prodStr.length() > 10)
				prod = Long.parseLong(prodStr.substring(prodStr.length() - 11, prodStr.length()));
		}
		prod *= 28433;
		prod++;
		System.out.println(prod % 1e10);
	}
}
