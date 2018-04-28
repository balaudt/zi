package zi.jam.y18.qual;

import org.apache.commons.lang.text.StrBuilder;
import org.apache.commons.math3.geometry.euclidean.threed.Plane;
import org.apache.commons.math3.geometry.euclidean.threed.Rotation;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author balamurugan
 */
public class CubicUFOAnalysis {
	public static void main(String[] args) throws FileNotFoundException {
//		System.setOut(new PrintStream("/Users/balamurugan/Temp/cubes.txt"));
		Plane plane = new Plane(new Vector3D(0, -3, 0));
		double[][] params = new double[][]{
				{Math.PI, 0.01},
				{0, 0.5},
				{0, 0.5}
		};
		DecimalFormat f = new DecimalFormat("#.########");
		for (double zi = 0.0; zi <= params[0][0]; zi += params[0][1]) {
			for (double yi = 0.0; yi <= params[1][0]; yi += params[1][1]) {
				for (double xi = 0.0; xi <= params[2][0]; xi += params[2][1]) {
					List<Point> points = new ArrayList<>();
					for (double x = -0.5; x <= 0.5; x += 1) {
						for (double y = -0.5; y <= 0.5; y += 1) {
							for (double z = -0.5; z <= 0.5; z += 1) {
								Rotation zr = new Rotation(Vector3D.PLUS_K, zi);
								Rotation yr = new Rotation(Vector3D.PLUS_J, yi);
								Rotation xr = new Rotation(Vector3D.PLUS_I, xi);
								Vector3D point = (Vector3D) plane
										.project(zr.applyTo(yr.applyTo(xr.applyTo(new Vector3D(x, y, z)))));
								points.add(new Point(point.getX(), point.getZ()));
							}
						}
					}
					double area = polygonArea(ConvexHull.makeHull(points));
					System.out.println(new StrBuilder()
							.append("a=").append(f.format(area))
							.append(" zi=").append(zi).append(", yi=").append(yi).append(", xi=").append(xi)
					);
				}
//				System.out.println("**************");
			}
//			System.out.println("################");
		}
	}

	private static double polygonArea(List<Point> points) {
		double area = 0;
		int j = points.size() - 1;
		for (int i = 0; i < points.size(); i++) {
			area += (points.get(j).x + points.get(i).x) * (points.get(j).y - points.get(i).y);
			j = i;
		}
		return area / 2;
	}
}

final class ConvexHull {

	// Returns a new list of points representing the convex hull of
	// the given set of points. The convex hull excludes collinear points.
	// This algorithm runs in O(n log n) time.
	public static List<Point> makeHull(List<Point> points) {
		List<Point> newPoints = new ArrayList<>(points);
		Collections.sort(newPoints);
		return makeHullPresorted(newPoints);
	}


	// Returns the convex hull, assuming that each points[i] <= points[i + 1]. Runs in O(n) time.
	public static List<Point> makeHullPresorted(List<Point> points) {
		if (points.size() <= 1)
			return new ArrayList<>(points);

		// Andrew's monotone chain algorithm. Positive y coordinates correspond to "up"
		// as per the mathematical convention, instead of "down" as per the computer
		// graphics convention. This doesn't affect the correctness of the result.

		List<Point> upperHull = new ArrayList<>();
		for (Point p : points) {
			hull(upperHull, p);
			upperHull.add(p);
		}
		upperHull.remove(upperHull.size() - 1);

		List<Point> lowerHull = new ArrayList<>();
		for (int i = points.size() - 1; i >= 0; i--) {
			Point p = points.get(i);
			hull(lowerHull, p);
			lowerHull.add(p);
		}
		lowerHull.remove(lowerHull.size() - 1);

		if (!(upperHull.size() == 1 && upperHull.equals(lowerHull)))
			upperHull.addAll(lowerHull);
		return upperHull;
	}

	private static void hull(List<Point> lowerHull, Point p) {
		while (lowerHull.size() >= 2) {
			Point q = lowerHull.get(lowerHull.size() - 1);
			Point r = lowerHull.get(lowerHull.size() - 2);
			if ((q.x - r.x) * (p.y - r.y) >= (q.y - r.y) * (p.x - r.x))
				lowerHull.remove(lowerHull.size() - 1);
			else
				break;
		}
	}

}


final class Point implements Comparable<Point> {

	public final double x;
	public final double y;


	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}


	public String toString() {
		return String.format("Point(%g, %g)", x, y);
	}


	public boolean equals(Object obj) {
		if (!(obj instanceof Point))
			return false;
		else {
			Point other = (Point) obj;
			return x == other.x && y == other.y;
		}
	}


	public int hashCode() {
		return Objects.hash(x, y);
	}


	public int compareTo(Point other) {
		if (x != other.x)
			return Double.compare(x, other.x);
		else
			return Double.compare(y, other.y);
	}

}
