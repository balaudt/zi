package zi.leet.fb;

/**
 * @author balamurugan
 */
public class PartitionList {

	List partitionOddEven(List in) {
		if (in == null) {
			return null;
		}
		List oddList = new List(), evenList = new List();
		for (Integer value : in) {
			if (value % 2 == 0) {
				evenList.append(value);
			} else {
				oddList.append(value);
			}
		}
		if (oddList.isEmpty()) {
			oddList.append(evenList);
			return oddList;
		} else {
			return evenList;
		}
	}
}
