package zi.leet.hard;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class RemoveDuplicateLettersTest {
    private RemoveDuplicateLetters solution = new RemoveDuplicateLetters();

    @DataProvider
    public static Object[][] data() {
        return new Object[][]{
//                {"bcabc", "abc"},
                {"cbacdcbc", "acdb"}
        };
    }

    @Test(dataProvider = "data")
    public void testRemoveDuplicateLetters(String in, String expected) {
        assertEquals(solution.removeDuplicateLetters(in), expected);
    }
}