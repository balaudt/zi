package zi.leet.mock.amazon;

import java.util.PriorityQueue;

/**
 * @author balamurugan
 */
//https://leetcode.com/problems/k-closest-points-to-origin/
public class ClosestPoints {
	class Point implements Comparable<Point> {
		int x, y, distance;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
			distance = x * x + y * y;
		}

		@Override
		public int compareTo(Point o) {
			return o.distance - distance;
		}
	}

	public int[][] kClosest(int[][] points, int K) {
		PriorityQueue<Point> q = new PriorityQueue<>();
		for (int i = 0; i < K; i++) {
			q.add(new Point(points[i][0], points[i][1]));
		}
		for (int i = K; i < points.length; i++) {
			Point p = new Point(points[i][0], points[i][1]);
			if (p.distance < q.peek().distance) {
				q.poll();
				q.offer(p);
			}
		}
		int[][] result = new int[K][2];
		int i = 0;
		for (Point point : q) {
			result[i++] = new int[]{point.x, point.y};
		}
		return result;
	}
}
