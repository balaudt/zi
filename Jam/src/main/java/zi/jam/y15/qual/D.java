package zi.jam.y15.qual;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import org.apache.commons.lang3.text.StrBuilder;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Multimap;

public class D {

	public static final String FOLDER_ROOT = "C:/ft/1/";
	static int x, r, c;
	static Multimap<Integer, ImmutableSet<int[][]>> freeMaps;
	static Map<Integer, ArrayList<int[][]>> fixedMap;
	static byte[][] dotMatrix;
	static Stack<Integer> polyominoInserted;
	static Stack<int[]> indexInserted;

	public static void main(String[] args) throws Exception {
		ImmutableSet<int[][]> monomino = ImmutableSet.<int[][]> of(new int[][] { { 0, 0 } });
		ImmutableSet<int[][]> domino = ImmutableSet.<int[][]> of(new int[][] { { 0, 0 }, { 0, 1 } }, new int[][] { { 0, 0 },
				{ 1, 0 } });

		ImmutableSet<int[][]> straightTriomino = ImmutableSet.<int[][]> of(new int[][] { { 0, 0 }, { 0, 1 }, { 0, 2 } },
				new int[][] { { 0, 0 }, { 1, 0 }, { 2, 0 } });
		ImmutableSet<int[][]> lTriomino = ImmutableSet.<int[][]> of(new int[][] { { 0, 0 }, { 0, 1 }, { 1, 0 } }, new int[][] {
				{ 0, 0 }, { 1, 0 }, { 1, 1 } }, new int[][] { { 1, 0 }, { 0, 1 }, { 1, 1 } }, new int[][] { { 0, 0 }, { 0, 1 },
				{ 1, 1, } });

		ImmutableSet<int[][]> t = ImmutableSet.<int[][]> of(new int[][] { { 0, 0 }, { 0, 1 }, { 0, 2 }, { 1, 1 } }, new int[][] {
				{ 0, 0 }, { 1, 0 }, { 2, 0 }, { 1, 1 } }, new int[][] { { 1, 0 }, { 0, 1 }, { 1, 1 }, { 2, 1 } }, new int[][] {
				{ 0, 1 }, { 1, 0 }, { 1, 1 }, { 1, 2 } });
		ImmutableSet<int[][]> straight = ImmutableSet.<int[][]> of(new int[][] { { 0, 0 }, { 0, 1 }, { 0, 2 }, { 0, 3 } },
				new int[][] { { 0, 0 }, { 1, 0 }, { 2, 0 }, { 3, 0 } });
		ImmutableSet<int[][]> l = ImmutableSet.<int[][]> of(new int[][] { { 0, 0 }, { 1, 0 }, { 2, 0 }, { 0, 1 } }, new int[][] {
				{ 0, 0 }, { 1, 0 }, { 2, 0 }, { 2, 1 } }, new int[][] { { 0, 0 }, { 0, 1 }, { 0, 2 }, { 1, 0 } }, new int[][] {
				{ 0, 0 }, { 1, 0 }, { 1, 1 }, { 1, 2 } }, new int[][] { { 0, 1 }, { 1, 1 }, { 2, 1 }, { 2, 0 } }, new int[][] {
				{ 0, 0 }, { 0, 1 }, { 1, 1 }, { 2, 1 } }, new int[][] { { 0, 2 }, { 1, 0 }, { 1, 1 }, { 1, 2 } }, new int[][] {
				{ 0, 0 }, { 0, 1 }, { 0, 2 }, { 1, 2 } });
		ImmutableSet<int[][]> skew = ImmutableSet.<int[][]> of(new int[][] { { 0, 1 }, { 1, 0 }, { 1, 1 }, { 2, 0 } }, new int[][] {
				{ 0, 0 }, { 1, 0 }, { 1, 1 }, { 2, 1 } }, new int[][] { { 1, 0 }, { 1, 1 }, { 0, 1 }, { 0, 2 } }, new int[][] {
				{ 0, 0 }, { 0, 1 }, { 1, 1 }, { 1, 2 } });
		ImmutableSet<int[][]> square = ImmutableSet.<int[][]> of(new int[][] { { 0, 0 }, { 0, 1 }, { 1, 0 }, { 1, 1 } });

		ArrayList<int[][]> allM = new ArrayList<int[][]>(monomino);
		ArrayList<int[][]> allD = new ArrayList<int[][]>(domino);
		ArrayList<int[][]> allTri = new ArrayList<int[][]>(straightTriomino);
		allTri.addAll(lTriomino);
		ArrayList<int[][]> allTetra = new ArrayList<int[][]>(t);
		allTetra.addAll(straight);
		allTetra.addAll(l);
		allTetra.addAll(skew);
		allTetra.addAll(square);

		freeMaps = ArrayListMultimap.<Integer, ImmutableSet<int[][]>> create();
		freeMaps.put(0, monomino);
		freeMaps.put(1, domino);
		freeMaps.put(2, straightTriomino);
		freeMaps.put(2, lTriomino);
		freeMaps.put(3, t);
		freeMaps.put(3, l);
		freeMaps.put(3, straight);
		freeMaps.put(3, square);
		freeMaps.put(3, skew);

		fixedMap = new HashMap<Integer, ArrayList<int[][]>>();
		fixedMap.put(0, allM);
		fixedMap.put(1, allD);
		fixedMap.put(2, allTri);
		fixedMap.put(3, allTetra);

		BufferedReader reader = new BufferedReader(new FileReader(FOLDER_ROOT + "D-small-attempt0.in"));
		BufferedWriter writer = new BufferedWriter(new FileWriter(FOLDER_ROOT + "D-small-attempt0.out"));
		int noCase = Integer.parseInt(reader.readLine());
		for (int i = 0; i < noCase; i++) {
			StrBuilder builder = new StrBuilder("Case #").append(i + 1).append(": ");
			String[] inStr = reader.readLine().split(" ");
			x = Integer.parseInt(inStr[0]);
			r = Integer.parseInt(inStr[1]);
			c = Integer.parseInt(inStr[2]);
			if ((r * c) % x != 0 || x > r * c) {
				builder.append("RICHARD\n");
				String builderStr = builder.toString();
				System.out.print(builderStr);
				writer.write(builderStr);
				continue;
			}
			int size = freeMaps.get(x - 1).size();
			boolean canRichardWin = false;
			for (int j = 0; j < size; j++) {
				if (!canGabrielWin(j)) {
					canRichardWin = true;
					break;
				}
			}
			if (!canRichardWin)
				builder.append("GABRIEL\n");
			else
				builder.append("RICHARD\n");
			String builderStr = builder.toString();
			System.out.print(builderStr);
			writer.write(builderStr);
		}
		reader.close();
		writer.close();
	}

