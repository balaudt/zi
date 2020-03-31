package zi.leet.ds;

import java.util.List;

/**
 * @author balamurugan
 */
public class NestedIntegerImpl implements NestedInteger {
	private boolean isInteger;
	private Integer integer;
	private List<NestedInteger> list;

	public NestedIntegerImpl(Integer integer) {
		this.integer = integer;
		isInteger = true;
	}

	public NestedIntegerImpl() {
	}

	public boolean isInteger() {
		return isInteger;
	}

	public void setInteger(boolean integer) {
		isInteger = integer;
	}

	public Integer getInteger() {
		return integer;
	}

	public void setInteger(Integer integer) {
		this.integer = integer;
	}

	public List<NestedInteger> getList() {
		return list;
	}

	public void setList(List<NestedInteger> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("NII{");
		sb.append("n=").append(isInteger);
		sb.append(",v=").append(integer);
		sb.append(",l=").append(list);
		sb.append('}');
		return sb.toString();
	}
}
