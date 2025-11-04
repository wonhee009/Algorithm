//
//  MaximumSubarray.swift
//  Algorithm
//
//  Created by Wonhee Lee on 11/4/25.
//
// https://leetcode.com/problems/maximum-subarray/description/

class MaximumSubarray {
    func maxSubArray(_ nums: [Int]) -> Int {
        // Time: O(n)
        var currentSum = nums[0], maxSum = nums[0]
        for i in 1..<nums.count {
            currentSum = max(nums[i], currentSum + nums[i])
            maxSum = max(maxSum, currentSum)
        }
        return maxSum
    }
}
