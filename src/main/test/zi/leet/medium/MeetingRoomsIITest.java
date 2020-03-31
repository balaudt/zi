package zi.leet.medium;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class MeetingRoomsIITest {
    private MeetingRoomsII solution = new MeetingRoomsII();

    @DataProvider
    public static Object[][] data() {
        return new Object[][]{
                {new int[][]{{0, 30}, {5, 10}, {15, 20}}, 2}
        };
    }

    @Test(dataProvider = "data")
    public void testMinMeetingRooms(int[][] in, int expected) {
        assertEquals(solution.minMeetingRooms(in), expected);
    }
}