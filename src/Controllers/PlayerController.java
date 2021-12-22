package Controllers;

import Model.*;
import View.GuiConstants;
import View.TriviaMazeLayout;
import javafx.application.Platform;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

public class PlayerController {
    final private Player myPlayer;
    final private ExitFlag myExitFlag;
    final private Maze myMaze;

    public PlayerController(TriviaMazeLayout theMazeLayout) {
        myPlayer = Player.getInstance();
        myExitFlag = ExitFlag.getInstance();
        myMaze = Maze.getInstance();
        setController(theMazeLayout);
    }

    private void setController(TriviaMazeLayout theMazeLayout) {

        theMazeLayout.getMazeGUI().getPlayerNode().translateXProperty()
                .bind(myPlayer.getXPosAsIntProperty().multiply(GuiConstants.SQUARE_NODE_WIDTH));

        theMazeLayout.getMazeGUI().getPlayerNode().translateYProperty()
                .bind(myPlayer.getYPosAsIntProperty().multiply(GuiConstants.SQUARE_NODE_HEIGHT));

        theMazeLayout.setOnKeyPressed(theKeyEvent -> {
            switch (theKeyEvent.getCode()) {
                case W :
                    if(myMaze.getMazeStructure(myPlayer.getYPosAsInt() - 1, myPlayer.getXPosAsInt())
                            .getIsPassable()) {
                        myMaze.setSelectedDoor(myMaze.getNoSelectedDoor());
                        do {
                            myPlayer.movePlayerNorth();
                        } while (myMaze.getMazeStructure(myPlayer.getYPosAsInt(), myPlayer.getXPosAsInt())
                                .getClass() != Room.class);
                        checkForWin();

                    } else if(myMaze.getMazeStructure(myPlayer.getYPosAsInt() - 1, myPlayer.getXPosAsInt())
                            .getClass() == Door.class) {
                        myMaze.setSelectedDoor((Door)myMaze.getMazeStructure(
                                myPlayer.getYPosAsInt() - 1, myPlayer.getXPosAsInt()));

                    } else {
                        myMaze.setSelectedDoor(myMaze.getNoSelectedDoor());
                    }
                    break;

                case S :
                    if(myMaze.getMazeStructure(myPlayer.getYPosAsInt() + 1, myPlayer.getXPosAsInt())
                            .getIsPassable()) {
                        myMaze.setSelectedDoor(myMaze.getNoSelectedDoor());
                        do {
                            myPlayer.movePlayerSouth();
                        } while (myMaze.getMazeStructure(myPlayer.getYPosAsInt(), myPlayer.getXPosAsInt())
                                .getClass() != Room.class);
                        checkForWin();

                    } else if(myMaze.getMazeStructure(myPlayer.getYPosAsInt() + 1, myPlayer.getXPosAsInt())
                            .getClass() == Door.class) {

                        myMaze.setSelectedDoor((Door)myMaze.getMazeStructure(
                                myPlayer.getYPosAsInt() + 1, myPlayer.getXPosAsInt()));

                    } else {
                        myMaze.setSelectedDoor(myMaze.getNoSelectedDoor());
                    }
                    break;

                case A :
                    if(myMaze.getMazeStructure(myPlayer.getYPosAsInt(), myPlayer.getXPosAsInt() - 1)
                            .getIsPassable()) {
                        myMaze.setSelectedDoor(myMaze.getNoSelectedDoor());
                        do {
                            myPlayer.movePlayerWest();
                        } while (myMaze.getMazeStructure(myPlayer.getYPosAsInt(), myPlayer.getXPosAsInt())
                                .getClass() != Room.class);
                        checkForWin();

                    } else if(myMaze.getMazeStructure(myPlayer.getYPosAsInt(), myPlayer.getXPosAsInt() - 1)
                            .getClass() == Door.class) {
                        myMaze.setSelectedDoor((Door)myMaze.getMazeStructure(
                                myPlayer.getYPosAsInt(), myPlayer.getXPosAsInt() - 1));

                    } else {
                        myMaze.setSelectedDoor(myMaze.getNoSelectedDoor());
                    }
                    break;

                case D :
                    if(myMaze.getMazeStructure( myPlayer.getYPosAsInt(), myPlayer.getXPosAsInt() + 1)
                            .getIsPassable()) {
                        myMaze.setSelectedDoor(myMaze.getNoSelectedDoor());
                        do {
                            myPlayer.movePlayerEast();
                        } while (myMaze.getMazeStructure(myPlayer.getYPosAsInt(), myPlayer.getXPosAsInt())
                                .getClass() != Room.class);
                        checkForWin();

                    } else if(myMaze.getMazeStructure(myPlayer.getYPosAsInt(), myPlayer.getXPosAsInt() + 1)
                            .getClass() == Door.class) {
                        myMaze.setSelectedDoor((Door)myMaze.getMazeStructure(
                                myPlayer.getYPosAsInt(), myPlayer.getXPosAsInt() + 1));

                    } else {
                        myMaze.setSelectedDoor(myMaze.getNoSelectedDoor());
                    }
                    break;
            }
        });
    }

    private void checkForWin() {
        if(isPlayerAtExitFlag()) {
            Dialog<String> winDialog = new Dialog<>();
            ButtonType buttonType = new ButtonType("Exit Program");
            winDialog.setTitle("You won!");
            winDialog.setContentText("Congratulations, you beat the Trivia Maze!");
            winDialog.getDialogPane().getButtonTypes().add(buttonType);
            winDialog.showAndWait();
            Platform.exit();
        }
    }

    private boolean isPlayerAtExitFlag() {
        return (myExitFlag.getXPos() == myPlayer.getXPosAsInt() && myExitFlag.getYPos() == myPlayer.getYPosAsInt());
    }
}
