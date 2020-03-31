package zi.leet.easy;

import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.testng.Assert.*;

/**
 * @author balamurugan
 */
public class TwitterTest {
	@Test
	public void testFail1() {
		Twitter solution = new Twitter();
		solution.postTweet(1, 4);
		solution.postTweet(2, 5);
		solution.unfollow(1, 2);
		solution.follow(1, 2);
		assertEquals(solution.getNewsFeed(1), Arrays.asList(5, 4));
	}

	@Test
	public void testFail2() {
		Twitter solution = new Twitter();
		solution.postTweet(1, 5);
		solution.follow(1, 2);
		solution.follow(2, 1);
		assertEquals(solution.getNewsFeed(2), Collections.singletonList(5));
		solution.postTweet(2, 6);
		assertEquals(solution.getNewsFeed(1), Arrays.asList(6, 5));
		assertEquals(solution.getNewsFeed(2), Arrays.asList(6, 5));

	}
}
