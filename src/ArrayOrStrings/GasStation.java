package ArrayOrStrings;

// ms practice
// 1. The Core Concept:
// There are n gas stations along a circular route, where the amount of gas at station i is gas[i].
// Traveling from station i to station i + 1 costs cost[i] gas.
// You begin with an empty tank at one station and want to complete a full circular tour once clockwise.
// Return the starting gas station's index if possible, otherwise return -1.
// If a solution exists, it is guaranteed to be unique.

// 2. The Strategy: Greedy / Running Balance
// - Observation 1: If total gas across all stations is less than total cost (sum(gas) < sum(cost)),
//   completing the full circuit is impossible -> return -1.
// - Observation 2: If we start at index A and get stuck (tank < 0) before reaching index B,
//   no station between A and B can be a valid starting point either.
//   (Any station between them would start with 0 gas instead of surplus gas from previous steps).
// - Therefore:
//   - Keep `totalSurplus` to check if a valid tour is globally possible.
//   - Keep `currentTank` balance. Whenever `currentTank < 0`, reset `currentTank = 0`
//     and set the next station `i + 1` as the candidate starting station.

// Complexity:
// - Time Complexity: O(n) — single pass through the array.
// - Space Complexity: O(1) — constant extra space using tracking variables.

public class GasStation {

    public int canCompleteCircuit(int[] gas, int[] cost) {
        int totalSurplus = 0;
        int currentTank = 0;
        int startingStation = 0;

        for (int i = 0; i < gas.length; i++) {
            int netGain = gas[i] - cost[i];
            totalSurplus += netGain;
            currentTank += netGain;

            // If we run out of gas, we cannot start at 'startingStation' or anywhere up to 'i'
            if (currentTank < 0) {
                startingStation = i + 1; // Reset candidate to the next station
                currentTank = 0;         // Reset current tank
            }
        }

        // If total gas is less than total cost, completing the circuit is impossible
        return totalSurplus >= 0 ? startingStation : -1;
    }

    // Optional main method for local testing
    public static void main(String[] args) {
        GasStation solver = new GasStation();

        int[] gas1 = {1, 2, 3, 4, 5};
        int[] cost1 = {3, 4, 5, 1, 2};
        System.out.println("Starting Station 1: " + solver.canCompleteCircuit(gas1, cost1));
        // Output: 3 (Start at station 3 index)

        int[] gas2 = {2, 3, 4};
        int[] cost2 = {3, 4, 3};
        System.out.println("Starting Station 2: " + solver.canCompleteCircuit(gas2, cost2));
        // Output: -1 (Total gas < total cost)
    }
}
