package zi.leet.easy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * @author balamurugan
 */
public class Twitter {
	private int counter = 0;

	class Tweet {
		int tweetId;
		int ts;

		Tweet(int tweetId) {
			this.tweetId = tweetId;
			ts = counter++;
		}

	}

	class Node implements Comparable<Node> {
		Node prev, next;
		Tweet t;

		public int compareTo(Node o) {
			return o.t.ts - t.ts;
		}
	}

	class LinkedList {
		Node head, tail;
		int size;

		public void add(Tweet t) {
			Node n = new Node();
			n.t = t;
			if (head == null) {
				head = n;
				tail = n;
				size = 1;
				return;
			}
			tail.next = n;
			n.prev = tail;
			tail = n;
			size++;
			if (size == 11) {
				head = head.next;
				size--;
			}
		}
	}

	private Map<Integer, LinkedList> userTweets = new HashMap<>();
	private Map<Integer, Set<Integer>> userFollowers = new HashMap<>();

	/**
	 * Initialize your data structure here.
	 */
	public Twitter() {

	}

	/**
	 * Compose a new tweet.
	 */
	public void postTweet(int userId, int tweetId) {
		LinkedList l = userTweets.getOrDefault(userId, new LinkedList());
		l.add(new Tweet(tweetId));
		userTweets.put(userId, l);
		if (!userFollowers.containsKey(userId)) {
			Set<Integer> followers = new HashSet<>();
			followers.add(userId);
			userFollowers.put(userId, followers);
		} else {
			userFollowers.get(userId).add(userId);
		}
	}

	/**
	 * Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent.
	 */
	public List<Integer> getNewsFeed(int userId) {
		Set<Integer> followeeIds = userFollowers.get(userId);
		if (followeeIds == null || followeeIds.isEmpty())
			return Collections.emptyList();
		final PriorityQueue<Node> q = new PriorityQueue<>();
		followeeIds.stream().map(id -> userTweets.get(id))
				.filter(l -> l != null && l.tail != null)
				.forEach(l -> q.offer(l.tail));
		List<Integer> results = new ArrayList<>();
		while (results.size() < 10 && !q.isEmpty()) {
			Node n = q.poll();
			results.add(n.t.tweetId);
			if (n.prev != null)
				q.offer(n.prev);
		}
		return results;
	}

	/**
	 * Follower follows a followee. If the operation is invalid, it should be a no-op.
	 */
	public void follow(int followerId, int followeeId) {
		if (followerId == followeeId)
			return;
		Set<Integer> followees = userFollowers.getOrDefault(followerId, new HashSet<>());
		followees.add(followeeId);
		userFollowers.put(followerId, followees);
	}

	/**
	 * Follower unfollows a followee. If the operation is invalid, it should be a no-op.
	 */
	public void unfollow(int followerId, int followeeId) {
		if (followerId == followeeId)
			return;
		Set<Integer> followees = userFollowers.getOrDefault(followerId, new HashSet<>());
		followees.remove(followeeId);
		userFollowers.put(followerId, followees);
	}
}
