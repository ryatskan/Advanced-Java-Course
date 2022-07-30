package q2;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

import java.io.File;

/*
 * Handles the entirety of the Dictionary Application except for executing graphical changes,
 * which is handled by DictionaryAppGraphic.
 * Uses the Dictionary class as data structure.
 */
public class DictionaryAppController {
    @FXML
    Text interactiveText;
    @FXML
    TextField wordTextField;
    @FXML
    TextField meaningTextField;
    @FXML
    VBox dictionaryVBox;

    // the data structure of the class - contains all the words and their meanings.
    private Dictionary mainDic = new Dictionary();

    private final FileChooser fileChooser = new FileChooser();

    // the instance we use to access DictionaryAppGraphic methods
    private final DictionaryAppGraphic dicGraphic = new DictionaryAppGraphic();

    /*======= 1/2 BUTTON INTERACTION

    /*
     * Action upon clicking the add word button. Receives a word and a meaning from the text field,
     * and if not already in the dictionary and legal, add it to the dictionary.
     */
    @FXML
    private void addWordButtonClick() {
        String givenWord = wordTextField.getText();
        String meaning = meaningTextField.getText();

        // Both fields are non-empty
        try {
            mainDic.addNewWord(givenWord, meaning);
            updateDictionaryGraphically();
            dicGraphic.changeTextField(interactiveText,
                    "\"" + givenWord + "\"" + " was successfully added!");

        } catch (Exception e) {
            // if failed to add the word
            dicGraphic.changeTextField(interactiveText,e.getMessage());
        }
    }



    /*
     * Action upon clicking the delete button. Receives a word from the text field,
     * and look it up on the dictionary. If found, delete it from the dictionary.
     */
    @FXML
    private void deleteButtonClick() {
        String givenWord = wordTextField.getText();


        boolean succeeded = mainDic.removeDictionaryWord(givenWord);

        if (succeeded) {
            updateDictionaryGraphically();
            dicGraphic.changeTextField(interactiveText,
                    "\"" + givenWord + "\"" + " was successfully deleted!");
        } else {
            dicGraphic.changeTextField(interactiveText,
                    "Failed to delete the given word");

        }
    }

    /*
     * Action upon clicking the search button. Receives a word from the text field,
     * and look it up on the dictionary. If found, Prints the result onto interactiveText.
     */
    @FXML
    private void searchButtonClick() {
        String word = wordTextField.getText();
        String meaning = mainDic.getMeaning(word);
        if (!word.equals("")) {
            if (meaning != null) {
                dicGraphic.changeTextField(interactiveText, "The meaning of the word " +
                        "\"" + word + "\"" + " is " + meaning);
            } else {
                dicGraphic.changeTextField(interactiveText, "Failed to find the word " +
                        "\"" + word + "\"");
            }
        } else {
            dicGraphic.changeTextField(interactiveText, "Word is empty!");
        }
    }


    /*
     * Action upon clicking the update button. Receives a word and a meaning from the text
     * fields, look up the word in the dictionary. If found, and the new meaning is different,
     * update the meaning.
     */
    @FXML
    private void updateButtonClick() {
        String word = wordTextField.getText();
        String newMeaning = meaningTextField.getText();

        // Both fields are non-empty
        try {
            mainDic.updateDictionaryWord(word, newMeaning);
            updateDictionaryGraphically();
            dicGraphic.changeTextField(interactiveText,
                    "Successfully updated the word's meaning.");

        } catch (Exception e) {
            dicGraphic.changeTextField(interactiveText,
                    e.getMessage());

        }
    }


    /*
     * Action upon clicking the load file button. Loads a file, transform it into a Dictionary class
     * and make it the new Dictionary.
     * If failed to access/analyze a file keep the old dictionary.
     */
    @FXML
    private void loadFileButtonClick() {
        boolean succeeded = loadFile();
        if (succeeded) {
            updateDictionaryGraphically();
            dicGraphic.changeTextField(interactiveText,
                    "Successfully loaded a file");

        } else {
            dicGraphic.changeTextField(interactiveText,
                    "Error loading a file!");
        }
    }

    /*======= 2/2 Helper Functions

    /*
     * Action upon clicking the load file button
     * Loads a file from XXXXXXX, and create a new Dictionary from it. clean the current Dictionary
     * and replace it with the Dictionary from the file.
     */


    /*
     * Pops the file selection dialog, and load the file as a dictionary.
     * Return true if succeeded and false otherwise.
     */
    private boolean loadFile() {
        File file = fileChooser.showOpenDialog(DictionaryApplication.currStage);
        Dictionary newDic = Dictionary.fileToDictionary(file);
        if (newDic == null) {
            return false;
        } else {
            mainDic = newDic;
            return true;
        }
    }

    /*
     * Loads all the entries in the Dictionary data structure to the graphical ScrollPane.
     */
    private void updateDictionaryGraphically() {
        int i;
        DictionaryWord dicWord;

        dicGraphic.clearDictionaryTable(dictionaryVBox);
        // add the words graphically in an ordered way
        for (i = 0; i < mainDic.getAmountOfWord(); i++) {

            dicWord = mainDic.getWordOfIndex(i);
            dicGraphic.addToDictionaryTable(dictionaryVBox, dicWord.getWord(),
                    dicWord.getMeaning());
        }
    }
}
