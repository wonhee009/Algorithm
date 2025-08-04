//
//  MergeTwoSortedLists.swift
//  Algorithm
//
//  Created by Wonhee Lee on 8/4/25.
//
// https://leetcode.com/problems/merge-two-sorted-lists/description/

public class ListNode {
    public var val: Int
    public var next: ListNode?
    public init() { self.val = 0; self.next = nil; }
    public init(_ val: Int) { self.val = val; self.next = nil; }
    public init(_ val: Int, _ next: ListNode?) { self.val = val; self.next = next; }
}

class MergeTwoSortedLists {
    func mergeTwoLists(_ list1: ListNode?, _ list2: ListNode?) -> ListNode? {
        // o(n)
//        if list1 == nil {
//            return list2
//        } else if list2 == nil {
//            return list1
//        }
//        
//        if list1!.val <= list2!.val {
//            list1?.next = mergeTwoLists(list1?.next, list2)
//            return list1
//        } else {
//            list2?.next = mergeTwoLists(list1, list2?.next)
//            return list2
//        }
        
        // o(n)
        let dummy = ListNode(0)
        var tail: ListNode? = dummy
        
        var l1 = list1
        var l2 = list2
        
        while let node1 = l1, let node2 = l2 {
            if node1.val <= node2.val {
                tail?.next = node1
                l1 = node1.next
            } else {
                tail?.next = node2
                l2 = node2.next
            }
            tail = tail?.next
        }
        
        tail?.next = l1 ?? l2
        return dummy.next
    }
}
