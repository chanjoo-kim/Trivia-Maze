package Model;

import View.GuiConstants;

import java.util.Arrays;

public class MazePathfinder {
    private static final Maze myMaze = Maze.getInstance();
    private static final Player myPlayer = Player.getInstance();
    private static final ExitFlag myExitFlag = ExitFlag.getInstance();

    private MazePathfinder() {    }

    public static boolean isPathToExit() {
        char[][]  mazeTracker = new char[GuiConstants.MAZE_ROWS][GuiConstants.MAZE_COLS];

        for(char[] row : mazeTracker ) {
            Arrays.fill(row, '.');
        }

        return traverse(mazeTracker, myPlayer.getYPosAsInt(), myPlayer.getXPosAsInt());
    }

    private static boolean traverse(final char[][] theMazeTracker,final int theRow, final int theCol) {
        boolean canReachExit = false;
        if (validMove(theMazeTracker, theRow, theCol)) {
            if(atExit(theRow, theCol)) {
                return true;
            }

            canReachExit = traverse(theMazeTracker, theRow + 1, theCol);

            if (!canReachExit) {
                canReachExit = traverse(theMazeTracker, theRow - 1, theCol);
            }

            if (!canReachExit) {
                canReachExit = traverse(theMazeTracker, theRow, theCol + 1);
            }

            if (!canReachExit) {
                canReachExit = traverse(theMazeTracker, theRow, theCol - 1);
            }

            if (!canReachExit) {
                markDeadEnd(theMazeTracker, theRow, theCol);
            }
        }

        return canReachExit;
    }

    private static boolean validMove(final char[][] theMazeTracker, final int theRow, final int theCol) {
        boolean isValidMove;

        if(theMazeTracker[theRow][theCol] == '.') {
            if (myMaze.getMazeStructure(theRow, theCol).getClass() == Door.class) {
                Door currentDoor = (Door) myMaze.getMazeStructure(theRow, theCol);
                isValidMove = !currentDoor.getIsLockedAsProperty().get();
            } else {
                isValidMove = myMaze.getMazeStructure(theRow, theCol).getIsPassable();
            }
        } else {
            isValidMove = false;
        }

        if (myPlayer.getYPosAsInt() == theRow && myPlayer.getXPosAsInt() == theCol) {
            markStart(theMazeTracker, theRow, theCol);
        } else if (atExit(theRow, theCol)) {
            markEnd(theMazeTracker, theRow, theCol);
        } else if (theMazeTracker[theRow][theCol] == '.' && isValidMove) {
            markVisited(theMazeTracker, theRow, theCol);
        } else if (theMazeTracker[theRow][theCol] == '.' && !isValidMove) {
            markBlocked(theMazeTracker, theRow, theCol);
        }

        return isValidMove;
    }

    private static void markVisited(final char[][] theMazeTracker, final int theRow, final int theCol){
        theMazeTracker[theRow][theCol] = 'O';
    }

    private static void markBlocked(final char[][] theMazeTracker, final int theRow, final int theCol){
        theMazeTracker[theRow][theCol] = 'X';
    }

    private static void markStart(final char[][] theMazeTracker, final int theRow, final int theCol){
        theMazeTracker[theRow][theCol] = 'S';
    }

    private static void markEnd(final char[][] theMazeTracker, final int theRow, final int theCol){
        theMazeTracker[theRow][theCol] = 'E';
    }

    private static void markDeadEnd(final char[][] theMazeTracker, final int theRow, final int theCol){
        theMazeTracker[theRow][theCol] = 'D';
    }

    private static boolean atExit(final int theRow, final int theCol) {
        return theRow == myExitFlag.getYPos() && theCol == myExitFlag.getXPos();
    }
}
