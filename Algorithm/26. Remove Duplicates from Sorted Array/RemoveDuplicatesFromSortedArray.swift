//
//  RemoveDuplicatesFromSortedArray.swift
//  Algorithm
//
//  Created by Wonhee Lee on 8/11/25.
//
// https://leetcode.com/problems/remove-duplicates-from-sorted-array/description/

class RemoveDuplicatesFromSortedArray {
    func removeDuplicates(_ nums: inout [Int]) -> Int {
        var index = 1
        for i in 1..<nums.count {
            if nums[i] != nums[i - 1] {
                nums[index] = nums[i]
                index += 1
            }
        }
        return index
    }
}
