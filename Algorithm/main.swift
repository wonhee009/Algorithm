//
//  main.swift
//  Algorithm
//
//  Created by Wonhee Lee on 7/19/25.
//

import Foundation

let removeElement = RemoveElement()
var obj1 = [3,2,2,3]
var obj2 = [0,1,2,2,3,0,4,2]
print(removeElement.removeElement(&obj1, 3))
print(obj1)
print(removeElement.removeElement(&obj2, 2))
print(obj2)
