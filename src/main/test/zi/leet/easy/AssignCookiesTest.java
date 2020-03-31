package zi.leet.easy;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class AssignCookiesTest {
    private AssignCookies solution = new AssignCookies();

    @Test
    public void testFindContentChildren() {
        assertEquals(solution.findContentChildren(new int[]{1, 2, 3}, new int[]{1, 1}), 1);
    }
}