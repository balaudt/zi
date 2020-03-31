package zi.leet.easy;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.testng.Assert.*;

/**
 * @author balamurugan
 */
public class DeleteParenTest {
	private DeleteParen solution = new DeleteParen();

	@DataProvider
	public static Object[][] data() {
		return new Object[][]{
//				{"()())()", new String[]{"()()()", "(())()"}},
//				{"(a)())()", new String[]{"(a)()()", "(a())()"}},
//				{")(", new String[]{""}},
//				{"))(()(", new String[]{"()"}},
//				{"n", new String[]{"n"}},
				{"((()", new String[]{"()"}}
		};
	}

	@Test(dataProvider = "data")
	public void testRemoveInvalid(String in, String[] expected) {
		assertEquals(new HashSet<>(solution.removeInvalidParentheses(in)), new HashSet<>(Arrays.asList(expected)));
	}

}
