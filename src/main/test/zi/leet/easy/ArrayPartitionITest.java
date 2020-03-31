package zi.leet.easy;

import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class ArrayPartitionITest {
    private ArrayPartitionI solution = new ArrayPartitionI();

    @Test
    public void testArrayPairSum() {
        assertEquals(solution.arrayPairSum(new int[]{1, 4, 3, 2}), 4);
    }
}