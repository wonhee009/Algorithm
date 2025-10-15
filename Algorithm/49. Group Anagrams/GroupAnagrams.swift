//
//  GroupAnagrams.swift
//  Algorithm
//
//  Created by Wonhee Lee on 10/15/25.
//
// https://leetcode.com/problems/group-anagrams/description/

class GroupAnagrams {
    func groupAnagrams(_ strs: [String]) -> [[String]] {
        // time: n*nlogn, space: n*n
        var group: [String: [String]] = [:]
        for s in strs {
            let orderedS = String(s.sorted())
            if let groupS = group[orderedS] {
                group[orderedS] = groupS + [s]
            } else {
                group[orderedS] = [s]
            }
        }
        let result = group.map { _, value in
            return value
        }
        return result
    }
}
