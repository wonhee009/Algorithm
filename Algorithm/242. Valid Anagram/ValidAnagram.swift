//
//  ValidAnagram.swift
//  Algorithm
//
//  Created by Wonhee Lee on 10/14/25.
//
// https://leetcode.com/problems/valid-anagram/description/

class ValidAnagram {
    func isAnagram(_ s: String, _ t: String) -> Bool {
        
        // time: n, space: n
        guard s.count == t.count else { return false }
        var characters: [Character: Int] = [:]
//        for c in s {
//            if let count = characters[c] {
//                characters[c] = count + 1
//            } else {
//                characters[c] = 1
//            }
//        }
        for c in s {
            characters[c, default: 0] += 1
        }
//        for c in t {
//            if let count = characters[c] {
//                if count == 1 {
//                    characters.removeValue(forKey: c)
//                } else {
//                    characters[c] = count - 1
//                }
//            } else {
//                return false
//            }
//        }
        for c in t {
            if let count = characters[c], count > 0 {
                characters[c] = count - 1
            } else {
                return false
            }
        }
//        return characters.count == 0 ? true : false
        return true
    }
    
//    An anagram just means two strings have exactly the same characters in the same quantities, just maybe in a different order.
//    So the first step we do is a quick length check: if the two strings aren’t the same length, they can’t be anagrams, right? After that, we use a dictionary (kind of like a hash map) where the key is each character and the value is how many times that character appears.
//    We count up all the characters in the first string, and then we go through the second string and subtract those counts out. If we ever find a character that isn’t in the dictionary or we end up with a leftover count, we know it’s not an anagram. Finally, if the dictionary is empty at the end, that means everything matched up perfectly, so we return true.
}
