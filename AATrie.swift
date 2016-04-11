import Foundation

public protocol AATrie {
  func processWords(words strWords: String, separatedBy : Character)
  func max() -> (word: String, count : UInt)?
  func min() -> (word: String, count : UInt)?

  // Creates an entry or increases the existing entry by one
  func touchWord(var word : String)
  func touchWords(words : [String])
  func printAll()
}

public class AMTDictionary : AMDictionary {
  private class AMDNode {
    var children = Dictionary<Character, AMDNode>()
    var actions: [String]
  }

  private let root = AMDNode()

  public func processWords(words strWords: String, separatedBy : Character = " ") {
    var words = strWords.componentsSeparatedByCharactersInSet(NSCharacterSet.whitespaceAndNewlineCharacterSet())
    words = words.filter { $0 != "" }
    touchWords(words)
  }

  private func sanitizeWord(var word : String) -> String {
    // "Files have been stripped so as to contain only capital, lower case, space, and newline characters. Words are separated by any number of spaces and/or newline characters. Make sure your parser is case insensitive."
    // Force lowercase
    word = word.lowercaseString

    return word
  }

  public func max() -> (word: String, count : UInt)? {
    if let (word, node) = maxNode(root, currentMax: (root, word: ""), word: "") {
      return (word, node.count)
    } else {
      return nil
    }
  }

  private func maxNode(base: AMDNode, var currentMax: (word: String, node: AMDNode), word: String) -> (word: String, node: AMDNode)? {
    if base.count > currentMax.node.count {
      currentMax = (word, base)
    }
    for (letter, node) in base.children {
      if let childMax = maxNode(node, currentMax: currentMax, word: "\(word)\(letter)") {
        if childMax.node.count > currentMax.node.count {
          currentMax = childMax
        }
      } else {
        return nil
      }
    }
    return currentMax
  }

  public func min() -> (word: String, count : UInt)? {
    if let (word, node) = minNode(root, currentMin: (root, word: ""), word: "") {
      return (word, node.count)
    } else {
      return nil
    }
  }

  private func minNode(base: AMDNode, var currentMin: (word: String, node: AMDNode), word: String) -> (word: String, node: AMDNode)? {
    for (letter, node) in base.children {
      if let childMin = minNode(node, currentMin: currentMin, word: "\(word)\(letter)") {
        if childMin.node.count < currentMin.node.count  || currentMin.node.count == 0 {
          currentMin = childMin
        }
      }
    }

    return ((base.count < currentMin.node.count || currentMin.node.count == 0) && base.count > 0) ?
                                                                                     (word, base) :
                                                                                       currentMin
  }

  public func lookup(word: String) -> UInt? {
    return nodeForWord(word)?.count
  }

  public func untouchWord(word: String) {
    let node = nodeForWord(word)
    node?.count--
  }
  public func delete(word: String) {
    let node = nodeForWord(word)
    node?.count = 0
  }

  private func nodeForWord(var word : String) -> AMDNode? {
    word = word.lowercaseString
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

  public func touchWord(var word : String) {
    word = sanitizeWord(word)
    var current = root
    for letter in word.characters {
      if current.children[letter] == nil {
        current.children[letter] = AMDNode()
      }
      current = current.children[letter]!
    }
    current.count++
  }

  public func touchWords(words : [String]) {
    for word in words {
      touchWord(word)
    }
  }

  public func printAll() {
    printNode(node: root, word: "")
  }

  private func printNode(node base: AMDNode, word : String) {
    if base.count > 0 {
      print("\(word): \(base.count)")
    }
    for (letter, node) in base.children {
      printNode(node: node, word: "\(word)\(letter)")
    }
  }
}
