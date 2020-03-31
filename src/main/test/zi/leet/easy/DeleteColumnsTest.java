package zi.leet.easy;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class DeleteColumnsTest {
    private DeleteColumns solution = new DeleteColumns();

    @DataProvider
    public static Object[][] data() {
        return new Object[][]{
                {new String[]{"cba", "daf", "ghi"}, 1},
                {new String[]{"a", "b"}, 0},
                {new String[]{"zyx", "wvu", "tsr"}, 3}
        };
    }

    @Test(dataProvider = "data")
    public void testMinDeletionSize(String[] in, int expected) {
        assertEquals(solution.minDeletionSize(in), expected);
    }
}