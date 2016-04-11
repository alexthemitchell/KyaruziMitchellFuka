public class AATrie {
    private class AANode {
        var children = Dictionary<Character, AANode>()
        var actions: [String]
        
        init(actions: [String]) {
            self.actions = actions
        }
    }
    
    private let root = AANode(actions: [])
    
    private func sanitizeWord(input : String) -> String {
        // Force lowercase
        let word = input.lowercaseString
        
        return word
    }
    
    public func lookup(input: String) -> [String]? {
        let word = sanitizeWord(input)
        return nodeForWord(word)?.actions
    }
    
    public func delete(word: String) {
        let node = nodeForWord(word)
        node?.actions = []
    }
    
    private func nodeForWord(input : String) -> AANode? {
        let word = input.lowercaseString
        var consider = root
        for letter in word.characters {
            if let next = consider.children[letter] {
                consider = next
            } else { // The subtree for the letter does not exist
                return nil
            }
        }
        return consider
    }
    
    public func insertWord(input : String, actions: [String]) {
        let word = sanitizeWord(input)
        var current = root
        for letter in word.characters {
            if current.children[letter] == nil {
                current.children[letter] = AANode(actions: [])
            }
            current = current.children[letter]!
        }
        current.actions = actions
    }
}
