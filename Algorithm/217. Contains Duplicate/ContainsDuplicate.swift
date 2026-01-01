class ContainsDuplicate {
    func containsDuplicate(_ nums: [Int]) -> Bool {
        // var dict = [Int: Int]()
        // for num in nums {
        //     if let n = dict[num] {
        //         return true
        //     } else {
        //         dict[num] = 1
        //     }
        // }
        // return false

        var set = Set<Int>()
        for num in nums {
            if set.contains(num) {
                return true
            }
            set.insert(num)
        }
        return false
    }
}