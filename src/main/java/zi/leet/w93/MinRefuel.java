package zi.leet.w93;

/**
 * @author balamurugan
 */
public class MinRefuel {
	private int target;
	private int[][] stations;
	private int[] runningFuels;

	public int minRefuelStops(int target, int startFuel, int[][] stations) {
		int n = stations.length;
		this.stations = new int[n + 1][];
		System.arraycopy(stations, 0, this.stations, 0, n);
		this.stations[n] = new int[]{target, 0};
		this.target = target;
		runningFuels = new int[n];
		runningFuels[n - 1] = stations[n - 1][1];
		for (int i = n - 2; i >= 0; i--) {
			runningFuels[i] = runningFuels[n - 1] + stations[i][1];
		}
		return minRefuel(0, startFuel);
	}

	private int minRefuel(int pos, int curFuel) {
		if (pos == stations.length - 1) return 0;
		//Fuel not enough to reach target
		if (curFuel + runningFuels[pos] < target - stations[pos][0]) return -1;
		//Fuel not enough to reach next station
		int distanceToNextStation = stations[pos + 1][0] - stations[pos][0];
		if (distanceToNextStation > curFuel + stations[pos][1]) return -1;
		int reFilledHere = minRefuel(pos + 1, curFuel + stations[pos][1] - distanceToNextStation);
		if (curFuel < distanceToNextStation) {
			return reFilledHere == -1 ? -1 : (1 + reFilledHere);
		}
		int reFillSkipped = minRefuel(pos + 1, curFuel - distanceToNextStation);
		if (reFilledHere == -1 && reFillSkipped == -1) return -1;
		else if (reFilledHere == -1) return reFillSkipped;
		else if (reFillSkipped == -1) return reFilledHere + 1;
		else return Integer.min(reFilledHere + 1, reFillSkipped);
	}

	public static void main(String[] args) {
		MinRefuel minRefuel = new MinRefuel();
		minRefuel.minRefuelStops(1, 1, new int[][]{});
		minRefuel.minRefuelStops(100, 1, new int[][]{{10, 100}});
		minRefuel.minRefuelStops(1, 1, new int[][]{{10, 60}, {20, 30}, {30, 30}, {60, 40}});
	}
}
