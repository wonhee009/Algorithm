//
//  main.swift
//  Algorithm
//
//  Created by Wonhee Lee on 7/19/25.
//

import Foundation

let validParentheses = ValidParentheses()
print(validParentheses.isValid("()"))
print(validParentheses.isValid("()[]{}"))
print(validParentheses.isValid("(]"))
print(validParentheses.isValid("([])"))
print(validParentheses.isValid("([)]"))

