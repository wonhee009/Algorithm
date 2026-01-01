class HappyNumber {
    func isHappy(_ n: Int) -> Bool {
        if n == 1 {
            return true
        }
        var set: Set<Int> = [n]
        var sum = n
        while sum != 1 {
            var num = sum
            sum = 0
            while num > 0 {
                sum += (num % 10) * (num % 10)
                num /= 10
            }
            if set.contains(sum) {
                return false
            }
            set.insert(sum)
        }
        return true
    }
}