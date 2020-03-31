package zi.leet.medium;

import java.util.*;

//https://leetcode.com/problems/queue-reconstruction-by-height
public class QueueReconstruction {
    class Person {
        Person(int h, int k) {
            this.h = h;
            this.k = k;
            originalK = k;
        }

        int h;
        int k;
        int originalK;

        @Override
        public String toString() {
            return "Person{" + "h=" + h + ", k=" + k + '}';
        }
    }

    private Comparator<Person> heightComparator = (p1, p2) -> p1.h != p2.h ? (p1.h - p2.h) : p1.k - p2.k;
    private Comparator<Person> kComparator = (p1, p2) -> p1.k != p2.k ? (p1.k - p2.k) : p1.h - p2.h;

    public int[][] reconstructQueue(int[][] people) {
        PriorityQueue<Person> persons = new PriorityQueue<>(kComparator);
        List<Person> heightSortedPeople = new ArrayList<>();
        for (int i = 0; i < people.length; i++) {
            Person person = new Person(people[i][0], people[i][1]);
            persons.add(person);
            heightSortedPeople.add(person);
        }
        heightSortedPeople.sort(heightComparator);
        List<int[]> results = new ArrayList<>();
        while (!persons.isEmpty()) {
            Person p = persons.poll();
            results.add(new int[]{p.h, p.originalK});
            int i = Collections.binarySearch(heightSortedPeople, p, heightComparator);
            heightSortedPeople.remove(i);
            if (i < heightSortedPeople.size() && heightSortedPeople.get(i).h == p.h)
                while (i < heightSortedPeople.size() && heightSortedPeople.get(i).h == p.h) i++;
            i--;
            for (int j = 0; j <= i; j++) {
                Person person = heightSortedPeople.get(j);
                persons.remove(person);
                person.k--;
                persons.add(person);
            }
        }
        results.forEach(res -> System.out.println(Arrays.toString(res)));
        return results.toArray(new int[][]{});
    }

    public static void main(String[] args) {
        QueueReconstruction queueReconstruction = new QueueReconstruction();
        queueReconstruction.reconstructQueue(new int[][]{{7, 0}, {4, 4}, {7, 1}, {5, 0}, {6, 1}, {5, 2}});
    }
}
