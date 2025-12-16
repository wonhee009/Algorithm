//
//  TwoSum.swift
//  Algorithm
//
//  Created by Wonhee Lee on 7/20/25.
//

// https://leetcode.com/problems/two-sum/description/

class TwoSum {
    func twoSum(_ nums: [Int], _ target: Int) -> [Int] {
        var numWithIndex: [Int: Int] = [:]
        for (index, num) in nums.enumerated() {
            if let otherIndex = numWithIndex[num] {
                return [otherIndex, index]
            } else {
                numWithIndex[target - num] = index
            }
        }
        return []
    }
}
