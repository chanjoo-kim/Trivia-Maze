package Model;

import java.util.ArrayList;
import java.util.Collections;

public class QuestionMC extends Question {
    private final ArrayList<String> myMCOptionList;
    private final String myMCOption1;
    private final String myMCOption2;
    private final String myMCOption3;

    QuestionMC(final String theQuestion,
               final String theAnswer,
               final String theMCOption1,
               final String theMCOption2) {

        super(theQuestion, theAnswer);
        super.setQuestionType("MC");

        myMCOptionList = new ArrayList<>();
        myMCOptionList.add(super.getAnswerString());
        myMCOptionList.add(theMCOption1);
        myMCOptionList.add(theMCOption2);

        Collections.shuffle(myMCOptionList);

        myMCOption1 = myMCOptionList.get(0);
        myMCOption2 = myMCOptionList.get(1);
        myMCOption3 = myMCOptionList.get(2);
    }

    public String getMCOption1() {
        return myMCOption1;
    }

    public String getMCOption2() {
        return myMCOption2;
    }

    public String getMCOption3() {
        return myMCOption3;
    }
}
