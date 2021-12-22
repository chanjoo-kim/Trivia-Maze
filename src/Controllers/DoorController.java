package Controllers;

import Model.Door;
import Model.Maze;
import View.DoorNode;
import View.GuiConstants;
import View.TriviaMazeLayout;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.paint.Color;

public class DoorController {
    private Maze myMaze;

    public DoorController(final TriviaMazeLayout theMazeLayout) {
        myMaze = Maze.getInstance();
        setController(theMazeLayout);
    }

    private void setController(final TriviaMazeLayout theMazeLayout) {
        for(int row = 1; row < GuiConstants.MAZE_ROWS - 1; row++) {
            for(int col = 1; col < GuiConstants.MAZE_COLS - 1; col++) {

                if(theMazeLayout.getMazeGUI().getNodeAtMazeLocation(row, col).getClass() == View.DoorNode.class &&
                myMaze.getMazeStructure(row, col).getClass() == Model.Door.class) {

                    DoorNode theDoorNode =  (DoorNode) theMazeLayout.getMazeGUI().getNodeAtMazeLocation(row, col);
                    Door theDoor = (Door) myMaze.getMazeStructure(row, col);

                    theDoorNode.viewOrderProperty().bind(Bindings.when(theDoor.getIsSelectedAsProperty())
                            .then(-1).otherwise(0));

                    theDoorNode.strokeProperty().bind(Bindings.when(theDoor.getIsSelectedAsProperty())
                            .then(Color.ORANGE).otherwise(Color.BLACK));

                    theDoorNode.strokeWidthProperty().bind(Bindings.when(theDoor.getIsSelectedAsProperty())
                            .then(GuiConstants.SQUARE_NODE_WIDTH / 10).otherwise(1));

                    theDoor.getIsLockedAsProperty().addListener((observableValue, theOldBoolean, theNewBoolean) -> {
                        if (theNewBoolean) {
                            theDoorNode.setFill(Color.RED);
                            myMaze.setSelectedDoor(myMaze.getNoSelectedDoor());
                        }
                    });

                    theDoor.getIsOpenAsProperty().addListener((observableValue, theOldBoolean, theNewBoolean) -> {
                        if (theNewBoolean) {
                            theDoorNode.setFill(Color.GREEN);
                            myMaze.setSelectedDoor(myMaze.getNoSelectedDoor());
                        }
                    });
                }
            }
        }
    }
}
