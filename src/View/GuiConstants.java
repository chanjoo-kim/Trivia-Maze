package View;

/**
 * Simple class that stores all the constants that will be used throughout the GUI. Created for
 * organizational structure and ease of changing the appearance of the application. Currently, this class is
 * package-private because only the View package (GUI) should be using these constants.
 *
 * @author Brandon Rosario
 * @version 1.1
 * @since 11-15-2021
 */
public class GuiConstants {
    /**
     * The number of rows that the maze will contain. Can be any odd number greater than or equal to 5 (IE: 5, 7, 9,
     * ... ect).
     *
     * TODO: Refactor idea: could add a setter with a check for valid value.
     */
    public static final int MAZE_ROWS = 7;

    /**
     * The number of columns that the maze will contain.  Can be any odd number greater than or equal to 5 (IE: 5, 7, 9,
     * ... ect).
     *
     * TODO: Refactor idea: Could add a setter with a check for valid value.
     */
    public static final int MAZE_COLS = 7;

    /**
     * The height of a square node in number of pixels (IE: Walls, Doors, Empty Spaces). Can be any number. Currently,
     * values between 10 - 100 seem ideal. Currently, the square node height and width should constants should be
     * identical to conform to the constant name "SQUARE"
     *
     * TODO: Refactor idea: Could add a setter with a check for valid value.
     */
    public static final double SQUARE_NODE_HEIGHT = 100;

    /**
     * The width of a square node in number of pixels (IE: Walls, Doors, Empty Spaces). Can be any number. Currently,
     * values between 10 - 100 seem ideal. Currently, the square node height and width should constants should be
     * identical to conform to the constant name "SQUARE"
     *
     * TODO: Refactor idea: Could add a setter with a check for valid value.
     */
    public static final double SQUARE_NODE_WIDTH = 100;

    /**
     * The amount of pixels that the initial scene height is set to. This is a function of the MAZE_ROWS and the
     * SQUARE_NODE HEIGHT to set up a scene that perfectly contains the entire maze.
     */
    public static final double SCENE_HEIGHT = MAZE_ROWS * SQUARE_NODE_HEIGHT;

    /**
     * The amount of pixels that the initial scene height is set to. This is a function of the MAZE_ROWS and the
     * SQUARE_NODE HEIGHT to set up a scene that perfectly contains the entire maze.
     */
    public static final double SCENE_WIDTH = MAZE_COLS * SQUARE_NODE_WIDTH;
}
