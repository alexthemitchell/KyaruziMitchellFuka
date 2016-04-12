typealias AAAction = String

func main() {
  // Use filename as Action identifier
  var totalDetectedIntents: UInt = 0
  var detectedIntents = [String : UInt]()

  var Dictionary = AATrie()

  // read input

}

/**
 * We assume the Word Intention File has been prepared by taking "allowable
 *   words," mapping intents to each of these words, then adding phonetic and
 *   typo-close words. The file should be formatted with the words in one column
 *   followed by a tab, then a column of comma separated matching intents in
 *   string format. This will be the key for the output hash table's
 *   probability.
 *
 * e.g. ~/WordIntentions.map
 * ~~~~~~~~~~~~~~~~~~~~~~~~~
 *  quadratic   quadForm
 *  kwadratic   quadForm
 *  pythagorean pythTheorem
 *  pytgahorean pythTheorem
 *  theorem     pythTheorem
 *  formula     quadForm,pythTheorem
 */
func loadWordIntentions(filepath: String) {

}

func intentForWord(word: String) -> AAAction {

}
