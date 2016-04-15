var intentions: AATrie!

func main() {
  // We should crash if this argument is not given.
  let intentionPath = Process.arguments[1]

  intentions = loadWordIntentions(intentionPath)

  //Loop
  // input
  if let sentence = readLine() {
    let probabilities = probabilitiesForSentence(sentence)
    // print probabilities
  }
  //Exit Loop
}

func probabilitiesForSentence(sentence: String) -> [AAAction : Double] {
  var totalDetectedIntents: UInt = 0
  var detectedIntents = [AAAction : UInt]()
  for word in sentence.split(" ") {
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
  return probabilities
}

/**
 * We assume the Word Intention File has been prepared by taking "allowable
 *   words," mapping intents to each of these words, then adding phonetic and
 *   typo-close words. The file should be formatted with the words in one column
 *   followed by a tab, then a column of comma separated matching intents in
 *   string format. This will be the key for the output hash table's
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
 */
func loadWordIntentions(filepath: String) -> AATrie {
  return AATrie()
}

func intentsForWord(word: String) -> [AAAction] {
  if let intents = intentions.lookup(word) {
    return intents
  } else {
    return []
  }
}

extension String : CollectionType {}