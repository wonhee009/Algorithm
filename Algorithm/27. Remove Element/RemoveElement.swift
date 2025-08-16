//
//  RemoveElement.swift
//  Algorithm
//
//  Created by Wonhee Lee on 8/16/25.
//
// https://leetcode.com/problems/remove-element/

class RemoveElement {
    func removeElement(_ nums: inout [Int], _ val: Int) -> Int {
        // O(n)
//        nums.removeAll { $0 == val }
//        return nums.count
        
        // O(n)
        var writeIndex = 0
        for readIndex in 0..<nums.count {
            if nums[readIndex] != val {
                nums[writeIndex] = nums[readIndex]
                writeIndex += 1
            }
        }
        return writeIndex
    }
}
