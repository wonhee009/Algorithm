//
//  ValidAnagram.swift
//  Algorithm
//
//  Created by Wonhee Lee on 10/14/25.
//
// https://leetcode.com/problems/valid-anagram/description/

class ValidAnagram {
    func isAnagram(_ s: String, _ t: String) -> Bool {
        guard s.count == t.count else { return false }
        var characters: [Character: Int] = [:]
        for c in t {
            characters[c, default: 0] += 1
        }

        for c in s {
            if let count = characters[c], count > 0 {
                characters[c] = count - 1
            } else {
                return false
            }
        }
        return true
    }
}
