package zi.hrank.y15.w15;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

//TODO - Incomplete [testing & debugging]
public class B {

	public static final String FOLDER_ROOT = "/home/bala/temp/3/";

	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(new FileInputStream(FOLDER_ROOT + "B-sample.in"));
		// Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		ArrayList<Person> in = new ArrayList<Person>(n);
		for (int i = 0; i < n; i++) {
			Person p = new Person(scanner.nextInt(), scanner.nextInt());
			int pos = Collections.binarySearch(in, p);
			if (pos < 0)
				in.add(-pos - 1, p);
			else
				in.add(pos, p);
		}
		// System.out.println(in);
		int pointer = n - 1;
		int peopleInGrp = 0;
		Comparator<Person> minComparator = new Comparator<Person>() {

			public int compare(Person o1, Person o2) {
				return o1.min - o2.min;
			}
		};
		while (true) {
			// Step 1 - Add person with max max values in group
			/**
			 * For the sake of step 3, while moving pointer, we are also making
			 * sure that the sublist [pointer,in.size] is always sorted
			 * descending by min
			 */
			int curMax = in.get(pointer--).max;
			peopleInGrp++;
			int curSize = in.size();
			Person p = in.get(pointer);
			while (p.max == curMax) {
				int pos = Collections.binarySearch(in.subList(pointer, curSize - 1), p, minComparator);
				int ip = -pos - 1;
				in.remove(pointer);
				in.add(pointer + ip, p);
				pointer--;
				peopleInGrp++;
				p = in.get(pointer);
			}
			// Step 2 - Check if no of people in group >= curMax
			if (peopleInGrp <= curMax)
				continue;
			/**
			 * Step 3 - Check if min constraint holds for all people in the
			 * group and if not eliminate persons who don't hold the constraint <br />
			 * Under the condition that no of persons in the group doesn't
			 * become < curMax <br />
			 * For optimization, elimination should happen such that person with
			 * max min value be eliminated first <br />
			 */
			int count = 1;
			while (peopleInGrp > curMax) {
				p = in.get(curSize - count);
				if (p.min < peopleInGrp)
					break;
				in.remove(curSize - count);
				count++;
			}
			// Step 4 - Check if min constraint holds for the last person in the
			// group
			if (in.get(curSize - count).min < peopleInGrp) {
				System.out.println(curSize - count);
				break;
			}
		}
		scanner.close();
	}

}

class Person implements Comparable<Person> {
	int min, max;

	public Person(int min, int max) {
		this.min = min;
		this.max = max;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + max;
		result = prime * result + min;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (max != other.max)
			return false;
		if (min != other.min)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "{" + min + "," + max + "}";
	}

	public int compareTo(Person o) {
		if (o.max != max)
			return max - o.max;
		return min - o.min;
	}
}
