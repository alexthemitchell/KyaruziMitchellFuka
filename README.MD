# Ajuna Kyaruzi and Alex Mitchell's 301 NLP Solution
## Preliminary Keyword Obfuscation

### Build
* `javac WordGenerator.java`

### Execute
* `java WordGenerator`
* Use STDIN to write name of InputFile

## Runtime Student Input Interpreter

### Build
* `swiftc AATrie.swift main.swift`

### Execute
* Generate list of allowed words (defaults to filename `WordIntentions`)
* `./main /PATH/TO/WordIntentions`
* Will load dictionary with obfuscated words
* Main run loops reads sentence on STDIN, outputs JSON with probabilities to STDOUT

## Known Deficiencies
* Does not natively interpret negation
* Should add phonetics to pre-compilation hashing function
* Punctuation is not stripped from the students' input, but should be
* Partial word matches should add confidence
