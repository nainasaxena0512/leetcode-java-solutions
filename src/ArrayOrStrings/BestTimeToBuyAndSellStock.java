package ArrayOrStrings;

// ms practice
// 1. The Core Concept:
// You are given an array `prices` where `prices[i]` is the price of a given stock on the i-th day.
// You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.
// Return the maximum profit you can achieve from this transaction. If no profit can be achieved, return 0.

// 2. The Strategy: Single-Pass Greedy / Minimum Price Tracking
// - We can only sell on a future day, meaning for each day `i`, the best day to have bought was the day with the lowest price seen so far (from day 0 to day i-1).
// - Maintain two variables:
//   - `minPrice`: The minimum stock price encountered so far (initialized to Integer.MAX_VALUE).
//   - `maxProfit`: The maximum profit found so far (initialized to 0).
// - Iterate through the array once:
//   - If the current price is less than `minPrice`, update `minPrice`.
//   - Otherwise, calculate potential profit: `price - minPrice` and update `maxProfit` if this profit is higher.

// Complexity:
// - Time Complexity: O(n) — single pass through the array.
// - Space Complexity: O(1) — constant extra space using only two integer variables.

public class BestTimeToBuyAndSellStock {

    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }

        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;

        for (int price : prices) {
            if (price < minPrice) {
                minPrice = price; // Update the lowest buying price seen so far
            } else {
                int potentialProfit = price - minPrice;
                if (potentialProfit > maxProfit) {
                    maxProfit = potentialProfit; // Update max profit achievable
                }
            }
        }

        return maxProfit;
    }

    // Optional main method for local testing
    public static void main(String[] args) {
        BestTimeToBuyAndSellStock solver = new BestTimeToBuyAndSellStock();

        int[] prices1 = {7, 1, 5, 3, 6, 4};
        System.out.println("Max Profit 1: " + solver.maxProfit(prices1)); 
        // Output: 5 (Buy on day 2 at price 1, sell on day 5 at price 6)

        int[] prices2 = {7, 6, 4, 3, 1};
        System.out.println("Max Profit 2: " + solver.maxProfit(prices2)); 
        // Output: 0 (Prices are constantly declining, no profitable transaction)
    }
}
