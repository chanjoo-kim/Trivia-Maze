package Model;

import View.GuiConstants;

public class ExitFlag {

    private static final ExitFlag mySingleExitFlagInstance = new ExitFlag();
    private final Player myPlayer;
    private final int myXPos;
    private final int myYPos;

    private ExitFlag() {
        myPlayer = Player.getInstance();
        myXPos = XPosOppositeOfPlayer();
        myYPos = YPosOppositeOfPlayer();
    }

    private int XPosOppositeOfPlayer() {
        return Math.abs(myPlayer.getXPosAsInt() - GuiConstants.MAZE_COLS) - 1;
    }

    private int YPosOppositeOfPlayer() {
        return Math.abs(myPlayer.getYPosAsInt() - GuiConstants.MAZE_ROWS) - 1;
    }

    public static ExitFlag getInstance() {
        return mySingleExitFlagInstance;
    }

    public int getXPos() {
        return myXPos;
    }

    public int getYPos() {
        return myYPos;
    }
}
