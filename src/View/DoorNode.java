package View;

import javafx.beans.property.BooleanProperty;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * This class creates a yellow Rectangle shape. It is named as a Node because rectangles are a child class
 * of Node and although this class uses Rectangle functionality the current implementation of the MazeGroup allows for
 * any JavaFX node to exist within the 2-d array maze structure. The Door is currently just a yellow box. There is a
 * possibility of changing the door's shape and appearance although that may take some thinking about.
 *
 * TODO: Make all maze nodes inherit from abstract class to eliminate code reuse.
 *
 * @author Brandon Rosario
 * @version 1.2
 * @since 11-15-2021
 */
public class DoorNode extends Rectangle {

    /**
     * Constructor for the door node (Rectangle). Currently, doors in the trivia maze are just yellow boxes but can
     * be changed later.
     */
    DoorNode() {
        //This is a rectangular node, and it's height and width are determined within the GuiConstants class and can be
        //changed there.
        setWidth(GuiConstants.SQUARE_NODE_WIDTH);
        setHeight(GuiConstants.SQUARE_NODE_HEIGHT);
        setStroke(Color.BLACK);
        setFill(Color.YELLOW);
    }
}
