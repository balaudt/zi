package zi.chef.y15.decLong;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
 
public class CFir {
 
	public static void main(String[] args) throws Exception {
//				BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader reader = new BufferedReader(new FileReader("/Users/balaudt/Temp/dec/C-gen.in"));
		//		BufferedWriter writer = new BufferedWriter(new FileWriter("/Users/balaudt/Temp/dec/C-gen.out"));
		int t = Integer.parseInt(reader.readLine());
		for (int i = 0; i < t; i++) {
			int n = Integer.parseInt(reader.readLine());
			char[][] in = new char[n][];
			for (int j = 0; j < n; j++) {
				in[j] = reader.readLine().toCharArray();
			}
			int m = in[0].length;
 
			List<int[]> positions = new ArrayList<>();
			positions.add(new int[n]);
			int ans = 0;
			loop: while (true) {
//				int maxSum = Integer.MIN_VALUE;
				List<int[]> newPositions = new ArrayList<int[]>(positions.size()*2);
				for (int[] indices : positions) {
					for (int j = 0; j < 2; j++) {
						//						int curSum = 0;
						int[] newIndices = new int[n];
						for (int k = 0; k < n; k++) {
							int ind = indices[k];
							while (ind < m && in[k][ind] != 'a' + j)
								ind++;
							if (ind == m) {
								break loop;
							}
							newIndices[k] = ind + 1;
							//							curSum += ind;
						}
						newPositions.add(newIndices);
						/*if (curSum > maxSum) {
							newPositions = new ArrayList<>();
							newPositions.add(newIndices);
							maxSum = curSum;
						} else if (curSum == maxSum) {
							newPositions.add(newIndices);
						}*/
					}
				}
				//				System.out.println(maxSum);
				ans++;
				positions = newPositions;
			}
			System.out.println(ans);
		}
		reader.close();
		//		writer.close();
	}
} 