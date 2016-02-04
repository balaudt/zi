package zi.chef.y15.decLong;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
 
public class B {
	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(reader.readLine());
		Map<Long, Integer> lkup;
		Set<Line> visitedLines;
		String[] inStr;
		for (int i = 0; i < t; i++) {
			int n = Integer.parseInt(reader.readLine());
			lkup = new HashMap<>(n);
			visitedLines = new HashSet<>(n);
			for (int j = 0; j < n; j++) {
				inStr = reader.readLine().split(" ");
				Line line = new Line(Integer.parseInt(inStr[0]), Integer.parseInt(inStr[1]), Integer.parseInt(inStr[2]));
				if (visitedLines.contains(line))
					continue;
				visitedLines.add(line);
				Integer lastVal = lkup.get(line.m);
				if (lastVal == null)
					lkup.put(line.m, 1);
				else
					lkup.put(line.m, lastVal + 1);
			}
//			System.out.println(visitedLines);
//			System.out.println(lkup);
			ArrayList<Integer> setCts = new ArrayList<>(lkup.values());
			setCts.sort(Comparator.reverseOrder());
			System.out.println(setCts.get(0));
		}
		reader.close();
	}
}
 
class Line {
	long m, c;
 
	public Line(long a, long b, long c) {
		long gcd;
		if (b == 0) {
			m = Long.MAX_VALUE;
			//X intercept
			gcd = gcd(a, c);
			this.c = ((c / gcd) << 32) + a / gcd;
			return;
		}
		gcd = gcd(a, b);
		m = ((a / gcd) << 32) + b / gcd;
		gcd = gcd(b, c);
		this.c = ((c / gcd) << 32) + b / gcd;
	}
 
	private long gcd(long a, long b) {
		if (b == 0)
			return a;
		return gcd(b, a % b);
	}
 
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (c ^ (c >>> 32));
		result = prime * result + (int) (m ^ (m >>> 32));
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
		Line other = (Line) obj;
		if (c != other.c)
			return false;
		if (m != other.m)
			return false;
		return true;
	}
 
	@Override
	public String toString() {
		return "L [m=" + (m >> 32) + "/" + (m & 0x7fffffff) + ", c=" + (c >> 32) + "/" + (c & 0x7fffffff) + "]";
	}
}