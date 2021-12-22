package View;

import Controllers.DoorController;
import Controllers.PlayerController;
import Controllers.QuestionController;
import Model.Maze;
import Model.Player;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;

public class TriviaMazeLayout extends VBox {

    private Player myPlayer;
    private Maze myMaze;
    private MazeGroup myMazeGUI;
    private QuestionAnswerGroup myQuestionGUI;

    TriviaMazeLayout() {
        myPlayer = Player.getInstance();
        myMaze = Maze.getInstance();
        myMazeGUI = new MazeGroup();
        myQuestionGUI = new QuestionAnswerGroup();

        new PlayerController(this);
        new DoorController(this);
        new QuestionController(this);

        getChildren().addAll(myMazeGUI, myQuestionGUI);

        setOnMouseClicked(mouseEvent ->
            myMazeGUI.requestFocus()
        );

        //----------------------------------------------added
        HBox hbox = new HBox();
        Menu menu = new Menu("Menu");
        // create menu item: save feature stuff in menu
        MenuItem saveItem = new MenuItem("Save");
        String filename = "triviaMaz.ser";
        saveItem.setOnAction(e -> {
            try {
                FileOutputStream file = new FileOutputStream(filename);
                ObjectOutputStream out = new ObjectOutputStream(file);
                out.writeObject(myPlayer);
                out.close();
                file.close();

            } catch (IOException ex) {
                ex.printStackTrace();
                System.out.println("IOException is caught  save exception");
            }
        });

        //do the deserilze the next thing to do, bc the deserilize just assign to the properties after the----------------
        MenuItem openItem = new MenuItem("Reload");
        openItem.setOnAction(e -> {
            try {
                FileInputStream file = new FileInputStream (filename);
                ObjectInputStream in = new ObjectInputStream (file);

                myPlayer = (Player) in.readObject();

                in.close();
                file.close();
            } catch (IOException ex) {
                System.out.println("IOException is caught this one");
            }
            catch (ClassNotFoundException ex) {
                System.out.println("ClassNotFoundException" +
                        " is caught");
            }
        });
        MenuItem restItem = new MenuItem("Reset");
        restItem.setOnAction(e -> {
        });

        Menu Music = new Menu("Music");
        //submenu start and stop music
        MenuItem submenuStart = new MenuItem("Start music");
        MenuItem submenuStop = new MenuItem("Stop music");
        Music.getItems().add(submenuStart);
        Music.getItems().add(submenuStop);

        //getting items into menu
        menu.getItems().add(saveItem);
        menu.getItems().add(openItem);
        menu.getItems().add(restItem);
        menu.getItems().add(Music);
        MenuBar mb = new MenuBar();
//--------------------------------------------------------------------------------------------
        //------------------------Tools menu ----------------------------------------------------------------
        Menu tools = new Menu("Tools");

        // create tools items
        MenuItem instruction = new MenuItem("Instructions");
        MenuItem aboutUs = new MenuItem("The Creators");

        //adding tool items to tool menu
        tools.getItems().add(instruction);
        tools.getItems().add(aboutUs);
        //------------------------dialog box for the creators----------------------------------------------------------------
        //creating aboutUS box
        Dialog<String> dialogBox2 = new Dialog<>();
        ButtonType aboutUsBut = new ButtonType("Okay", ButtonBar.ButtonData.OK_DONE);
        //Setting the content of the dialog
        dialogBox2.setContentText("Be greatful that you exist");
        //Adding buttons to the dialog pane
        dialogBox2.getDialogPane().getButtonTypes().add(aboutUsBut);
        aboutUs.setOnAction( e -> dialogBox2.showAndWait());

//------------------------dialog box for the instruction----------------------------------------------------------------
                //creating aboutUS box
        Dialog<String> dialogBox1 = new Dialog<>();
        ButtonType InstructButton = new ButtonType("Okay", ButtonBar.ButtonData.OK_DONE);
        //Setting the content of the dialog
        dialogBox1.setContentText("Make to the end");
        //Adding buttons to the dialog pane
        dialogBox1.getDialogPane().getButtonTypes().add(InstructButton);
        instruction.setOnAction( e-> dialogBox1.showAndWait());

        //add menu to menubar
        mb.getMenus().add(menu);
        mb.getMenus().add(tools);

        hbox.getChildren().addAll(myMazeGUI,myQuestionGUI);
        getChildren().addAll(mb, hbox);
    }

    public MazeGroup getMazeGUI() {
        return myMazeGUI;
    }

    public QuestionAnswerGroup getQuestionGUI() {
        return myQuestionGUI;
    }
}
