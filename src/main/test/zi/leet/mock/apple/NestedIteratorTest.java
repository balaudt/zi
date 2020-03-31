package zi.leet.mock.apple;

import org.testng.annotations.Test;
import zi.leet.ds.NestedIntegerImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * @author balamurugan
 */
public class NestedIteratorTest {
	@Test
	public void test() {

		NestedIntegerImpl e1 = new NestedIntegerImpl();
		e1.setInteger(false);
		e1.setList(Arrays.asList(new NestedIntegerImpl(1), new NestedIntegerImpl(2)));

		NestedIntegerImpl e3 = new NestedIntegerImpl();
		e3.setInteger(false);
		e3.setList(Arrays.asList(new NestedIntegerImpl(4), new NestedIntegerImpl(5)));

		NestedIterator it = new NestedIterator(Arrays.asList(e1, new NestedIntegerImpl(3), e3));
		int count = 0;
		while (it.hasNext() && count < 20) {
			System.out.println(it.next());
			count++;
		}
	}

	@Test
	public void emptyTest() {
		NestedIntegerImpl e1 = new NestedIntegerImpl();
		e1.setInteger(false);
		e1.setList(new ArrayList<>());
		NestedIterator it = new NestedIterator(Collections.singletonList(e1));
		while (it.hasNext()) {
			System.out.println(it.next());
		}
	}
}
