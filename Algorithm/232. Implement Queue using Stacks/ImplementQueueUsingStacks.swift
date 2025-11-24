//
//  ImplementQueueUsingStacks.swift
//  Algorithm
//
//  Created by Wonhee Lee on 11/24/25.
//
// https://leetcode.com/problems/implement-queue-using-stacks/description/

class ImplementQueueUsingStacks {
    private var input: [Int] = []
    private var output: [Int] = []

    func push(_ x: Int) {
        input.append(x)
    }

    func pop() -> Int {
        if output.isEmpty {
            shift()
        }
        return output.removeLast()
    }

    func peek() -> Int {
        if output.isEmpty {
            shift()
        }
        return output.last!
    }

    func empty() -> Bool {
        return input.isEmpty && output.isEmpty
    }

    private func shift() {
        while let value = input.popLast() {
            output.append(value)
        }
    }
}
