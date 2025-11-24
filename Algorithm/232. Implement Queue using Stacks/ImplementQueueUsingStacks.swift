//
//  ImplementQueueUsingStacks.swift
//  Algorithm
//
//  Created by Wonhee Lee on 11/24/25.
//
// https://leetcode.com/problems/implement-queue-using-stacks/description/

class ImplementQueueUsingStacks {
    var stack: [Int]
    var reversedStack: [Int]
    init() {
        stack = []
        reversedStack = []
    }
    
    func push(_ x: Int) {
        stack.append(x)
    }
    
    func pop() -> Int {
        if reversedStack.isEmpty {
            moveToReversedStack()
        }
        return reversedStack.removeFirst()
    }
    
    func peek() -> Int {
        if reversedStack.isEmpty {
            moveToReversedStack()
        }
        return reversedStack.first!
    }
    
    private func moveToReversedStack() {
        while !stack.isEmpty {
            reversedStack.append(stack.removeLast())
        }
    }
    
    func empty() -> Bool {
        return stack.isEmpty && reversedStack.isEmpty
    }
}
