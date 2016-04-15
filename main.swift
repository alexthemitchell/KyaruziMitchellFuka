import Foundation

var intentions: AATrie!

func probabilitiesForSentence(sentence: String) -> ([AAAction : Double], UInt) {
  var totalDetectedIntents: UInt = 0
  var detectedIntents = [AAAction : UInt]()
  for word in sentence.componentsSeparatedByString(" ") {
    let intents = intentsForWord(word)
    for intent in intents {
      if detectedIntents[intent] == nil {
        detectedIntents[intent] = 0
      }
      detectedIntents[intent]! += 1
      totalDetectedIntents += 1
    }
  }

  var probabilities = [AAAction : Double]()
  for (intent,count) in detectedIntents {
    let percentage = Double(count)/Double(totalDetectedIntents)
    probabilities[intent] = percentage
  }
  return (probabilities, totalDetectedIntents)
}

/**
 * We assume the Word Intention File has been prepared by taking "allowable
 *   words," mapping intents to each of these words, then adding phonetic and
 *   typo-close words. The file should be formatted with the words in one column
 *   followed by a four tabs, then a column of comma separated matching intents
 *   in string format. This will be the key for the output hash table's
 *   probability.
 *
 * e.g. /home/user/WordIntentions.map
 * ~~~~~~~~~~~~~~~~~~~~~~~~~
 *  quadratic   quadForm
 *  kwadratic   quadForm
 *  pythagorean pythTheorem
 *  pytgahorean pythTheorem
 *  theorem     pythTheorem
 *  formula     quadForm,pythTheorem
 * .
 * .
 * .
 * \n
 */
func loadWordIntentions(filepath: String) -> AATrie {
  if let content = try? String(contentsOfFile: filepath, encoding: NSUTF8StringEncoding) {
    return parseFileText(content)
  } else {
      fatalError("\(filepath) was not readable")
  }
}

func parseFileText(fullText: String) -> AATrie {
  let trie = AATrie()
  var pairs = fullText.componentsSeparatedByString("\n")
  // The newline at the end of the file generates an empty pair
  pairs.removeLast()
  for pair in pairs {
    let entries = pair.componentsSeparatedByString(" ")
    let keyword = entries[0]
    let actions = entries[1].componentsSeparatedByString(",")
    print("\(keyword): \(actions)")
    trie.insertWord(keyword, actions: actions)
  }
  return trie
}

func intentsForWord(word: String) -> [AAAction] {
  if let intents = intentions.lookup(word) {
    return intents
  } else {
    return []
  }
}

let arguments = Process.arguments
let intentionPath = arguments[1]

intentions = loadWordIntentions(intentionPath)
print("Keywords loaded. Listening on STDIN")
while true {
  if let sentence = readLine() {
    let start = NSDate()
    let (probabilities, actionCount) = probabilitiesForSentence(sentence)
    // processTime will be milliseconds it took to look up in the trie
    let processTime = start.timeIntervalSinceNow * -1000
    let output = [
      "probabilities" : probabilities,
      "actionWords" : actionCount,
      "computeTime" : processTime
    ]
    let data = try! NSJSONSerialization.dataWithJSONObject(output, options: .PrettyPrinted)
    print(String(data: data, encoding: NSUTF8StringEncoding)!)
  }
}
