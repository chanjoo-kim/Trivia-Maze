package View;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This is the main class for the trivia maze. This class launches the GUI application from the main method and handles
 * the key listeners
 *
 * TODO: Refactor: figure out how to decouple the key listeners form the main class.
 *
 * @author Brandon Rosario
 * @version 1.1
 * @since 11-15-2021
 */
public class TriviaMazeApplication extends Application {

    /**
     * Main method that launches the GUI application.
     *
     * @param theArgs The command line arguments.
     */
    public static void main(String[] theArgs) {
        launch();
    }

    /**
     * Overridden abstract method for the JavaFX Application method. Is called when the launch() method is called from
     * within main. This is the primary starting point for all JavaFX applications.
     *
     * TODO: Refactor: it is possible to create our own custom Stage class to decouple the key listener.
     *
     * @param thePrimaryStage The primary stage of the JavaFX application. This is the parent container attached to the
     *                        primary window.
     */
    @Override
    public void start(Stage thePrimaryStage) {
        //The primary stage and therefore the primary window is not resizable.
        thePrimaryStage.setResizable(false);

        //The scene which as intermediary for the Stage and the Group where all content is displayed. This scene also
        //controls the actions listeners (Needs more research to see why this is the case).
        Scene scene = new Scene(new TriviaMazeLayout(), GuiConstants.SCENE_WIDTH + 500,
                GuiConstants.SCENE_HEIGHT + 25);

        //Keypress Listener for the Scene. The setOnKeyPressed() method is a listener that takes in a method that
        //implements the EventHandler interface. Currently, this is implemented through a lambda expression but can be
        //changed to a method reference for readability. (More research needs to be done on key events to fully understand
        //this implementation)

        //The name of the window displayed at the top of the window pane.
        thePrimaryStage.setTitle("Trivia Maze!");

        //Sets the primary scene to the stage.
        thePrimaryStage.setScene(scene);

        //Shows all contents of the Primary stage which consist of the scene and the Maze group contained within.
        thePrimaryStage.show();
    }
}