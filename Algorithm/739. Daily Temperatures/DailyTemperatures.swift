class DailyTemperatures {
    func dailyTemperatures(_ temperatures: [Int]) -> [Int] {
        var result = Array(repeating: 0, count: temperatures.count)
        var stack = [Int]()

        for i in 0..<temperatures.count {
            while !stack.isEmpty && temperatures[i] > temperatures[stack.last!] {
                let last = stack.removeLast()
                result[last] = i - last
            }
            stack.append(i)
        }

        return result
    }
}