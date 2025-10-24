//
//  FindAllAnagramsInAString.swift
//  Algorithm
//
//  Created by Wonhee Lee on 10/24/25.
//
// https://leetcode.com/problems/find-all-anagrams-in-a-string/description/

class FindAllAnagramsInAString {
    func findAnagrams(_ s: String, _ p: String) -> [Int] {
//        var result: [Int] = []
//        var pMap: [Character: Int] = [:]
//        for c in p {
//            pMap[c, default: 0] += 1
//        }
//        
//        var pCount = p.count
//        let sString = Array(s)
//        for (index, c) in s.enumerated() {
//            if index - p.count >= 0 {
//                if let count = pMap[sString[index - p.count]] {
//                    if count >= 0 {
//                        pCount += 1
//                    }
//                    pMap[sString[index - p.count]] = count + 1
//                }
//            }
//            if let count = pMap[c] {
//                if count > 0 {
//                    pCount -= 1
//                }
//                pMap[c] = count - 1
//            }
//            if index >= p.count - 1, pCount == 0 {
//                result.append(index - p.count + 1)
//            }
//        }
//        
//        return result
        
        guard s.count >= p.count else { return [] }
        let sArr = Array(s)
        let pArr = Array(p)
        var result: [Int] = []
        
        var pCount: [Character: Int] = [:]
        var windowCount: [Character: Int] = [:]
        
        for char in pArr {
            pCount[char, default: 0] += 1
        }
        
        for index in 0..<sArr.count {
            let currentChar = sArr[index]
            windowCount[currentChar, default: 0] += 1
            
            if index >= pArr.count {
                let leftChar = sArr[index - pArr.count]
                if let count = windowCount[leftChar] {
                    if count == 1 {
                        windowCount.removeValue(forKey: leftChar)
                    } else {
                        windowCount[leftChar] = count - 1
                    }
                }
            }
            
            if windowCount == pCount {
                result.append(index - pArr.count + 1)
            }
        }
        
        return result
    }
}
