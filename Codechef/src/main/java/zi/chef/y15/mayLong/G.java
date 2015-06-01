package zi.chef.y15.mayLong;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

public class G {

	public static final String FOLDER_ROOT = "/home/bala/temp/2/";
	private static ArrayList<Node> cities;

	public static void main(String[] args) throws Exception {
//		Scanner scanner = new Scanner(new FileInputStream(FOLDER_ROOT + "G-sample.in"));
		 Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int m = scanner.nextInt();
		cities = new ArrayList<Node>();
		for (int i = 0; i < n; i++) {
			cities.add(new Node(i + 1));
		}
		for (int i = 0; i < m; i++) {
			int c1 = scanner.nextInt();
			int c2 = scanner.nextInt();
			Node city1 = cities.get(c1 - 1);
			Node city2 = cities.get(c2 - 1);
			city1.edgeCount++;
			city2.edgeCount++;
			city1.edges.add(city2);
			city2.edges.add(city1);
		}
		TreeMultiMap<Integer, Node> frequencyMap = getFrequencyMap();
		int totalScore = 0;
		while (true) {
			Set<Entry<Integer, List<Node>>> entrySet = frequencyMap.entrySet();
			Node nextUnHappyCity = null;
			for (Entry<Integer, List<Node>> entry : entrySet) {
				if (nextUnHappyCity != null)
					break;
				List<Node> cs = entry.getValue();
				int maxPosScore = 0;
				for (Node possibleUnHappyCity : cs) {
					int score = -1;
					Set<Node> possibleHappyCities = possibleUnHappyCity.edges;
					for (Node possibleHappyCity : possibleHappyCities) {
						if (!possibleHappyCity.tranUnHappy) {
							score++;
							Set<Node> citiesMadeUnHappy = possibleHappyCity.edges;
							for (Node newUnHappy : citiesMadeUnHappy) {
								newUnHappy.tranUnHappy = true;
							}
						}
					}
					if (score > maxPosScore) {
						maxPosScore = score;
						nextUnHappyCity = possibleUnHappyCity;
					}
					resetTrans();
				}
			}
			if (nextUnHappyCity == null) {
				break;
			}
			Set<Node> possibleHappyCities = nextUnHappyCity.edges;
			Set<Node> citiesToRemove = new HashSet<Node>();
			for (Node possibleHappyCity : possibleHappyCities) {
				if (possibleHappyCity.isHappy != null && !possibleHappyCity.isHappy) {
					continue;
				}
				possibleHappyCity.isHappy = true;
				citiesToRemove.add(possibleHappyCity);
				totalScore++;
				Set<Node> unHapCities = possibleHappyCity.edges;
				citiesToRemove.addAll(unHapCities);
				for (Node unHapCity : unHapCities) {
					if (unHapCity.isHappy != null)
						continue;
					unHapCity.isHappy = false;
					totalScore--;
					for (Node node : unHapCity.edges) {
						node.edgeCount--;
						node.edges.remove(unHapCity);
					}
				}
			}
			cities.removeAll(citiesToRemove);
			frequencyMap = getFrequencyMap();
		}
		System.out.println(totalScore);
		scanner.close();
	}

	static void resetTrans() {
		for (Node city : cities) {
			city.tranUnHappy = false;
		}
	}

	static TreeMultiMap<Integer, Node> getFrequencyMap() {
		TreeMultiMap<Integer, Node> freqMap = new TreeMultiMap<Integer, Node>();
		for (Node city : cities) {
			freqMap.put(city.edgeCount, city);
		}
		return freqMap;
	}
}

class TreeMultiMap<K, V> {
	TreeMap<K, List<V>> map = new TreeMap<K, List<V>>(Collections.reverseOrder());

	void put(K key, V val) {
		List<V> list = map.get(key);
		if (list == null) {
			list = new ArrayList<V>();
			map.put(key, list);
		}
		list.add(val);
	}

	List<V> get(K key) {
		return map.get(key);
	}

	Set<Entry<K, List<V>>> entrySet() {
		return map.entrySet();
	}

	@Override
	public String toString() {
		return map.toString();
	}
}

class Node {
	Boolean isHappy = null;
	Set<Node> edges = new HashSet<Node>();
	int edgeCount = 0;
	boolean tranUnHappy;
	int index;

	Node(int index) {
		this.index = index;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("i:").append(index).append(",h:").append(isHappy == null ? 0 : isHappy ? 1 : -1).append(",c:")
				.append(edgeCount).append(",t:").append(tranUnHappy ? 1 : 0);
		return builder.toString();
	}
}
