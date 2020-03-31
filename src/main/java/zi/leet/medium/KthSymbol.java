package zi.leet.medium;

//https://leetcode.com/problems/k-th-symbol-in-grammar
public class KthSymbol {
    public int kthGrammar(int N, int K) {
        if (N == 1)
            return 0;
        if (N == 2)
            return K == 0 ? 0 : 1;
        int last = kthGrammar(N - 1, K / 2);
        return K % 2 != 0 ? last & 1 : last & 2;
    }

    public static void main(String[] args) {
        KthSymbol kthSymbol = new KthSymbol();
        System.out.println(kthSymbol.kthGrammar(1, 1));
        System.out.println(kthSymbol.kthGrammar(2, 1));
        System.out.println(kthSymbol.kthGrammar(2, 2));
        System.out.println(kthSymbol.kthGrammar(4, 5));
    }
}
