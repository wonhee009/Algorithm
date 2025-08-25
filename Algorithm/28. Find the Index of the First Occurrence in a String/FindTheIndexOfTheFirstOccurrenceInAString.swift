//
//  FindTheIndexOfTheFirstOccurrenceInAString.swift
//  Algorithm
//
//  Created by Wonhee Lee on 8/25/25.
//

class FindTheIndexOfTheFirstOccurrenceInAString {
    func strStr(_ haystack: String, _ needle: String) -> Int {
        // O(n * n)
//        var count = 0
//        var stack = haystack
//        while count <= haystack.count - needle.count {
//            if stack.hasPrefix(needle) {
//                return count
//            }
//            count += 1
//            stack.removeFirst()
//        }
//        return -1
        // O(n * n)
        if let range = haystack.firstRange(of: needle) {
                    return haystack.distance(from: haystack.startIndex, to: range.lowerBound)
        } else {
            return -1
        }
    }
}
