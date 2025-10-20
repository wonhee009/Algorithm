//
//  RansomNote.swift
//  Algorithm
//
//  Created by Wonhee Lee on 10/20/25.
//
// https://leetcode.com/problems/ransom-note/description/

class RansomNote {
    func canConstruct(_ ransomNote: String, _ magazine: String) -> Bool {
        // time: O(n+m), space: O(n)
        var magazineCount: [Character: Int] = [:]
        for m in magazine {
            magazineCount[m, default: 0] += 1
        }
        
        for n in ransomNote {
            if let count = magazineCount[n], count > 0 {
                magazineCount[n] = count - 1
            } else {
                return false
            }
        }
        return true
        
// The main idea behind my solution is to use a dictionary to count how many times each character appears in the magazine. Then I go through each character in the ransom note and check if it’s available in that dictionary with a sufficient count. If any character is missing or we run out of it, I immediately return false. Otherwise, if we get through the whole note, we return true. It’s a straightforward O(n + m) time solution and it’s pretty efficient.
    }
}
