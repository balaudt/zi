package zi.chef.y16.snck.r1B;

import java.util.Arrays;

/**
 * Created by balaudt on 6/19/16.
 */
public class ChocolatesNew {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(ExtendedEuclid(4, 6)));
    }

    public static int[] ExtendedEuclid(int a, int b)
    /*  This function will perform the Extended Euclidean algorithm
        to find the GCD of a and b.  We assume here that a and b
        are non-negative (and not both zero).  This function also
        will return numbers j and k such that
               d = j*a + k*b
        where d is the GCD of a and b.
    */ {
        int[] ans = new int[3];
        int q;

        if (b == 0) {  /*  If b = 0, then we're done...  */
            ans[0] = a;
            ans[1] = 1;
            ans[2] = 0;
        } else {     /*  Otherwise, make a recursive function call  */
            q = a / b;
            ans = ExtendedEuclid(b, a % b);
            int temp = ans[1] - ans[2] * q;
            ans[1] = ans[2];
            ans[2] = temp;
        }

        return ans;
    }
}
