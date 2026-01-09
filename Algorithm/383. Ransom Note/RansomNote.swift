//
//  RansomNote.swift
//  Algorithm
//
//  Created by Wonhee Lee on 10/20/25.
//
// https://leetcode.com/problems/ransom-note/description/

class RansomNote {
    func canConstruct(_ ransomNote: String, _ magazine: String) -> Bool {
        var magazineCount: [Character: Int] = [:]
        for m in magazine {
            magazineCount[m, default: 0] += 1
        }

        for r in ransomNote {
            if let count = magazineCount[r], count > 0 {
                magazineCount[r] = count - 1
            } else {
                return false
            }
        }
        return true
    }
}
