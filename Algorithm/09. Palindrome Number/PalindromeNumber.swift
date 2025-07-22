//
//  PalindromeNumber.swift
//  Algorithm
//
//  Created by Wonhee Lee on 7/22/25.
//

// https://leetcode.com/problems/palindrome-number/description/

class PalindromeNumber {
    func isPalindrome(_ x: Int) -> Bool {
        // time: 1, space: 1
//        guard x >= 0 else { return false }
//        guard x >= 10 else { return true }
//        let palindromeString = String(x)
//        var startOffset = 0
//        var endOffset = -1
//        var midOffset = palindromeString.count / 2
//        while startOffset < midOffset {
//            let startIndex = palindromeString.index(palindromeString.startIndex, offsetBy: startOffset)
//            let endIndex = palindromeString.index(palindromeString.endIndex, offsetBy: endOffset)
//            if endIndex == startIndex  {
//                return true
//            }
//            if palindromeString[startIndex] != palindromeString[endIndex] {
//                return false
//            }
//            startOffset += 1
//            endOffset -= 1
//        }
//        return true
        
        guard x >= 0 else { return false }
        let palindromeString = String(x)
        let prefix = palindromeString.prefix(palindromeString.count / 2)
        return palindromeString.hasSuffix(String(prefix.reversed()))
    }
}
