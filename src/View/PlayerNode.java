package View;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * This class creates a green Circle shape. It is named as a Node because Circles are a child class of
 * Node and although this class uses Circle functionality the current implementation of the MazeGroup allows for any
 * JavaFX node to exist within the 2-d array maze structure. The main actor/player can be any sort of shape or Icon. We
 * should have a discussion on how to represent the main actor/player.
 *
 * @author Brandon Rosario
 * @version 1.1
 * @since 11-15-2021
 */
public class PlayerNode extends ImageView {
    /**
     * Constant used to determine the main actor/player circle radius. Is calculated by finding the smallest value
     * among the SQUARE_NODE_WIDTH/SQUARE_NODE_HEIGHT values and setting itself as a 2/5 fraction of that height or
     * width. Effectively this makes the diameter of the main actor/player circle always have a diameter that is 4/5 of
     * the smallest width or height of the Rectangle nodes of the maze.
     */
//    final private static double myRadius = (Math.min(GuiConstants.SQUARE_NODE_WIDTH,
//            GuiConstants.SQUARE_NODE_HEIGHT) * 2 / 5);


    /**
     * Constructor for the main actor/player node which is just a Circle shape. Currently, the player is just a green
     * circle but can be changed later.
     */
    PlayerNode() {
        //Sets the radius and initial center position of the main actor/player. The center position is a function of the
        //starting position in the 2-d maze array and the constants of the square node height and widths. This ensures
        //that the center of the circle will be in the center of the square nodes
//        setRadius(myRadius);
//        setCenterX(GuiConstants.SQUARE_NODE_WIDTH * 0.5);
//        setCenterY(GuiConstants.SQUARE_NODE_HEIGHT * 0.5);
//
//        setStroke(Color.BLACK);
//        setFill(Color.GREEN);

        Image player = new Image("PlayerSprite.png");
        setImage(player);
        setFitWidth(GuiConstants.SQUARE_NODE_HEIGHT);
        setFitHeight(GuiConstants.SQUARE_NODE_WIDTH);

    }
}
