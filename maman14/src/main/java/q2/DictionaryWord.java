package q2;


/*
 * Represent a word in a dictionary, that contains the word itself (as string) and its meaning (as string)
 */
public class DictionaryWord {
    static final private int wordLengthLimit = 20;
    static final private int meaningLengthLimit = 70;
    /*
     * The two parameters
     */
    private String word;
    private String meaning;

    /*
     * Two getters
     */
    public String getWord() {
        return word;
    }

    public String getMeaning() {
        return meaning;
    }

    /*
     * Constructor.
     */
    public DictionaryWord(String givenWord, String givenMeaning) throws Exception {
        setWord(givenWord);
        setMeaning(givenMeaning);

    }

    /*
     * two setters
     */
    void setMeaning(String str) throws Exception {
        try {
            if (str != null) {
                if (str.equals("")) throw new Exception("is empty!");
                if (str.length() > meaningLengthLimit) throw new Exception("exceeds length limit!");
            }
        } catch (Exception e) {
            throw new Exception("Meaning " + e.getMessage());
        }
        meaning = str;
    }

    void setWord(String str) throws Exception {
        try {
            if (str != null) {
                if (str.equals("")) throw new Exception("is empty!");
                if (str.length() > wordLengthLimit) throw new Exception("exceeds length limit!");
            }
        } catch (Exception e) {
            throw new Exception("Word " + e.getMessage());
        }
        word = str;
    }
}
