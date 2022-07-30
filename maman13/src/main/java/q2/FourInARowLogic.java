package q2;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import static q2.FourInARowApplication.currScene;
import static q2.FourInARowGraphic.*;


/*
 * A class that controls all the logic in the four in a row game.
 */
public class FourInARowLogic {
    // grid constants
    public static final int PANESINAROW = 7;
    public static final int PANESINCOLUMN = 6;

    private static boolean isGameOver = false;
    private static Player currTurn;
    public static Player BEGINNINGPLAYER = Player.BLUE;

    enum Player {
        RED,
        BLUE;

        public Color toColor() {
            if (this == Player.BLUE) {
                return Color.DODGERBLUE;
            }
            if (this == Player.RED) {
                return Color.RED;
            }
            return null;
        }

        @Override
        public String toString() {
            if (this == Player.BLUE) {
                return "BLUE";
            }
            if (this == Player.RED) {
                return "RED";
            }
            return null;
        }
    }

    /*
     * Initialize a new game.
     */
    static void initGame(Scene newScene) {
        currScene = newScene;
        initTurn();
        GamePanes.initPanes();

        Pane winnerPane = (Pane) currScene.lookup("#winnerPane");
        Pane buttonPane = (Pane) currScene.lookup("#buttonPane");
        buttonPane.setViewOrder(-2);
        winnerPane.setViewOrder(-2);
        winnerPane.setVisible(false);
    }
    /*
     * Start a new game.
     */
    public static void startNewGame() {
            Pane winnerPane = (Pane) currScene.lookup("#winnerPane");
            winnerPane.setVisible(false);
            clearChipsFromScreen();
            initTurn();
            GamePanes.initPanes();
            isGameOver = false;
    }

    /*
     * init the current turn into the default beginning turn,
     * and update the turnIndicator.
     */
    static void initTurn() {
        currTurn = BEGINNINGPLAYER;
        updateCurrentTurnIndicator(BEGINNINGPLAYER.toColor());
    }

    /*
     * Switches between the two players, if
     * it was RED, now it BLUE's turn, and
     * vice versa.
     */
    static void switchTurnsLogic() {
        if (currTurn.toString().equals("RED")) {
            currTurn =  Player.BLUE;
        } else currTurn =  Player.RED;
    }

    /*
     * main program to add a chip. program is activated when user clicks on
     * screen.
     */
    static boolean addNewChip(MouseEvent mEvent) {
        double sceneX = mEvent.getSceneX();
        GamePanes.GamePane paneToInsert;
        paneToInsert = GamePanes.addChipToGrid(currTurn,sceneX);
        if (paneToInsert != null) {
           addNewChipToScene(currScene, paneToInsert.getX(), paneToInsert.getY(), currTurn.toColor());
           return true;
        }
        else return false;
    }

    /* =================================================================================
     * NEXT TWO FUNCTIONS - Check if a player has won the game (checkForWin() method).
     * If so, print the winning player, and mark the game as over.
     */
    static void handlePossibleWin() {
        Player winner = checkForWin();
        if (winner != null) {
            printWinner(currScene, winner.toColor(), winner.toString());
            isGameOver = true;
        }
    }

    /*
     * Check if any player has won the game - has an horizontal, diagonal or vertical
     * order of 4 chips.
     */
    static Player checkForWin() {
        int paneXOrder, paneYOrder;
        for (Player x : Player.values()) {
            for (GamePanes.GamePane pane : GamePanes.getAllPanes()) {
                paneXOrder = pane.getxOrder();
                paneYOrder = pane.getYOrder();
                boolean fourInARow = GamePanes.checkForOrderedPanes(paneXOrder, paneYOrder,
                        x, 0, 1, 4);
                boolean fourInAColumn = GamePanes.checkForOrderedPanes(paneXOrder, paneYOrder,
                        x, 1, 0, 4);
                boolean fourInADecreasingDiagonal = GamePanes.checkForOrderedPanes(paneXOrder, paneYOrder,
                        x, 1, -1, 4);
                boolean fourInAIncreasingDiagonal = GamePanes.checkForOrderedPanes(paneXOrder, paneYOrder,
                        x, 1, 1, 4);
                if (fourInAColumn || fourInARow || fourInADecreasingDiagonal || fourInAIncreasingDiagonal) {
                    return x;
                }
            }
        }
        return null;
    }
    // Two getters
    static Color getTurnColor() {
        return currTurn.toColor();

    }
    static boolean isIsGameOver() {
        return isGameOver;
    }

}