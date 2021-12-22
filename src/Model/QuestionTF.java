package Model;

public class QuestionTF extends Question{
    QuestionTF(final String theQuestion, final String theAnswer) {
        super(theQuestion, theAnswer);
        super.setQuestionType("TrueFalse");
    }
}
