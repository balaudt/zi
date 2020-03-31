package zi.leet.easy;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class BuyAndSellTest {
    BuyAndSell solution = new BuyAndSell();

    @DataProvider
    public static Object[][] data() {
        return new Object[][]{
                {new int[]{7, 1, 5, 3, 6, 4}, 7},
                {new int[]{1, 2, 3, 4, 5}, 4}
        };
    }

    @Test(dataProvider = "data")
    public void testMaxProfit(int[] in, int expected) {
        assertEquals(solution.maxProfit(in), expected);
    }
}