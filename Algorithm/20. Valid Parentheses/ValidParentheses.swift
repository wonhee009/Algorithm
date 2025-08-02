//
//  ValidParentheses.swift
//  Algorithm
//
//  Created by Wonhee Lee on 8/2/25.
//
// https://leetcode.com/problems/valid-parentheses/description/

class ValidParentheses {
    func isValid(_ s: String) -> Bool {
        // time: n*n
//        let openBraket = ["(", "[", "{"]
//        let pairBraket = [
//            ")" : "(",
//            "]" : "[",
//            "}" : "{"
//        ]
//        
//        guard s.count > 1 else { return false }
//        var string = s
//        var result = ""
//        
//        while string.isEmpty == false {
//            let first = string.removeFirst()
//            if openBraket.contains(String(first)) {
//                result += String(first)
//            } else {
//                if let last = result.last {
//                    if pairBraket[String(first)] == String(last) {
//                        result.removeLast()
//                    } else {
//                        return false
//                    }
//                } else {
//                    return false
//                }
//            }
//        }
//        return result.isEmpty
        
        // time: n (set.contains -> n(1), array.contains -> n(n))
        let openBraket: Set<Character> = ["(", "[", "{"]
        let pairBraket: [Character: Character] = [
            ")" : "(",
            "]" : "[",
            "}" : "{"
        ]
        
        var stack: [Character] = []
        for c in s {
            if openBraket.contains(c) {
                stack.append(c)
            } else {
                guard let last = stack.last, last == pairBraket[c] else {
                    return false
                }
                stack.removeLast()
            }
        }
        return stack.isEmpty
    }
}
