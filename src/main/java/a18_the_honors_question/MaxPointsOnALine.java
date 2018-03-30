package a18_the_honors_question;

import java.util.HashMap;
import java.util.Map;

import util.Point;

/**
 * Given n points on a 2D plane, find the maximum number of points that lie on the same straight
 * line.
 * 
 * @author lchen
 *
 */
public class MaxPointsOnALine {
	public int maxPointsOnALine(Point[] points) {
		if (points.length == 0)
			return 0;
		if (points.length <= 2)
			return points.length;
		int result = 0;
		for (int i = 0; i < points.length - 1; i++) {
			Map<String, Integer> map = new HashMap<>();
			int overlap = 0;
			int max = 0;
			for (int j = i + 1; j < points.length; j++) {
				int x = points[i].x - points[j].x;
				int y = points[i].y - points[j].y;
				if (x == 0 && y == 0) {
					overlap++;
					continue;
				}
				int gcd = generateGcd(x, y);
				x /= gcd;
				y /= gcd;
				// use string as the slope key
				String slope = String.valueOf(x) + ',' + String.valueOf(y);
				int count = map.getOrDefault(slope, 0);
				count++;
				map.put(slope, count);
				max = Math.max(max, count);
			}
			result = Math.max(result, max + overlap + 1);
		}
		return result;
	}

	private int generateGcd(int x, int y) {
		if (y == 0) {
			return x;
		}
		return generateGcd(y, x % y);
	}

	public static void main(String[] args) {
		MaxPointsOnALine solution = new MaxPointsOnALine();
		Point[] points = new Point[] { new Point(1, 1), new Point(1, 21), new Point(1, 23) };
		assert solution.maxPointsOnALine(points) == 3;
		points = new Point[] { new Point(2, 3), new Point(3, 3), new Point(-5, 3) };
		assert solution.maxPointsOnALine(points) == 3;
	}
}
