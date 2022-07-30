package q2;
import java.util.ArrayList;

import static q2.FourInARowLogic.PANESINAROW;
import static q2.FourInARowLogic.PANESINCOLUMN;

/*
 * The grid of panes of the four in a row game.
 * Contains GamePane objects filling the grid.
 */
public class GamePanes {

    // list of all (game) panes in the grid.
    private static ArrayList<GamePane> gamePaneList;
    private static int[] freePaneInXOrder = {0, 0, 0, 0, 0, 0, 0, 0};

    /*
     * Create the grid - add multiple panes.
     */
    static void initPanes() {
        gamePaneList = new ArrayList<>();
        int i, j;
        for (i = 0; i < PANESINCOLUMN; i++) {
            for (j = 0; j < PANESINAROW; j++) {
                gamePaneList.add(new GamePane(j, i)); // check
            }
        }
        for (i = 0; i < PANESINAROW; i++) {
            freePaneInXOrder[i] = PANESINCOLUMN - 1;
        }
    }

    /*
     * given a x-axis value, find the column it is inside, and add
     * a new chip at the lowest empty pane of the column.
     */
    static GamePane addChipToGrid(FourInARowLogic.Player player, double x) {
        int i, column = -1, lowestFreeChip;
        for (i = 0; i < PANESINAROW; i++) {
            // find the column x is inside
            if (x <= GamePane.PANEWIDTH * (i + 1)) {
                column = i;
                break;
            }
        }
        // if found a column x is inside
        if (column != -1) {
            // If the column is full - no place to put another chip.
            lowestFreeChip = freePaneInXOrder[i];
            if (lowestFreeChip != -1) {
                GamePane gp = getPane(column, lowestFreeChip);
                gp.addChipToPane(player);
                freePaneInXOrder[column]--;
                return gp;
            } else return null;
        } else {
            System.out.println("Error");
            return null;
        }
    }

    /*
     * Clear chips from all game panes. Makes the grid empty of chips
     */
    static void clearChipsFromPanes() {
        for (GamePane pane : gamePaneList) {
            pane.clearChip();
        }
    }

    /*
     * Find a pane by its x,y order in the grid.
     */
    static GamePane getPane(int xOrder, int yOrder) {
        for (GamePane pane : gamePaneList) {
            if (pane.getxOrder() == xOrder && pane.getYOrder() == yOrder) {
                return pane;
            }
        }
        return null;
    }

    /*
     * check for an order of panes, with a starting pane and up/down pattern and
     * the size oef the order.
     */
    static boolean checkForOrderedPanes(int firstPaneX, int firstPaneY, FourInARowLogic.Player x, int diagDistance,
                                        int horDistance, int amount) {
        int i;
        GamePane currPane;
        int currXorder = firstPaneX;
        int currYorder = firstPaneY;
        for (i = 0; i < amount; i++) {
            currPane = getPane(currXorder, currYorder);

            if (currPane == null) return false;

            if (!currPane.isPlayerChip(x)) return false;

            currYorder += diagDistance;
            currXorder += horDistance;
        }
        return true;
    }

    // getter
    static ArrayList<GamePane> getAllPanes() {
        return (ArrayList<GamePane>) gamePaneList.clone();
    }

    /*
     * Represent a single pane in the game.
     * can associate a player's chip to it.
     */
    public static class GamePane {
        // game pane constant
        private static final int PANEWIDTH = 100;
        private static final int PANEHEIGHT = 70;
        // pane physical order in the grid
        private int xOrder;
        private int yOrder;
        // associated chip
        private FourInARowLogic.Player playerChip = null;
        // Constructor
        GamePane(int x, int y) {
            this.xOrder = x;
            this.yOrder = y;
        }
        /*
         * Associate a chip to the pane.
         */
        boolean addChipToPane(FourInARowLogic.Player x) {
            if (playerChip == null) {
                playerChip = x;
                return true;
            }
            return false;
        }
        // Two getters.
        int getxOrder() {
            return xOrder;
        }

        int getYOrder() {
            return yOrder;
        }
        // gets X-axis value of center of the pane.
        boolean isPlayerChip(FourInARowLogic.Player x) {
            return playerChip == x;
        }

        // gets X-axis value of center of the pane.
        int getX() {
            if (xOrder > 0) {
                return xOrder * PANEWIDTH + PANEWIDTH / 2;
            } else {
                return PANEHEIGHT / 2;
            }
        }
        // gets Y-axis value of center of the pane.
        int getY() {
            if (yOrder > 0) {
                return yOrder * PANEHEIGHT + PANEHEIGHT / 2;
            } else {
                return PANEHEIGHT / 2;
            }
        }
        // clear chip from the pane.
        void clearChip() {
            playerChip = null;
        }
    }
}
