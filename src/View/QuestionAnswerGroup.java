package View;

import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 *
 */
public class QuestionAnswerGroup extends VBox {
    private final Label myQuestionLabel = new Label("The question: ");
    private final Text myQuestionText;

    private final ToggleGroup myTrueFalseToggleGroup = new ToggleGroup();
    private final RadioButton myTrueRadioButton = new RadioButton("True");
    private final RadioButton myFalseRadioButton = new RadioButton("False");

    private final ToggleGroup myMCToggleGroup = new ToggleGroup();
    private final RadioButton myMCFirstChoiceRadioButton = new RadioButton();
    private final RadioButton myMCSecondChoiceRadioButton = new RadioButton();
    private final RadioButton myMCThirdChoiceRadioButton = new RadioButton();

    private final TextArea myShortAnswerBox;
    private final Button mySubmitAnswerButton;

    QuestionAnswerGroup() {
        setPrefHeight(GuiConstants.SCENE_HEIGHT);
        setPrefWidth(500);

        myQuestionText = new Text("There is no current question.");
        myShortAnswerBox = new TextArea();
        mySubmitAnswerButton =  new Button("Submit Answer");

        myTrueRadioButton.setToggleGroup(myTrueFalseToggleGroup);
        myFalseRadioButton.setToggleGroup(myTrueFalseToggleGroup);

        myMCFirstChoiceRadioButton.setToggleGroup(myMCToggleGroup);
        myMCSecondChoiceRadioButton.setToggleGroup(myMCToggleGroup);
        myMCThirdChoiceRadioButton.setToggleGroup(myMCToggleGroup);

        getChildren().addAll(myQuestionLabel, myQuestionText);
    }

    public void changeQuestionGroup(final String theQuestionType){

        switch(theQuestionType) {
            case "MC" :
                getChildren().clear();

                getChildren().addAll(myQuestionLabel, myQuestionText, myMCFirstChoiceRadioButton,
                        myMCSecondChoiceRadioButton, myMCThirdChoiceRadioButton, mySubmitAnswerButton);
                break;

            case "TrueFalse" :
                getChildren().clear();
                getChildren().addAll(myQuestionLabel, myQuestionText, myTrueRadioButton, myFalseRadioButton,
                        mySubmitAnswerButton);
                break;

            case "ShortAnswer" :
                getChildren().clear();
                getChildren().addAll(myQuestionLabel, myQuestionText, myShortAnswerBox, mySubmitAnswerButton);
                break;

            case "None" :
                getChildren().clear();
                getChildren().addAll(myQuestionLabel, myQuestionText);

            default:
                break;
        }
    }

    public void setMyQuestionText(final String theNewQuestionText){
        myQuestionText.setText(theNewQuestionText);
    }

    public Button getSubmitAnswerButton() {
        return mySubmitAnswerButton;
    }

    public ToggleGroup getTrueFalseToggleGroup() {
        return myTrueFalseToggleGroup;
    }

    public ToggleGroup getMCToggleGroup() {
        return myMCToggleGroup;
    }

    public RadioButton getMCFirstChoiceRadioButton() {
        return myMCFirstChoiceRadioButton;
    }

    public RadioButton getMCSecondChoiceRadioButton() {
        return myMCSecondChoiceRadioButton;
    }

    public RadioButton getMCThirdChoiceRadioButton() {
        return myMCThirdChoiceRadioButton;
    }

    public TextArea getSATextArea() {
        return myShortAnswerBox;
    }
}
