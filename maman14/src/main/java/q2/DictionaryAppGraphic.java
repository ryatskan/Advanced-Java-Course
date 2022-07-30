package q2;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
/*
 * Do all the graphical changes in the package, as directed by the DictionaryAppController.
 */
public class DictionaryAppGraphic {
    /*
     * Create a new pane representing a dictionary entry graphically -
     * an HBox with two Strings, the word and the meaning.
     */
    Pane createScrollPaneComponent(String word, String meaning) {
        int fontSize = 20, wrappingWidth = 400;

        Font defFont = Font.font("Verdana", FontWeight.BOLD, fontSize);
        Text meaningTextBox = new Text("| Meaning: " + meaning);
        Text wordTextBox = new Text("Word: " + word);
        wordTextBox.setWrappingWidth(wrappingWidth);
        wordTextBox.setFont(defFont);

        meaningTextBox.setWrappingWidth(wrappingWidth);
        meaningTextBox.setFont(defFont);


        HBox dicHBox = new HBox(wordTextBox, meaningTextBox);
        return new Pane(dicHBox);
    }

    /*
     * Clears the graphic dictionary table from any entries.
     */
    void clearDictionaryTable(VBox dictionaryVBox) {
        dictionaryVBox.getChildren().clear();
    }

    void changeTextField(Text givenField, String text) {
        givenField.setText(text);
    }

    /*
     * Add an entry to the graphic dictionary table.
     */
    void addToDictionaryTable(VBox dictionaryVBox, String word, String meaning) {
        Pane newEntry = createScrollPaneComponent(word, meaning);
        // add the new entry to the dictionary
        dictionaryVBox.getChildren().add(newEntry);
    }

}
