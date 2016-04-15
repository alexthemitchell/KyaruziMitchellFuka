# Ajuna Kyaruzi and Alex Mitchell's 301 NLP Solution
## Preliminary Keyword Obfuscation
### Build
### Execute
## Runtime Student Input Interpreter
### Build
* `swiftc AATrie.swift main.swift`
### Execute
* Generate list of allowed words (defaults to filename `WordIntentions`)
* `./main /PATH/TO/WordIntentions`
* Will load dictionary with obfuscated words
* Main run loops reads sentence on STDIN, outputs JSON with probabilities to STDOUT
## Known Deficiencies
* Does not natively detect negation
* Should add phonetics to pre-compilation hashing function
