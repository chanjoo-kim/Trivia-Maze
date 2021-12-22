package View;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * This class creates a white Rectangle shape. It is named as a Node because Rectangles are a child
 * class of Node and although this class uses Rectangle functionality the current implementation of the MazeGroup allows
 * for any JavaFX node to exist within the 2-d array maze structure. Creating a node to represent rooms or empty space
 * is necessary to have the 2-d maze array not contain any null values. Also, there are feature addition possibilities
 * with this implementation.
 *
 * TODO: Make all maze nodes inherit from abstract class to eliminate code reuse.
 *
 * @author Brandon Rosario
 * @version 1.1
 * @since 11-15-2021
 */
public class RoomNode extends Rectangle {

    /**
     * Constructor for the room node (Rectangle). Currently the room node is just a white rectangle. If issues arise
     * where the empty room is displayed over other nodes we can always shift it's z axis location.
     */
    RoomNode() {
        //This is a rectangular node, and it's height and width are determined within the GuiConstants class and can be
        //changed there.
        setWidth(GuiConstants.SQUARE_NODE_WIDTH);
        setHeight(GuiConstants.SQUARE_NODE_HEIGHT);
        setStroke(Color.BLACK);
        setFill(Color.WHITE);
    }
}
