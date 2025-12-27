class ContainsDuplicate {
    func containsDuplicate(_ nums: [Int]) -> Bool {
        var dict = [Int: Int]()
        for num in nums {
            if let n = dict[num] {
                return true
            } else {
                dict[num] = 1
            }
        }
        return false
    }
}