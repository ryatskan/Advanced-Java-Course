package q2;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

/*
 * Represents a Dictionary, that contains words and their meanings.
 * In reality is an interactive list of DictionaryWords.
 */
class Dictionary {

    // The actual data structure.
    private final LinkedList<DictionaryWord> dWords = new LinkedList<>();

    /*
     * Receives a word. If exists in Dictionary, remove its corresponding DictionaryWord
     * from the Dictionary and return true.
     * else do nothing and return false;
     */
    boolean removeDictionaryWord(String word) {
        DictionaryWord correspondingDicWord = getDictionaryWord(word);
        return dWords.remove(correspondingDicWord);
    }

    /*
     * Add a new word to the dictionary. Return true if added successfully,
     * and false otherwise.
     */
    boolean addNewWord(String word, String meaning) throws Exception {
        DictionaryWord newEntry = new DictionaryWord(word, meaning);
        return addDictionaryWord(newEntry);

    }

    /*
     * Update the meaning of a specific word in the dictionary.
     * Return true if successfully updated, return false otherwise.
     */
    void updateDictionaryWord(String word, String newMeaning) throws Exception {
        DictionaryWord matchingWord = null;
        boolean foundWord = false;
        if (word.equals("")) throw new Exception("Word is empty!");

        for (DictionaryWord d : dWords) {
            // Find the DictionaryWord that its word is the same as given one.
            if (word.compareTo(d.getWord()) == 0) {
                matchingWord = d;
                foundWord = true;
            }
        }
        // Update the word if found
        if (foundWord) {
            // If the current word's meaning is different from the given one.
            if (!matchingWord.getMeaning().equals(newMeaning)) {
                matchingWord.setMeaning(newMeaning);
            } else {
                throw new Exception("Same meaning already exists!");
            }
        } else throw new Exception("Word not found!");
    }


    /*
     * Add a new DictionaryWord input into the Dictionary. If the Dictionary already contains the word
     * of the input, return false and do nothing.
     * Else, add the input to the Dictionary and return true.
     */
    boolean addDictionaryWord(DictionaryWord word) {
        int i, comparison;
        /* iterate over each DictionaryWord in the dictionary. find the first word that is smaller
        than the given word*/
        for (i = 0; i < dWords.size(); i++) {
            comparison = dWords.get(i).getWord().compareTo(word.getWord());
            if (comparison == 0) return false;
            if (comparison > 0) break;
        }
        dWords.add(i, word);
        return true;
    }

    /*
     * Load a dictionary from file.
     */
    static Dictionary fileToDictionary(File file) {
        if (file == null) return null;
        if (!file.isFile()) return null;

        Dictionary dic = new Dictionary();
        // Read the file line by line:
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {

                if (line.length() != 0) {
                    DictionaryWord dWord = lineToDictionaryWord(line);

                    if (dWord != null) {
                        dic.addDictionaryWord(dWord);
                    }
                    else return null;
                }
            }

        } catch (Exception e) {
            return null;
        }
        return dic;
    }

    /*
     * Helper method.
     * Receives a String representing a line in File. Transform it into a DictionaryWord
     * assuming it is formatted "Word;Meaning". If not formatted that way, return null.
     */
    private static DictionaryWord lineToDictionaryWord(String line) throws Exception {
        String word, meaning;
        String[] parts = line.split(";");
        if (parts.length != 2) return null;
        else {
            word = parts[0];
            meaning = parts[1];
        }
        return new DictionaryWord(word, meaning);
    }

    /*
     * Receives a word, find the corresponding DictionaryWord and return it.
     */
    private DictionaryWord getDictionaryWord(String word) {
        for (DictionaryWord dicWord : dWords) {
            if (dicWord.getWord().equals(word)) {
                return dicWord;
            }
        }
        return null;
    }

    /*
     * Receives a word, find the corresponding DictionaryWord and return its meaning parameter.
     * If not found any corresponding DictionaryWord, return null.
     */
    String getMeaning(String word) {

        DictionaryWord matchingDicWord = getDictionaryWord(word);
        if (matchingDicWord != null) {
            return matchingDicWord.getMeaning();
        } else return null;
    }

    int getAmountOfWord() {
        return dWords.size();
    }

    /*
     * Get entry
     */
    DictionaryWord getWordOfIndex(int index) {
        try {
            return new DictionaryWord(dWords.get(index).getWord(), dWords.get(index).getMeaning());
        } catch (Exception e) {
            return null;
        }
    }
}
