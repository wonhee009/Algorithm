//
//  MinStack.swift
//  Algorithm
//
//  Created by Wonhee Lee on 11/14/25.
//
// https://leetcode.com/problems/min-stack/description/

class MinStack {
    var stack: [Int]
    var orderedStack: [Int]
    init() {
        stack = []
        orderedStack = []
    }
    
    func push(_ val: Int) {
        stack.append(val)
        orderedStack = stack.sorted()
    }
    
    func pop() {
        stack.removeLast()
        orderedStack = stack.sorted()
    }
    
    func top() -> Int {
        return stack[stack.count - 1]
    }
    
    func getMin() -> Int {
        return orderedStack[0]
    }
}
