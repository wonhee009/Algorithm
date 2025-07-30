//
//  LongestCommonPrefix.swift
//  Algorithm
//
//  Created by Wonhee Lee on 7/30/25.
//
// https://leetcode.com/problems/longest-common-prefix/description/

import Foundation

class LongestCommonPrefix {
    func longestCommonPrefix(_ strs: [String]) -> String {
        // time: n*n
        guard strs.count > 1 else {
            return strs[0]
        }
        var sortedStrings = strs.sorted { $0.count < $1.count }
        var result: String = ""
        var first = sortedStrings.removeFirst()
        while first.isEmpty == false {
            var hasPrefix: Bool = true
            for string in sortedStrings {
                if string.hasPrefix(first) == false {
                    hasPrefix = false
                }
            }
            if hasPrefix {
                return first
            } else {
                first.removeLast()
            }
        }
        return result
    }
}