	static boolean canGabrielWin(int i) {
		Collection<ImmutableSet<int[][]>> possibleChoices = freeMaps.get(x - 1);
		int count = 0;
		ImmutableSet<int[][]> set = null;
		for (ImmutableSet<int[][]> immutableSet : possibleChoices) {
			if (count == i) {
				set = immutableSet;
				break;
			}
			count++;
		}
		for (int[][] startSymbol : set) {
			dotMatrix = new byte[r][c];
			boolean isPossible = true;
			for (int j = 0; j < startSymbol.length; j++) {
				try {
					dotMatrix[startSymbol[j][0]][startSymbol[j][1]] = 1;
				} catch (ArrayIndexOutOfBoundsException e) {
					isPossible = false;
					break;
				}
			}
			if (!isPossible)
				continue;
			if (x == r * c)
				return true;
			polyominoInserted = new Stack<Integer>();
			indexInserted = new Stack<int[]>();
			if (tryFill(0))
				return true;
		}
		return false;
	}

	static boolean tryFill(int ominoStart) {
		// System.out.println(Arrays.deepToString(dotMatrix));
		ArrayList<int[][]> allOminos = fixedMap.get(x - 1);
		int size = allOminos.size();
		boolean wasInserted = false;
		for (int j = ominoStart; j < size && !wasInserted; j++) {
			int[][] omino = allOminos.get(j);
			int[] slot = nextAvailableSlot(0, 0, false);
			while (slot != null) {
				boolean canInsert = true;
				for (int i = 0; i < omino.length; i++) {
					try {
						if (dotMatrix[slot[0] + omino[i][0]][slot[1] + omino[i][1]] != 0) {
							canInsert = false;
							break;
						}
					} catch (ArrayIndexOutOfBoundsException e) {
						canInsert = false;
						break;
					}
				}
				if (canInsert) {
					indexInserted.push(slot);
					polyominoInserted.push(j);
					wasInserted = true;
					for (int i = 0; i < omino.length; i++) {
						dotMatrix[slot[0] + omino[i][0]][slot[1] + omino[i][1]] = 1;
					}
					break;
				}
				slot = nextAvailableSlot(slot[0], slot[1], true);
			}
		}
		if (!wasInserted) {
			if (polyominoInserted.isEmpty()) {
				return false;
			} else {
				Integer lastInsertedPolyomino = polyominoInserted.pop();
				int[] lastInsertedLocation = indexInserted.pop();
				clear(lastInsertedLocation, lastInsertedPolyomino);
				return tryFill(lastInsertedPolyomino + 1);
			}
		} else {
			if ((polyominoInserted.size() + 1) * x == r * c) {
				return true;
			}
			return tryFill(0);
		}
	}

	static void clear(int[] lastLoc, int polyomino) {
		int[][] locs = ((ArrayList<int[][]>) fixedMap.get(x - 1)).get(polyomino);
		for (int i = 0; i < locs.length; i++) {
			dotMatrix[lastLoc[0] + locs[i][0]][lastLoc[1] + locs[i][1]] = 0;
		}
	}

	static int[] nextAvailableSlot(int sx, int sy, boolean shouldIncrement) {
		if (shouldIncrement) {
			if (sy == c - 1) {
				if (sx == r - 1)
					return null;
				else {
					sx++;
					sy = 0;
				}
			} else {
				sy++;
			}
		}
		for (int i = sx; i < r; i++) {
			for (int j = i == sx ? sy : 0; j < c; j++) {
				if (dotMatrix[i][j] != 1)
					return new int[] { i, j };
			}
		}
		return null;
	}
}
