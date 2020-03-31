package zi.common.ds.tree;

/**
 * @author balamurugan
 */
public interface SegmentTree<T> {
	T query(int start, int end);

	void update(int position, T newValue);
}
