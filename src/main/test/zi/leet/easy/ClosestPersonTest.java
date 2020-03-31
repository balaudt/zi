package zi.leet.easy;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ClosestPersonTest {
    private ClosestPerson solution = new ClosestPerson();

    @Test
    public void testMaxDistToClosest() {
        assertEquals(solution.maxDistToClosest(new int[]{1, 0, 0, 0}), 3);
    }
}