//
//  PermutationInString.swift
//  Algorithm
//
//  Created by Wonhee Lee on 11/8/25.
//
// https://leetcode.com/problems/permutation-in-string/description/

class PermutationInString {
    func checkInclusion(_ s1: String, _ s2: String) -> Bool {
        let s1Len = s1.count, s2Len = s2.count
        if s1Len > s2Len { return false }
        
        var s1Count: [Character: Int] = [:]
        for s in s1 {
            s1Count[s, default: 0] += 1
        }
        
        var s2Count: [Character: Int] = [:]
        let s2Array = Array(s2)
        for i in 0..<s1Len {
            s2Count[s2Array[i], default: 0] += 1
        }
        if s1Count == s2Count { return true }
        
        for i in s1Len..<s2Len {
            let add = s2Array[i]
            let remove = s2Array[i-s1Len]
            
            s2Count[add, default: 0] += 1
            
            if let count = s2Count[remove] {
                if count == 1 {
                    s2Count.removeValue(forKey: remove)
                } else {
                    s2Count[remove] = count - 1
                }
            }
            
            if s1Count == s2Count {
                return true
            }
        }
        return false
    }
}
