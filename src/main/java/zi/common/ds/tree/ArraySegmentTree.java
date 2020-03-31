package zi.common.ds.tree;

import java.util.function.BinaryOperator;

/**
 * @author balamurugan
 */
public class ArraySegmentTree<T> implements SegmentTree<T> {
	private final BinaryOperator<T> merger;
	private final int size;

	public ArraySegmentTree(T[] initialData, BinaryOperator<T> merger){
		this.merger = merger;
		this.size = initialData.length;

	}
	@Override
	public T query(int start, int end) {
		return null;
	}

	@Override
	public void update(int position, T newValue) {

	}
}
