//
//  IsomorphicStrings.swift
//  Algorithm
//
//  Created by Wonhee Lee on 10/20/25.
//
// https://leetcode.com/problems/isomorphic-strings/description/

class IsomorphicStrings {
    func isIsomorphic(_ s: String, _ t: String) -> Bool {
        if s.count != t.count { return false }
        
        var characterMap: [Character: Character] = [:]
        let sArray = Array(s)
        var tArray = Array(t)
        
        for index in 0..<s.count {
            if let c = characterMap[sArray[index]] {
                if c != tArray[index] {
                    return false
                } else {
                    tArray[index] = sArray[index]
                }
            } else {
                characterMap[sArray[index]] = tArray[index]
                tArray[index] = sArray[index]
            }
        }
        print("\(sArray) and \(tArray)")
        return String(sArray) == String(tArray)
    }
}
