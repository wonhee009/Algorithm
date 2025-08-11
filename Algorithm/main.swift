//
//  main.swift
//  Algorithm
//
//  Created by Wonhee Lee on 7/19/25.
//

import Foundation

let removeDuplicatesFromSortedArray = RemoveDuplicatesFromSortedArray()
var obj1 = [1, 1, 2]
var obj2 = [0,0,1,1,1,2,2,3,3,4]
print(removeDuplicatesFromSortedArray.removeDuplicates(&obj1))
print(removeDuplicatesFromSortedArray.removeDuplicates(&obj2))
