package zi.leet.easy;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class BackspaceStringCompareTest {
    private BackspaceStringCompare solution = new BackspaceStringCompare();

    @DataProvider
    public static Object[][] data() {
        return new Object[][]{
                {"ab#c", "ad#c", true},
                {"ab##", "c#d#", true},
                {"a##c", "#a#c", true},
                {"a#c", "b", false},
                {"y#fo##f", "y#fx#o##f", true},
                {"isfcow#", "isfcog#w#", true}
        };
    }

    @Test(dataProvider = "data")
    public void testBackspaceCompare(String s, String t, boolean expected) {
        assertEquals(solution.backspaceCompare(s, t), expected);
    }
}