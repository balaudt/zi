package zi.euler;


public class SelfPowers48 {

    public static void main(String[] args) {
        long prods[] = new long[1000];
        for (int i = 1; i <= 1000; i++) {
            Long prod = 1l;
            for (int j = 1; j <= i; j++) {
                prod *= i;
                String prodStr = prod.toString();
                if (prodStr.length() > 10)
                    prod = Long.parseLong(prodStr.substring(prodStr.length() - 10, prodStr.length()));
            }
            prods[i - 1] = prod;
        }
//        System.out.println(Arrays.toString(prods));
        long sum = 0;
        for (int i = 0; i < prods.length; i++) {
            sum += prods[i];
        }
        System.out.println(sum);
    }
}


