class ReverseString {
    func reverseString(_ s: inout [Character]) {
        // var left = 0
        // var right = s.count - 1
        // while left < right {
        //     let temp = s[left]
        //     s[left ] = s[right]
        //     s[right] = temp
        //     left += 1
        //     right -= 1
        // }

        var left = 0
        var right = s.count - 1
        while left < right {
            s.swapAt(left, right)
            left += 1
            right -= 1
        }
    }
}