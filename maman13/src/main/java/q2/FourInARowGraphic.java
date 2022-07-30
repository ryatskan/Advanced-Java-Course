package q2;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.ArrayList;

import static q2.FourInARowApplication.currScene;
import static q2.FourInARowLogic.*;
import static q2.FourInARowLogic.isIsGameOver;
import static q2.GamePanes.initPanes;

/*
 * A class that controls the graphics in the application -
 * adding chips, clearing screen, printing winner, etc..
 * The graphic class still contains some "logic", but it is
 * just calling methods from the logic class. The alternative is
 * that the graphic class will have mostly 1-line functions.
 */
public class FourInARowGraphic {
    // FXML Constants
    @FXML
    Circle turnIndicator;
    @FXML
    Button clearButton;
    private static ArrayList<Circle> chipList = new ArrayList<>();

    /*
     * Upon clicking the clear button, clear all the chips from the screen
     * logic and graphic wise.
     */
    @FXML
    void clearChipButtonClick() {
        if (!isIsGameOver()) {
            clearChipsFromScreen();
            updateCurrentTurnIndicator(FourInARowLogic.BEGINNINGPLAYER.toColor());
            initPanes();
            initTurn();
        }
    }

    /*
     * Clears all the chips from the screen
     */
    static void clearChipsFromScreen() {
        Pane gamePane = (Pane) currScene.lookup("#gamePane");
        for (Circle c : chipList) {
            gamePane.getChildren().remove(c);
        }
    }

    /*
     * Pop a new screen showing the winner of the game.
     */
    static void printWinner(Scene currScene, Color winnerColor, String winnerName) {
        Pane winnerPane = (Pane) currScene.lookup("#winnerPane");
        Rectangle winnerRec = (Rectangle) currScene.lookup(("#winnerRec"));
        winnerRec.setFill(winnerColor);
        Text winnerText = (Text) currScene.lookup("#winnerText");
        winnerText.setText(winnerName);
        winnerPane.setVisible(true);

    }

    /*
     * Add a new chip to the scene in x,y location and a given color.
     */
    static void addNewChipToScene(Scene currScene, int x, int y, Color chipColor) {
        int chipRadius = 30;
        Circle newChip = createNewCircle(x, y, chipColor, chipRadius);
        chipList.add(newChip);
        Pane gamePane = (Pane) currScene.lookup("#gamePane");
        gamePane.getChildren().add(newChip);
    }

    /*
     * Creates a new circle with given parameters.
     */
    static Circle createNewCircle(int x, int y, Color circleColor, int circleRadius) {
        Circle newCircle = new Circle();
        newCircle.centerXProperty().setValue(x);
        newCircle.centerYProperty().setValue(y);
        newCircle.setFill(circleColor);
        newCircle.setRadius(circleRadius);
        return newCircle;
    }

    /*
     * update the turn indicator - indicates whose turn is it by showing the
     * player's chip color.
     */
    static void updateCurrentTurnIndicator(Color turnColor) {
        Circle turnIndicator = (Circle) currScene.lookup("#turnIndicator");
        turnIndicator.setFill(turnColor);
    }

    /*
     * Upon clicking the button Pane (one with 7 numbers), add
     * new a new chip and check if now any player won the game.
     */
    @FXML
    void buttonPaneClick(MouseEvent mEvent) {
        if (!isIsGameOver()) {
            if (FourInARowLogic.addNewChip(mEvent)) {
                FourInARowLogic.switchTurnsLogic();
                updateCurrentTurnIndicator(FourInARowLogic.getTurnColor());
                FourInARowLogic.handlePossibleWin();
            }
        }
    }

    /*
     * Upon clicking any key, when a game is over, start a new one.
     */
    @FXML
    void keyPressing() {
        if (isIsGameOver()) {
            FourInARowLogic.startNewGame();
        }
    }
}