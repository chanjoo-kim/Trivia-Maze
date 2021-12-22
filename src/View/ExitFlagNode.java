package View;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * This class creates a Text node with the string "Exit" within a HBox. A HBox is a layout element that stands for
 * Horizontal box. The Horizontal box is the same size as a square maze node (IE: Wall, Door, Room) and is necessary to
 * center the exit flag text within. It is named as a Node because HBoxes are a child class of Node and although this
 * class uses HBox functionality the current implementation of the MazeGroup allows for any Java node to exist within
 * the 2-d array maze structure. The exit flag currently is just a Box node with a Text node contained within
 * indicating the exit point.
 *
 * @author Brandon Rosario
 * @version 1.1
 * @since 11-15-2021
 */
public class ExitFlagNode extends HBox {

    /**
     * Constant used to dictate the font size in points of the exit flag Text. The Text will always be one fifth the
     * size of the smallest value of the SQUARE_NODE_WIDTH or SQUARE_NODE_ROW constants.
     */
    private static final double FONT_SIZE = Math.min(GuiConstants.SQUARE_NODE_WIDTH,
            GuiConstants.SQUARE_NODE_HEIGHT) / 5;


    /**
     * Constructor for the exit flag. Currently the exit flag is just a HBox with a Text Node contained within.
     * This constructor sets the HBox size, and Alignment of its children. It also adds a Text Node within that sets
     * the font family, font weight, font posture, and font size in points.
     */
    ExitFlagNode() {
        //The size Specifications and alignment of the children contained within.
        setPrefWidth(GuiConstants.SQUARE_NODE_WIDTH);
        setPrefHeight(GuiConstants.SQUARE_NODE_HEIGHT);
        setAlignment(Pos.CENTER);

        //The text to be contained in the HBox.
        Text exitText = new Text("Exit");

        //This sets the details of the font.
        exitText.setFont(Font.font("helvetica", FontWeight.EXTRA_BOLD, FontPosture.REGULAR, FONT_SIZE));

        //Adds the Text Node to the HBox.
        getChildren().add(exitText);
    }
}
