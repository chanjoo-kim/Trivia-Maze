package View;

import Model.Door;
import javafx.scene.Group;
import javafx.scene.Node;

/**
 * This class creates a group that we can consider to be the "maze" as a whole. The group contains an observable list
 * that has all game objects (nodes) contained within. This list is not sorted and all elements placed within the list
 * lay in indices determined on the order they were placed. All nodes are then displayed by setting there initial
 * position according to a 2-d array that serves as a backing store for all the positions of all the game elements.
 * All rectangular game object positions are placed algorithmically and are will be placed properly even if the maze
 * shrinks or expands in size (Note: Not the window size, this means if the constants of the GuiConstants.MAZE_ROWS or
 * GuiConstants.MAZE_COLUMNS are changed).
 *
 * TODO: Consider refactoring player movement to be handled by the backend through MVC and update the render in this class.
 * TODO: Backing store of the 2-d maze array should be handled by the backend (Model).
 *
 * @author Brandon Rosario
 * @version 1.1
 * @since 11-15-2021
 */
public class MazeGroup extends Group {
    /**
     * Current backing store for the 2-d maze. Needed for interactions in the maze, easier node rendering, and overall
     * better code structure. Should also be moved to the backend.
     * <p>
     * TODO: MVC: Move 2-d array backing store to backend (model).
     */
    private final Node[][] myMaze;

    private final PlayerNode myPlayerNode;
    /**
     * Constructor that calls helper methods placing all game objects in the maze backing store and displays them
     * accordingly.
     */
    MazeGroup() {
        myMaze = new Node[GuiConstants.MAZE_ROWS][GuiConstants.MAZE_COLS];
        myPlayerNode = new PlayerNode();

        addWallNodesToMazeArray();
        addDoorNodesToMazeArray();
        addRoomNodesToMazeArray();
        addNodesToMazeGroup();
    }

    /**
     * Helper method to add wall nodes to the 2-d array backing store. Currently, this method fills the backing store
     * with wall nodes. This method has been refactored to only add walls to the borders of the maze and to the
     * "pillars" of the maze which are located in even rows and even columns (IE: [2,2], [2,4], [4,2], [4,4]). Works as
     * intended but really should be refactored for readability.
     * <p>
     * TODO: Refactor for readability
     */
    private void addWallNodesToMazeArray() {
        //Top and Bottom row border.
        for (int col = 0; col < GuiConstants.MAZE_COLS; col++) {
            //Top row.
            myMaze[0][col] = new WallNode();

            //Bottom Row.
            myMaze[GuiConstants.MAZE_ROWS - 1][col] = new WallNode();
        }

        //Left and Right row border.
        for (int row = 1; row < GuiConstants.MAZE_ROWS - 1; row++) {
            //Left row.
            myMaze[row][0] = new WallNode();

            //Right Row.
            myMaze[row][GuiConstants.MAZE_COLS - 1] = new WallNode();
        }

        //Pillars placed at even rows and even cols.
        for (int row = 2; row < GuiConstants.MAZE_ROWS - 2; row = row + 2) {
            for (int col = 2; col < GuiConstants.MAZE_COLS - 2; col = col + 2) {
                myMaze[row][col] = new WallNode();
            }
        }
    }

    /**
     * Helper method to add the door nodes to the 2-d array backing store. The algorithm goes as follows: doors are
     * placed in odd rows and even columns (IE: [1,2], [1,4], [3,2], [3,4] ect.) and are placed in even rows and odd
     * columns (IE: [2,1], [2,3], [4,1], [4,3] ect.). Currently ,this works as intended but could be refactored for
     * readability.
     * <p>
     * TODO: Refactor for readability.
     */
    private void addDoorNodesToMazeArray() {
        //All rows that are not borders.
        for (int row = 1; row < GuiConstants.MAZE_ROWS - 1; row++) {
            //If the row is odd.
            if (row % 2 == 1) {
                //Only columns that are even and not borders.
                for (int col = 2; col < GuiConstants.MAZE_COLS - 1; col = col + 2) {
                    myMaze[row][col] = new DoorNode();
                }
            }
            //If the row is even.
            else {
                //Only columns that are odd and not borders.
                for (int col = 1; col < GuiConstants.MAZE_COLS - 1; col = col + 2) {
                    myMaze[row][col] = new DoorNode();
                }
            }
        }
    }

    /**
     * Helper method to add room nodes to the 2-d array backing store. Rooms are located at odd rows and odd columns
     * (IE: [1,1], [1,3], [3,1], [3,3]). Works as intended, doesn't need refactoring but since I am refactoring
     * everything else for readability then I might do the same for this.
     * <p>
     * TODO: Refactor for readability?
     */
    private void addRoomNodesToMazeArray() {
        for (int row = 1; row < GuiConstants.MAZE_ROWS - 1; row = row + 2) {
            for (int col = 1; col < GuiConstants.MAZE_COLS - 1; col = col + 2) {
                myMaze[row][col] = new RoomNode();
            }
        }
    }

    /**
     * Helper method that adds all nodes in the trivia maze to the MazeGroup allowing them all to be displayed. When this
     * helper method is called, all maze nodes (IE: Walls, Doors, Rooms) should be added to the 2-d maze array backing
     * store. This array is then iterated through and all elements are added to the group. The PlayerNode and the
     * ExitFlag node are added separately since they cannot take a position in the 2-d maze array and their locations
     * within the maze array are set and tracked separately. The ExitFlagNode is added and displayed in the opposite
     * position from where the PlayerNode is created.
     */
    private void addNodesToMazeGroup() {
        //Iterate through the 2-d maze array backing store and add all elements to the MazeGroup.
        for (int row = 0; row < GuiConstants.MAZE_ROWS; row++) {
            for (int col = 0; col < GuiConstants.MAZE_COLS; col++) {
                myMaze[row][col].setLayoutX(col * GuiConstants.SQUARE_NODE_WIDTH);
                myMaze[row][col].setLayoutY(row * GuiConstants.SQUARE_NODE_HEIGHT);
                getChildren().add(myMaze[row][col]);
            }
        }

        //Add the PlayerNode. It's initial position is determined within its own class

        getChildren().add(myPlayerNode);

        //The ExitFlagNode is created, and it's initial position is set algorithmically based on where the PlayerNode's
        //initial position is.
        ExitFlagNode exitFlagNode = new ExitFlagNode();
        exitFlagNode.setLayoutX((Math.
                abs(myPlayerNode.getLayoutX() - GuiConstants.MAZE_COLS) - 2) * GuiConstants.SQUARE_NODE_WIDTH);
        exitFlagNode.setLayoutY((Math.
                abs(myPlayerNode.getLayoutY() - GuiConstants.MAZE_ROWS) - 2) * GuiConstants.SQUARE_NODE_HEIGHT);

        //Add the Exit flag to the group.
        getChildren().add(exitFlagNode);
    }

    public Node getNodeAtMazeLocation(final int theRow, final int theCol) {
        return myMaze[theRow][theCol];
    }

    public PlayerNode getPlayerNode() {
        return myPlayerNode;
    }
}
