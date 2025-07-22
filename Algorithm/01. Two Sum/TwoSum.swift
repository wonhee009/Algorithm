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
        
        // tiem: 1, space: 1
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
        return []
    }
}
