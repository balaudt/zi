package zi.leet.medium;

//https://leetcode.com/problems/string-without-aaa-or-bbb
public class WithoutAB {
    public static String strWithout3a3b(int A, int B) {
        StringBuilder builder = new StringBuilder();
        while (A > 0 && B > 0) {
            if (A > B) {
                builder.append("aab");
                A -= 2;
                B -= 1;
            } else if (A < B) {
                builder.append("bba");
                B -= 2;
                A -= 1;
            } else {
                builder.append("ab");
                A -= 1;
                B -= 1;
            }
        }
        while (A > 0) {
            builder.append('a');
            A -= 1;
        }
        while (B > 0) {
            builder.append('b');
            B -= 1;
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        System.out.println(WithoutAB.strWithout3a3b(1,2));
        System.out.println(WithoutAB.strWithout3a3b(4,1));
    }
}
