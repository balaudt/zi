package zi.leet.medium;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author balamurugan
 */
//https://leetcode.com/problems/online-election
public class OnlineElection {
	static class TopVotedCandidate {
		Comparator<AbstractMap.Entry<Integer, Integer>> keyComparator = Comparator.comparingInt(Map.Entry::getKey);
		List<AbstractMap.Entry<Integer, Integer>> timeline = new ArrayList<>();

		public TopVotedCandidate(int[] persons, int[] times) {
			int lastTopper = -1, maxVotesSoFar = -1;
			Map<Integer, Integer> candiateVotes = new HashMap<>();
			for (int i = 0; i < persons.length; i++) {
				int person = persons[i];
				Integer existingNoVotes = candiateVotes.get(person);
				if (existingNoVotes == null) {
					candiateVotes.put(person, 1);
					existingNoVotes = 0;
				} else {
					candiateVotes.put(person, existingNoVotes + 1);
				}
				if (existingNoVotes + 1 >= maxVotesSoFar && lastTopper != person) {
					maxVotesSoFar = existingNoVotes;
					lastTopper = person;
					timeline.add(new AbstractMap.SimpleEntry<>(times[i], lastTopper));
				}
			}
		}

		public int q(int t) {
			int ip = Collections.binarySearch(timeline, new AbstractMap.SimpleEntry<>(t, 0), keyComparator);
			if (ip >= 0) {
				return timeline.get(ip).getValue();
			} else {
				ip = -ip - 2;
				return timeline.get(ip).getValue();
			}
		}
	}

	/**
	 * Your TopVotedCandidate object will be instantiated and called as such:
	 * TopVotedCandidate obj = new TopVotedCandidate(persons, times);
	 * int param_1 = obj.q(t);
	 */
	public static void main(String[] args) {
//		TopVotedCandidate candidate = new TopVotedCandidate(new int[]{0, 1, 1, 0, 0, 1, 0}, new int[]{0, 5, 10, 15, 20, 25, 30});
//		Arrays.asList(3, 12, 25, 15, 24, 8).forEach(t -> System.out.println(candidate.q(t)));
		TopVotedCandidate candidate = new TopVotedCandidate(new int[]{0, 0, 1, 1, 2}, new int[]{0, 67, 69, 74, 87});
		Arrays.asList(4, 62, 100, 88, 70, 73, 22, 75, 29, 10).forEach(t -> System.out.println(candidate.q(t)));
		//Expected: [null,0,0,1,1,0,0,0,1,0,0]
	}
}
