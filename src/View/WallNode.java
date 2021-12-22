package View;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * This class creates a gray Rectangle shape. It is named as a Node because Rectangles are a child
 * class of Node and although this class uses Rectangle functionality the current implementation of the MazeGroup allows
 * for any JavaFX node to exist within the 2-d array maze structure. Walls Are currently only placed at the edge of the
 * 2-d maze array and as "pillars" in even rows and columns but with a node for themselves it will be possible to
 * make more intricate maze designs very easy.
 *
 * TODO: Make all maze nodes inherit from abstract class to eliminate code reuse.
 *
 * @author Brandon Rosario
 * @version 1.1
 * @since 11-15-2021
 */
public class WallNode extends ImageView {
    /**
     * Constructor for the wall node. Currently the wall node is just a gary rectangle. There is a possibility
     * for wall nodes to have some sort of sprite design.
     *
     */
    WallNode() {
        Image wall = new Image("Wall.png");
        setImage(wall);
        setFitWidth(GuiConstants.SQUARE_NODE_HEIGHT);
        setFitHeight(GuiConstants.SQUARE_NODE_WIDTH);
    }
}
