//
//  TwoSum.swift
//  Algorithm
//
//  Created by Wonhee Lee on 7/20/25.
//

// https://leetcode.com/problems/two-sum/description/

class TwoSum {
    func twoSum(_ nums: [Int], _ target: Int) -> [Int] {
        // time: n*n, space: 1
        /*
        var startIndex = 0
        var endIndex = nums.count - 1
        
        while startIndex < (nums.count - 1) {
            while endIndex > startIndex {
                if nums[startIndex] + nums[endIndex] == target {
                    return [startIndex, endIndex]
                }
                endIndex = endIndex - 1
            }
            startIndex = startIndex + 1
            endIndex = nums.count - 1
        }
        return []
         */
        
        // time: 1, space: 1
        /*
        var numWithIndex: [Int: Int] = [:]
        for (index, num) in nums.enumerated() {
            if let otherIndex = numWithIndex[num] {
                return [index, otherIndex]
            } else {
                numWithIndex[target - num] = index
            }
        }
        return []
         */
        
// We use a dictionary so we only loop through the array once.
// For each number, we compute 'target number - number'
// If that complement is already in a dictionary, we return its index and the current index.
// Otherwise, we store the current number with index.
// This runs in O(n) time and uses O(n) extra space.
        
        return []
    }
}
