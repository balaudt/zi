package zi.leet.easy;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class AnagramsInStringTest {
    private AnagramsInString solution = new AnagramsInString();

    @DataProvider
    public static Object[][] data() {
        return new Object[][]{
                {"cbaebabacd", "abc", Arrays.asList(0, 6)},
                {"abab", "ab", Arrays.asList(0, 1, 2)}
        };
    }

    @Test(dataProvider = "data")
    public void testFindAnagrams(String s, String p, List<Integer> expected) {
        assertEquals(solution.findAnagrams(s, p), expected);
    }
}