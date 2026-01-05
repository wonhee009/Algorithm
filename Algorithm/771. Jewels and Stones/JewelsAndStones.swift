class JewelsAndStones {
    func numJewelsInStones(_ jewels: String, _ stones: String) -> Int {
        let set = Set(jewels)
        return stones.reduce(0) { $0 + set.contains($1) ? 1 : 0) }     
    }
}