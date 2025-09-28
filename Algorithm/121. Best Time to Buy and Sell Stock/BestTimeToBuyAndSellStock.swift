//
//  BestTimeToBuyAndSellStock.swift
//  Algorithm
//
//  Created by Wonhee Lee on 9/28/25.
//

// https://leetcode.com/problems/best-time-to-buy-and-sell-stock/description/

class BestTimeToBuyAndSellStock {
    func maxProfit(_ prices: [Int]) -> Int {
//        var minNum = Int.max
//        var profit = 0
//        for p in prices {
//            minNum = min(p, minNum)
//            profit = max(profit, p - minNum)
//        }
//        return profit
        
        return 0
        
// We need to find the minimum price so far as we iterate through the array.
// At each step, we compare the current price with that minimum to calculate the profit.
// If the profit is larger than the previous maximum, we update it.
// We also keep updating the minimum whenever we see a lower price.
// This way, we only traverse the array once.”
    }
}
