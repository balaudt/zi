package zi.leet.fb;

import java.util.Iterator;

/**
 * @author balamurugan
 */
public class PartitionListTest {
	public void testPartition() {
		List in = new List();
		in.append(1);
		in.append(2);
		in.append(3);
		in.append(4);
		in.append(5);
		in.append(6);
		List out = new PartitionList().partitionOddEven(in);
		Iterator<Integer> it = out.iterator();
		int[] expectedOut = new int[]{1, 3, 5, 2, 4, 6};
		for (int i = 0; i < expectedOut.length; i++) {
			assert it.next() == expectedOut[i];
		}
		assert !it.hasNext();
	}
}
