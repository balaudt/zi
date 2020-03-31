package zi.leet.fb;

import java.util.Iterator;

/**
 * @author balamurugan
 */
public class ListTest {
	public void testInsert() {
		List list = new List();
		list.append(10);
		assert list.head != null && list.head.value == 10;

		list.append(20);
		assert list.head.value == 10 && list.tail.value == 20;
	}

	public void testIterator() {
		List list = new List();
		list.append(10);
		list.append(20);
		Iterator<Integer> it = list.iterator();
		assert it.hasNext() && it.next() == 10;
		assert it.hasNext() && it.next() == 20;
		assert !it.hasNext();
	}

	public static void main(String[] args) {
		ListTest listTest = new ListTest();
		listTest.testInsert();
		listTest.testIterator();
	}
}
