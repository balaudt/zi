package zi.euler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang3.text.StrBuilder;

public class PokerHands54 {

	static int p1Count = 0;
	static StrBuilder builder = new StrBuilder();
	private static Comparator<Card> cardValComparator = new Comparator<Card>() {

		public int compare(Card o1, Card o2) {
			return o1.val - o2.val;
		}
	};

	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader("io/p054_poker.txt"));
		String line;
		while ((line = reader.readLine()) != null) {
			String[] cards = line.split(" ");
			Card[] allCards = new Card[10];
			for (int i = 0; i < 10; i++) {
				allCards[i] = new Card(cards[i]);
			}
			Card[] p1 = Arrays.copyOfRange(allCards, 0, 5);
			Arrays.sort(p1);
			Card[] p2 = Arrays.copyOfRange(allCards, 5, 10);
			Arrays.sort(p2);

			if (builder.length() > 0) {
//				System.out.println(builder);
			}
			builder.clear();
//			System.out.println(Arrays.toString(p1) + "\t" + Arrays.toString(p2));

			if (isRoyalFlush(p1)) {
				builder.append(" P1 ");
				p1Count++;
				continue;
			}
			if (isRoyalFlush(p2)) {
				builder.append(" P2 ");
				continue;
			}
			if (isAnyWinner(isStraightFlush(p1), isStraightFlush(p2)))
				continue;
			if (isAnyWinner(isOfAKind(4, p1), isOfAKind(4, p2)))
				continue;
			if (isAnyWinner(isFullHouse(p1), isFullHouse(p2)))
				continue;
			if (isFlush(p1)) {
				builder.append(" P1 ");
				p1Count++;
				continue;
			}
			if (isFlush(p2)) {
				builder.append(" P2 ");
				continue;
			}
			if (isAnyWinner(isStraight(p1), isStraight(p2)))
				continue;
			if (isAnyWinner(isOfAKind(3, p1), isOfAKind(3, p2)))
				continue;
			if (isAnyWinner(twoPairs(p1), twoPairs(p2)))
				continue;
			if (isAnyWinner(isOfAKind(2, p1), isOfAKind(2, p2)))
				continue;
			isAnyWinner(highCard(p1), highCard(p2));
		}
		System.out.println(p1Count);
		reader.close();
	}

	static int highCard(Card[] cards) {
		Card[] newCards = getValueSortedCards(cards);
		return newCards[newCards.length - 1].val;
	}

	static int twoPairs(Card[] cards) {
		Map<Integer, Integer> indCount = new HashMap<Integer, Integer>();
		for (int i = 0; i < cards.length; i++) {
			int val = cards[i].val;
			if (indCount.containsKey(val)) {
				indCount.put(val, indCount.get(val) + 1);
			} else {
				indCount.put(val, 1);
			}
		}
		int[] twoPairInd = new int[] { -1, -1 };
		Set<Entry<Integer, Integer>> entrySet = indCount.entrySet();
		for (Entry<Integer, Integer> entry : entrySet) {
			if (entry.getValue() == 2) {
				if (twoPairInd[0] == -1) {
					twoPairInd[0] = entry.getKey();
					continue;
				}
				Integer key = entry.getKey();
				if (twoPairInd[0] > key) {
					twoPairInd[1] = twoPairInd[0];
					twoPairInd[0] = key;
				} else {
					twoPairInd[1] = key;
				}
			}
		}
		if (twoPairInd[0] == -1 || twoPairInd[1] == -1) {
			return -1;
		} else {
//			System.out.println(" Two pairs ");
			return twoPairInd[1] * 10 + twoPairInd[0];
		}
	}

	static int isStraight(Card[] cards) {
		Card[] newCards = getValueSortedCards(cards);
		int value = newCards[0].val;
		for (int i = 0; i < newCards.length; i++) {
			if (newCards[i].val != value + i)
				return -1;
		}
		builder.append(" Straight ");
		return value;
	}

	static Card[] getValueSortedCards(Card[] cards) {
		Card[] newCards = Arrays.copyOf(cards, cards.length);
		Arrays.sort(newCards, cardValComparator);
		return newCards;
	}

	static boolean isFlush(Card[] cards) {
		char shape = cards[0].shape;
		for (int i = 0; i < cards.length; i++) {
			if (cards[i].shape != shape)
				return false;
		}
		builder.append(" Flush ");
		return true;
	}

	static int isFullHouse(Card[] cards) {
		int threeInd = isOfAKind(3, cards);
		if (threeInd == -1) {
			return -1;
		}
		int twoInd = isOfAKind(2, cards);
		if (twoInd == -1) {
			return -1;
		}
//		System.out.println(" Full house ");
		return threeInd * 10 + twoInd;
	}

	static int isOfAKind(int index, Card[] cards) {
		Map<Integer, Integer> indCount = new HashMap<Integer, Integer>();
		for (int i = 0; i < cards.length; i++) {
			int val = cards[i].val;
			if (indCount.containsKey(val)) {
				indCount.put(val, indCount.get(val) + 1);
			} else {
				indCount.put(val, 1);
			}
		}
		Collection<Integer> values = indCount.values();
		if (!values.contains(index)) {
			return -1;
		}
		int maxOfAKind = 0;
		Set<Entry<Integer, Integer>> entrySet = indCount.entrySet();
		for (Entry<Integer, Integer> entry : entrySet) {
			if (entry.getValue() == index && entry.getKey() > maxOfAKind) {
				maxOfAKind = entry.getKey();
			}
		}
		builder.append(index + "  of a kind ");
		return maxOfAKind;
	}

	static boolean isAnyWinner(int p1, int p2) {
		if (p1 - p2 == 0) {
			return false;
		}
		if (p1 - p2 > 0) {
			builder.append(" P1 ");
			p1Count++;
		} else
			builder.append(" P2 ");
		return true;
	}

	static int isStraightFlush(Card[] cards) {
		char shape = cards[0].shape;
		for (int i = 0; i < cards.length; i++) {
			if (cards[i].val != cards[0].val + i || cards[i].shape != shape)
				return -1;
		}
		builder.append("Straight flush " + cards[0].val + " ");
		return cards[0].val;
	}

	static boolean isRoyalFlush(Card[] cards) {
		char shape = cards[0].shape;
		for (int i = 0; i < cards.length; i++) {
			if (cards[i].val != (10 + i) || cards[i].shape != shape) {
				return false;
			}
		}
		builder.append("Royal flush ");
		return true;
	}
}

class Card implements Comparable<Card> {
	int val;
	char shape;

	public Card(String card) {
		this(card.charAt(0), card.charAt(1));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + shape;
		result = prime * result + val;
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
		Card other = (Card) obj;
		if (shape != other.shape)
			return false;
		if (val != other.val)
			return false;
		return true;
	}

	public Card(char in, char s) {
		shape = s;
		switch (in) {
		case 'J':
			val = 11;
			break;
		case 'Q':
			val = 12;
			break;
		case 'K':
			val = 13;
			break;
		case 'A':
			val = 14;
			break;
		case 'T':
			val = 10;
			break;
		default:
			val = (in - '0');
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		switch (val) {
		case 11:
			builder.append('J');
			break;
		case 12:
			builder.append('Q');
			break;
		case 13:
			builder.append('K');
			break;
		case 14:
			builder.append('A');
			break;
		default:
			builder.append(val);
		}
		builder.append(shape);
		return builder.toString();
	}

	public int compareTo(Card rhs) {
		if (shape == rhs.shape)
			return val - rhs.val;
		else
			return shape - rhs.shape;
	}
}