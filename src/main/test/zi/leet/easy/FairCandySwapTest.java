package zi.leet.easy;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class FairCandySwapTest {
    private FairCandySwap solution = new FairCandySwap();

    @DataProvider
    public static Object[][] data() {
        return new Object[][]{
                {new int[]{1, 1}, new int[]{2, 2}},
                {new int[]{1, 2}, new int[]{2, 3}},
                {new int[]{2}, new int[]{1, 3}},
                {new int[]{1, 2, 5}, new int[]{2, 4}}
        };
    }

    @Test(dataProvider = "data")
    public void testFairCandySwap(int[] a, int[] b, int[] expected) {
        assertEquals(solution.fairCandySwap(a, b), expected);
    }
}