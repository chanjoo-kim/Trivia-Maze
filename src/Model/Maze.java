package Model;

import View.GuiConstants;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Maze {

    private static Maze mySingleMazeInstance = new Maze();
    final private int myRows;
    final private int myCols;
    private QuestionPool myQuestionPool;
    private MazeStructure[][] myMaze;
    private Question myNoSelectedQuestion;
    private Door myNoSelectedDoor;
    private ObjectProperty<Door> mySelectedDoor;

    private  Maze() {
        myRows = GuiConstants.MAZE_ROWS;
        myCols = GuiConstants.MAZE_COLS;
        myQuestionPool = QuestionPool.getInstance();
        myMaze = new MazeStructure[myRows][myCols];
        myNoSelectedQuestion =  new QuestionNone();
        myNoSelectedDoor = new Door(myNoSelectedQuestion);
        mySelectedDoor = new SimpleObjectProperty<>(myNoSelectedDoor);

        addWalls();
        addDoors();
        addRooms();
    }

    private void addWalls() {
        //Top and Bottom row border.
        for(int col = 0; col < myCols; col++) {

            //Top row.
            myMaze[0][col] = new Wall();

            //Bottom Row.
            myMaze[myRows - 1][col] = new Wall();
        }

        //Left and Right row border.
        for(int row = 1; row < myRows - 1; row++) {
            //Left row.
            myMaze[row][0] = new Wall();

            //Right Row.
            myMaze[row][myCols - 1] =  new Wall();
        }

        //Pillars placed at even rows and even cols.
        for(int row = 2; row < myRows - 2; row = row + 2){
            for(int col = 2; col < myCols - 2; col = col + 2) {
                myMaze[row][col] = new Wall();
            }
        }
    }

    private void addDoors() {
        for(int row = 1; row < GuiConstants.MAZE_ROWS - 1; row++ ){
            //If the row is odd.
            if(row % 2 == 1){
                //Only columns that are even and not borders.
                for(int col = 2; col < GuiConstants.MAZE_COLS - 1; col = col + 2) {
                    myMaze[row][col] = new Door(myQuestionPool.getQuestionFromPool());
                }
            }
            //If the row is even.
            else {
                //Only columns that are odd and not borders.
                for(int col = 1; col < GuiConstants.MAZE_COLS - 1; col = col + 2) {
                    myMaze[row][col] = new Door(myQuestionPool.getQuestionFromPool());
                }
            }
        }
    }

    private void addRooms() {
        for(int row = 1; row < myRows - 1; row = row + 2) {
            for(int col = 1; col < myCols - 1; col = col + 2) {
                myMaze[row][col] = new Room();
            }
        }
    }

    public static Maze getInstance(){
        return mySingleMazeInstance;
    }

    public MazeStructure getMazeStructure(final int theRow, final int theColumn) {
        return myMaze[theRow][theColumn];
    }

    public ObjectProperty<Door> getSelectedDoor() {
        return mySelectedDoor;
    }

    public void setSelectedDoor(Door theSelectedDoor) {
        if(!theSelectedDoor.getIsLockedAsProperty().get()) {
            mySelectedDoor.get().setIsSelected(false);
            mySelectedDoor.set(theSelectedDoor);
            mySelectedDoor.get().setIsSelected(true);
        } else {
            setSelectedDoor(getNoSelectedDoor());
        }
    }

    public Door getNoSelectedDoor() {
        return myNoSelectedDoor;
    }
}
