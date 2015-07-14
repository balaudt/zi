package zi.cfc.r308;

import java.util.ArrayList;
import java.util.List;

public class LamdbaTutor {

	public static void main(String[] args) {
		List<Person> roster = new ArrayList<>();
		for (Person p : roster) {
			if (p.name.equals("test"))
				System.out.println(p);
		}
		CheckPerson tester = (Person p) -> p.name.equals("test");
		for (Person p : roster) {
			if (tester.test(p))
				System.out.println(p);
		}
	}
}

interface CheckPerson {
	boolean test(Person p);
}

class Person {
	public enum Sex {
		MALE, FEMALE;
	}

	String name;
	Sex gender;
	String email;

}