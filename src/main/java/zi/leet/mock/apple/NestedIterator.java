package zi.leet.mock.apple;

import zi.leet.ds.NestedInteger;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author balamurugan
 */
public class NestedIterator implements Iterator<Integer> {
	private Iterator<Integer> currentIterator = null;
	private int nextInd = -1;
	private List<NestedInteger> list;

	public NestedIterator(List<NestedInteger> nestedList) {
		this.list = nestedList;
	}


	@Override
	public Integer next() {
		return currentIterator.next();
	}

	@Override
	public boolean hasNext() {
		if (list == null || list.isEmpty()) return false;
		if (currentIterator != null && currentIterator.hasNext()) return true;
		nextInd++;
		while (nextInd < list.size()) {
			NestedInteger next = list.get(nextInd);
			currentIterator = next.isInteger() ? Collections.singletonList(next.getInteger()).iterator() :
					new NestedIterator(next.getList());
			if (currentIterator.hasNext()) return true;
		}
		PriorityQueue<Map.Entry<Integer, Integer>> q = new PriorityQueue<>(Comparator.comparingInt(Map.Entry::getKey));
		return false;
	}
}
