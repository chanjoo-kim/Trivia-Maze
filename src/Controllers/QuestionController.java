package Controllers;

import Model.Maze;
import Model.MazePathfinder;
import Model.QuestionMC;
import View.TriviaMazeLayout;
import javafx.application.Platform;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.RadioButton;


public class QuestionController {
    final private Maze myMaze;

    public QuestionController(final TriviaMazeLayout theMazeLayout) {
        myMaze = Maze.getInstance();
        setController(theMazeLayout);
    }

    private void setController(final TriviaMazeLayout theMazeLayout) {

        myMaze.getSelectedDoor().addListener((observableValue, TheOldDoor, theNewDoor) -> {
            theMazeLayout.getQuestionGUI().setMyQuestionText(theNewDoor.getQuestion().getQuestionString());
            theMazeLayout.getQuestionGUI().changeQuestionGroup(theNewDoor.getQuestion().getQuestionType());

            if(theNewDoor.getQuestion().getQuestionType() == "MC") {
                QuestionMC dummyMCQuestion = (QuestionMC) theNewDoor.getQuestion();
                theMazeLayout.getQuestionGUI().getMCFirstChoiceRadioButton().setText(dummyMCQuestion.getMCOption1());
                theMazeLayout.getQuestionGUI().getMCSecondChoiceRadioButton().setText(dummyMCQuestion.getMCOption2());
                theMazeLayout.getQuestionGUI().getMCThirdChoiceRadioButton().setText(dummyMCQuestion.getMCOption3());
            }
        });

        theMazeLayout.getQuestionGUI().getSubmitAnswerButton().setOnAction(actionEvent -> {
            String userAnswer = "";
            RadioButton selectedRadioButton;
            switch (myMaze.getSelectedDoor().get().getQuestion().getQuestionType()) {
                case "MC":
                    selectedRadioButton = (RadioButton) theMazeLayout.getQuestionGUI().getMCToggleGroup()
                            .getSelectedToggle();
                    userAnswer = selectedRadioButton.getText();
                    break;
                case "ShortAnswer":
                    userAnswer = theMazeLayout.getQuestionGUI().getSATextArea().getText();
                    theMazeLayout.getQuestionGUI().getSATextArea().clear();
                    break;
                case "TrueFalse":
                    selectedRadioButton = (RadioButton) theMazeLayout.getQuestionGUI().getTrueFalseToggleGroup()
                            .getSelectedToggle();
                    userAnswer = selectedRadioButton.getText();
                    break;
                default:
                    break;
            }

            if(myMaze.getSelectedDoor().get().getQuestion().getAnswerString().equals(userAnswer)){
                myMaze.getSelectedDoor().get().setIsOpen(true);
            } else {
                myMaze.getSelectedDoor().get().setIsLocked(true);
            }

            checkForLoss();
        });
    }

    private void checkForLoss() {
        if(!MazePathfinder.isPathToExit()) {
            Dialog<String> winDialog = new Dialog<>();
            ButtonType buttonType = new ButtonType("Exit Program");
            winDialog.setTitle("You Lost!");
            winDialog.setContentText("I'm sorry, you got locked in the trivia maze. :(");
            winDialog.getDialogPane().getButtonTypes().add(buttonType);
            winDialog.showAndWait();
            Platform.exit();
        }
    }
}
