package zi.leet.easy;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.*;

public class CommonCharactersTest {
    private CommonCharacters solution = new CommonCharacters();

    @DataProvider
    public static Object[][] data() {
        return new Object[][]{
                {new String[]{"bella", "label", "roller"}, Arrays.asList("e", "l", "l")},
                {new String[]{"cool", "lock", "cook"}, Arrays.asList("c", "o")}
        };
    }

    @Test
    public void testCommonChars(String[] in, List<String> expected) {
        assertEquals(solution.commonChars(in), expected);
    }
}