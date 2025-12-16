class ValidPalindrome {
    func isPalindrome(_ s: String) -> Bool {
        // let trimmedString = s.lowercased().filter { $0.isLetter || $0.isNumber }
        // return trimmedString == String(trimmedString.reversed())

        let trimmedString = s.lowercased().filter { $0.isLetter || $0.isNumber }
        let arrayString = Array(trimmedString)
        var left = 0
        var right = arrayString.count - 1
        while left < right {
            if arrayString[left] != arrayString[right] { return false }
            left += 1
            right -= 1
        }
        return true
    }
}