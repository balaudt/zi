package zi.leet.easy;

import org.testng.annotations.Test;
import zi.leet.ds.TreeNode;

import java.util.Arrays;

import static org.testng.Assert.*;

public class AverageLevelsBTTest {
    private AverageLevelsBT solution = new AverageLevelsBT();

    @Test
    public void testAverageOfLevels() {
        assertEquals(solution.averageOfLevels(TreeNode.createBST(new Integer[]{3, 9, 20, null, null, 15, 7})), Arrays.asList(3.0, 14.5, 11.0));
    }
}