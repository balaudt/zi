package zi.leet.hard;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class WildcardMatchingTest {
    private WildcardMatching solution = new WildcardMatching();

    @DataProvider
    public static Object[][] data() {
        return new Object[][]{
                {"aa", "a", false},
                {"aa", "*", true},
                {"cb", "?a", false},
                {"adceb", "*a*b", true},
                {"acdcb", "a*c?b", false}
        };
    }

    @Test(dataProvider = "data")
    public void testIsMatch(String s, String p, boolean expected) {
        assertEquals(solution.isMatch(s, p), expected);
    }
}