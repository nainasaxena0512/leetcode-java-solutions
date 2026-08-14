package ArrayOrStrings;

// ms practice
// 1. The Core Concept:
// You are given an integer array `prices` where `prices[i]` is the price of a given stock on the i-th day.
// On each day, you may decide to buy and/or sell the stock. You can only hold at most one share of the stock at any time.
// However, you can buy it and immediately sell it on the same day.
// Return the maximum profit you can achieve.

// 2. The Strategy: Greedy / Peak-Valley Accumulation
// - Since we can make as many transactions as we want, we can simply capture EVERY upward price movement.
// - If the stock price tomorrow (prices[i]) is higher than today (prices[i - 1]), we buy today and sell tomorrow.
// - Mathematically: Continuous price gains (e.g. buying at A and selling at C when A < B < C) equal:
//   (C - A) == (B - A) + (C - B).
// - Therefore, simply sum up every positive difference (prices[i] - prices[i - 1]).

// Complexity:
// - Time Complexity: O(n) — single pass through the array.
// - Space Complexity: O(1) — constant space using a single accumulator variable.

public class BestTimeToBuyAndSellStockII {

    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }

        int totalProfit = 0;

        for (int i = 1; i < prices.length; i++) {
            // If the price increased compared to the previous day, take the profit
            if (prices[i] > prices[i - 1]) {
                totalProfit += prices[i] - prices[i - 1];
            }
        }

        return totalProfit;
    }

    // Optional main method for local verification
    public static void main(String[] args) {
        BestTimeToBuyAndSellStockII solver = new BestTimeToBuyAndSellStockII();

        int[] prices1 = {7, 1, 5, 3, 6, 4};
        System.out.println("Max Profit 1: " + solver.maxProfit(prices1)); 
        // Output: 7 (Buy day 2 at 1, sell day 3 at 5 -> profit 4; Buy day 4 at 3, sell day 5 at 6 -> profit 3; Total = 7)

        int[] prices2 = {1, 2, 3, 4, 5};
        System.out.println("Max Profit 2: " + solver.maxProfit(prices2)); 
        // Output: 4 (Buy day 1 at 1, sell day 5 at 5 -> Total = 4)

        int[] prices3 = {7, 6, 4, 3, 1};
        System.out.println("Max Profit 3: " + solver.maxProfit(prices3)); 
        // Output: 0 (No profit possible)
    }
}
