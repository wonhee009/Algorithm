class WordPattern {
    func wordPattern(_ pattern: String, _ s: String) -> Bool {
        let sArray = s.split(separator: " ").map { String($0) }
        if pattern.count != sArray.count { return false }
        var patternToS: [Character: String] = [:]
        var valueSet: Set<String> = []
        
        for (index, value) in pattern.enumerated() {
            if let s = patternToS[value] {
                if s != sArray[index] {
                    return false
                } else {
                    patternToS[value] = sArray[index]
                }
            } else {
                if valueSet.contains(sArray[index]) {
                    return false
                } else {
                    valueSet.insert(sArray[index])
                    patternToS[value] = sArray[index]
                }
            }
        }
        return true
    }
}