//
//  RomanToInteger.swift
//  Algorithm
//
//  Created by Wonhee Lee on 7/23/25.
//
// https://leetcode.com/problems/roman-to-integer/description/

import Foundation

class RomanToInteger {
    func romanToInt(_ s: String) -> Int {
        
        // time: n*n
        let romanToIntList: [(String, Int)] = [
            ("M", 1000), ("CM", 900), ("D", 500), ("CD", 400), ("C", 100), ("XC", 90), ("L", 50), ("XL", 40), ("X", 10), ("IX", 9), ("V", 5), ("IV", 4), ("I", 1)
        ]
        
        var result = 0
        var targetString = s
        
        for (key, value) in romanToIntList {
            while targetString.hasPrefix(key) {
                result += value
                targetString.removeFirst(key.count)
            }
        }
        
        return result
    }
}

