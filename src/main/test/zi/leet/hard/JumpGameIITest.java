package zi.leet.hard;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class JumpGameIITest {
    private JumpGameII jumpGame = new JumpGameII();

    @DataProvider
    public static Object[][] data() {
        return new Object[][]{
                {new int[]{3, 2, 1}, 1},
                {new int[]{5, 9, 3, 2, 1, 0, 2, 3, 3, 1, 0, 0}, 3},
                {new int[]{2, 3, 1, 1, 4}, 2}
        };
    }

    @Test(dataProvider = "data")
    public void testJump(int[] in, int expected) {
        assertEquals(jumpGame.jump(in), expected);
    }
}