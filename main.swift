typealias AAAction = String

var intentions: AATrie!

func main() {
  // We should crash if this argument is not given.
  let intentionPath = Process.arguments[arg1]

  intentions = loadWordIntentions(intentionPath)

  //Loop
    // input
    let sentence: String!

    let probabilities = probabilitiesForSentence(sentence)
    // print probabilities
  //Exit Loop
}

func probabilitiesForSentence(input: String) -> [String : Double] {
  var totalDetectedIntents: UInt = 0
  var detectedIntents = [String : UInt]()
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

}

func intentForWord(word: String) -> AAAction {

}
