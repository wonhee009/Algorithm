//
//  LongestSubstringWithoutRepeatingCharacters.swift
//  Algorithm
//
//  Created by Wonhee Lee on 11/6/25.
//
// https://leetcode.com/problems/longest-substring-without-repeating-characters/description/

class LongestSubstringWithoutRepeatingCharacters {
    func lengthOfLongestSubstring(_ s: String) -> Int {
        // Time: O(n), Space: O(n)
//        var maximum = 0
//        var indexSet: [Character : Int] = [:]
//        var count: [Int] = []
//        var changedIndex: Int = -1
//        for (i, c) in s.enumerated() {
//            if let index = indexSet[c] {
//                changedIndex = max(changedIndex, index)
//                count.append(i - changedIndex)
//                indexSet[c] = i
//            } else {
//                indexSet[c] = i
//                if i == 0 {
//                    count.append(1)
//                } else {
//                    count.append(count[i - 1] + 1)
//                }
//            }
//            maximum = max(maximum, count[i])
//        }
//        return maximum
        
        var indexSet: [Character: Int] = [:]
        var maxLen = 0
        var left = -1

        for (i, c) in s.enumerated() {
            if let prev = indexSet[c] {
                left = max(left, prev)
            }
            indexSet[c] = i
            maxLen = max(maxLen, i - left)
        }
        return maxLen
    }
}
